package com.meetingcalendar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetingcalendar.model.Meeting;
import com.meetingcalendar.repository.EmployeeRepository;
import com.meetingcalendar.service.MeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any; 

@ExtendWith(MockitoExtension.class)
public class MeetingControllerTests {
    @Mock
    private MeetingService meetingService;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private MeetingController meetingController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(meetingController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testBookMeeting() throws Exception {
        Meeting meeting = new Meeting();
        meeting.setEmployee(1L); 
        meeting.setDate("15/10/2024"); 
        meeting.setTime("10:00");

//        when(employeeRepository.existsById(meeting.getEmployee())).thenReturn(true);
        when(meetingService.bookMeeting(any(Meeting.class))).thenReturn(1); 

        mockMvc.perform(post("/calendar/book/meeting")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(meeting)))
                .andExpect(status().isOk());
    }
}
