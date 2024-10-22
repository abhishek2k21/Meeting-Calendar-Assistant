package com.meetingcalendar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.meetingcalendar.model.TimeSlot;
import com.meetingcalendar.repository.MeetingRepository;
import com.meetingcalendar.service.impl.ConflictServiceImpl;

public class ConflictServiceTest {

    @Mock
    private MeetingRepository meetingRepository;

    @InjectMocks
    private ConflictServiceImpl conflictService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetConflicts_NoConflicts() {
        when(meetingRepository.findByTimeSlot("10:00 AM")).thenReturn(Collections.emptyList());
        List<String> conflicts = conflictService.getConflicts("10:00 AM");
        assertEquals(0, conflicts.size());
    }

    @Test
    public void testGetConflicts_WithConflicts() {
        List<String> participants1 = List.of("Alice", "Bob");
        List<String> participants2 = List.of("Charlie");

        TimeSlot timeSlot1 = new TimeSlot("10:00 AM", participants1);
        TimeSlot timeSlot2 = new TimeSlot("10:00 AM", participants2);

        when(meetingRepository.findByTimeSlot("10:00 AM")).thenReturn(List.of(timeSlot1, timeSlot2));

        List<String> conflicts = conflictService.getConflicts("10:00 AM");
        assertEquals(3, conflicts.size()); // 2 from timeSlot1 and 1 from timeSlot2
    }
}
