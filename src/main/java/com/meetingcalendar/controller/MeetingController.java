package com.meetingcalendar.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.meetingcalendar.model.Employee;
import com.meetingcalendar.model.Meeting;
import com.meetingcalendar.service.MeetingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/calendar")
public class MeetingController {

    @Autowired
    private MeetingService calendarService;

    @PostMapping("/register/employee")
    public ResponseEntity<String> registerEmployee(@RequestParam("empName") String empName) {
        if (empName == null || empName.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee name cannot be empty");
        }

        Employee registerEmployee = calendarService.registerEmployee(empName);
        return ResponseEntity.ok("Employee Registered With ID=" + registerEmployee.getEmpID());
    }

    @PostMapping("/book/meeting")
    public ResponseEntity<String> bookMeeting(@Valid @RequestBody Meeting meeting, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.append(error.getDefaultMessage()).append("\n");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString().trim());
        }

        int result = calendarService.bookMeeting(meeting);
        switch (result) {
            case 0:
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Employee with ID=" + meeting.getEmployee() + " doesn't exist.");
            case 1:
                return ResponseEntity.ok("Meeting with Employee ID=" + meeting.getEmployee() + " booked on " + meeting.getDate() + " at " + meeting.getTime());
            case 2:
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Employee with ID=" + meeting.getEmployee() + " is already booked at this date & time.");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred. Please try again later.");
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployeesWithMeetings() {
        List<Employee> employees = calendarService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/freeSlots/{emp1}/{emp2}")
    public ResponseEntity<List<String>> getFreeSlots(@PathVariable("emp1") Long emp1, @PathVariable("emp2") Long emp2) {
        List<String> freeSlots = calendarService.getFreeSlots(emp1, emp2);
        return ResponseEntity.ok(freeSlots);
    }

    @GetMapping("/conflicts/{time}")
    public ResponseEntity<List<String>> getConflicts(@PathVariable("time") String time) {
        if (time == null || time.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<String> conflicts = calendarService.getConflicts(time);
        return ResponseEntity.ok(conflicts);
    }
}
