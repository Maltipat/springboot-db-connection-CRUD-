package com.springboot_db_connection;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;
    private LocalDate joiningDate;

    private int leaveBalance = 20; // default leave balance
}
