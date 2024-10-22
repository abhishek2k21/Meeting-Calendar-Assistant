package com.meetingcalendar.service;

import java.text.ParseException;
import java.util.List;

import com.meetingcalendar.model.Employee;
import com.meetingcalendar.model.Meeting;

public interface MeetingService {
    Employee registerEmployee(String empName);

    int bookMeeting(Meeting meeting) throws ParseException;

    List<Employee> getEmployees();

    List<String> getFreeSlots(Long emp1, Long emp2);

    List<String> getConflicts(String time);
}
