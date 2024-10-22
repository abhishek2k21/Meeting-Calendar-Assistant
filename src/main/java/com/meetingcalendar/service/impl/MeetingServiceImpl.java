package com.meetingcalendar.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingcalendar.model.Employee;
import com.meetingcalendar.model.Meeting;
import com.meetingcalendar.model.TimeSlot;
import com.meetingcalendar.repository.EmployeeRepository;
import com.meetingcalendar.repository.MeetingRepository;
import com.meetingcalendar.service.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public Employee registerEmployee(String empName) {
        Employee employee = new Employee();
        employee.setEmpName(empName);
        return employeeRepository.save(employee);
    }

    @Override
    public int bookMeeting(Meeting meeting) throws ParseException {
        Optional<Employee> employeeOpt = employeeRepository.findById(meeting.getEmployee()); // Now using Long directly
        if (employeeOpt.isEmpty()) {
            return 0; // Employee not found
        }

        Employee employee = employeeOpt.get();
        String dateTime = meeting.getDate() + " " + meeting.getTime();
        if (employee.getMeetings() != null && employee.getMeetings().contains(dateTime)) {
            return 2; // Meeting conflict
        }

        List<String> employeeMeetings = employee.getMeetings() != null ? employee.getMeetings() : new ArrayList<>();
        employeeMeetings.add(dateTime);
        employee.setMeetings(employeeMeetings);
        employeeRepository.save(employee);

        // Also, save in the meeting repository
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setTimeSlot(dateTime);
        timeSlot.setParticipants(List.of(String.valueOf(meeting.getEmployee()))); // Convert Long to String
        meetingRepository.save(timeSlot);

        return 1; // Meeting booked successfully
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<String> getFreeSlots(Long emp1, Long emp2) {
        Optional<Employee> employee1 = employeeRepository.findById(emp1);
        Optional<Employee> employee2 = employeeRepository.findById(emp2);

        List<String> freeSlots = new ArrayList<>();
        if (employee1.isPresent() && employee2.isPresent()) {
            List<String> emp1Meetings = employee1.get().getMeetings();
            List<String> emp2Meetings = employee2.get().getMeetings();

            if (emp1Meetings != null && emp2Meetings != null) {
                for (String meeting : emp1Meetings) {
                    if (!emp2Meetings.contains(meeting)) {
                        freeSlots.add(meeting);
                    }
                }
            }
        }
        return freeSlots;
    }

    @Override
    public List<String> getConflicts(String time) {
        List<TimeSlot> conflictingSlots = meetingRepository.findByTimeSlot(time);
        List<String> conflicts = new ArrayList<>();
        for (TimeSlot slot : conflictingSlots) {
            conflicts.addAll(slot.getParticipants());
        }
        return conflicts;
    }
}
