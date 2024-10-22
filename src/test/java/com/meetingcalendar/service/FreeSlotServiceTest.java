package com.meetingcalendar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.meetingcalendar.model.Employee;
import com.meetingcalendar.repository.EmployeeRepository;
import com.meetingcalendar.service.impl.FreeSlotServiceImpl;

public class FreeSlotServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private FreeSlotServiceImpl freeSlotService;

    @BeforeEach
    public void setup() {
        // Initialize the mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFreeSlots() {
        // Assuming that the getFreeSlots method returns a list of free slots based on employee IDs
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        // Call the method and assert the expected outcome
        assertEquals(0, freeSlotService.getFreeSlots(1L, 2L).size());
    }
}
