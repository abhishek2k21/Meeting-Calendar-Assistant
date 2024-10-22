package com.meetingcalendar.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.meetingcalendar.model.Employee;
import com.meetingcalendar.model.Meeting;
import com.meetingcalendar.repository.EmployeeRepository;
import com.meetingcalendar.repository.MeetingRepository;
import com.meetingcalendar.service.impl.MeetingServiceImpl;

public class MeetingServiceTests {

    @InjectMocks
    private MeetingServiceImpl meetingService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private MeetingRepository meetingRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookMeeting_EmployeeNotFound() throws ParseException {
        Meeting meeting = new Meeting();
        meeting.setEmployee(1L); // Change to Long
        meeting.setDate("15/10/2024");
        meeting.setTime("10:00");

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        int result = meetingService.bookMeeting(meeting);
        assertEquals(0, result);
    }

    @Test
    public void testBookMeeting_Conflict() throws ParseException {
        // Setup employee with existing meetings
        Employee employee = new Employee();
        employee.setEmpID(1L);
        employee.setMeetings(List.of("15/10/2024 10:00"));

        Meeting meeting = new Meeting();
        meeting.setEmployee(1L); // Change to Long
        meeting.setDate("15/10/2024");
        meeting.setTime("10:00");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        int result = meetingService.bookMeeting(meeting);
        assertEquals(2, result); // Conflict expected
    }
}
