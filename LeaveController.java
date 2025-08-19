package com.springboot_db_connection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return leaveService.addEmployee(employee);
    }

    @PostMapping("/employees/{empId}/leave")
    public LeaveRequest applyLeave(@PathVariable Long empId, @RequestBody LeaveRequest request) {
        return leaveService.applyLeave(empId, request);
    }

    @PutMapping("/leave/{leaveId}/approve")
    public LeaveRequest approveLeave(@PathVariable Long leaveId) {
        return leaveService.approveLeave(leaveId);
    }

    @PutMapping("/leave/{leaveId}/reject")
    public LeaveRequest rejectLeave(@PathVariable Long leaveId) {
        return leaveService.rejectLeave(leaveId);
    }

    @GetMapping("/employees/{empId}/balance")
    public int getLeaveBalance(@PathVariable Long empId) {
        return leaveService.getLeaveBalance(empId);
    }
}
