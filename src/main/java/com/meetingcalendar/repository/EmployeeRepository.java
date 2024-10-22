package com.meetingcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meetingcalendar.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
