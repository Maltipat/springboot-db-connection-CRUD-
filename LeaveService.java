package com.springboot_db_connection;

import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

@Service
public class LeaveService {
	private final EmployeeRepository employeeRepo;
	private final LeaveRequestRepository leaveRepo;

	public LeaveService(EmployeeRepository employeeRepo, LeaveRequestRepository leaveRepo) {
		this.employeeRepo = employeeRepo;
		this.leaveRepo = leaveRepo;
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	public LeaveRequest applyLeave(Long empId, LeaveRequest request) {
		Employee emp = employeeRepo.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));

		long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()) + 1;

		if (request.getStartDate().isBefore(emp.getJoiningDate()))
			throw new RuntimeException("Cannot apply leave before joining date");

		if (days > emp.getLeaveBalance())
			throw new RuntimeException("Not enough leave balance");

		request.setEmployee(emp);
		request.setStatus(LeaveStatus.PENDING);
		return leaveRepo.save(request);
	}

	public LeaveRequest approveLeave(Long leaveId) {
		LeaveRequest req = leaveRepo.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));

		if (req.getStatus() != LeaveStatus.PENDING)
			throw new RuntimeException("Leave already processed");

		long days = ChronoUnit.DAYS.between(req.getStartDate(), req.getEndDate()) + 1;
		Employee emp = req.getEmployee();

		if (emp.getLeaveBalance() < days)
			throw new RuntimeException("Insufficient leave balance");

		emp.setLeaveBalance(emp.getLeaveBalance() - (int) days);
		employeeRepo.save(emp);

		req.setStatus(LeaveStatus.APPROVED);
		return leaveRepo.save(req);
	}

	public LeaveRequest rejectLeave(Long leaveId) {
		LeaveRequest req = leaveRepo.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));

		if (req.getStatus() != LeaveStatus.PENDING)
			throw new RuntimeException("Leave already processed");

		req.setStatus(LeaveStatus.REJECTED);
		return leaveRepo.save(req);
	}

	public int getLeaveBalance(Long empId) {
		Employee emp = employeeRepo.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));
		return emp.getLeaveBalance();
	}
}
