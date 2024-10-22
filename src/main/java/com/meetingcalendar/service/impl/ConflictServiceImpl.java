package com.meetingcalendar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingcalendar.model.TimeSlot;
import com.meetingcalendar.repository.MeetingRepository;
import com.meetingcalendar.service.ConflictService;

@Service
public class ConflictServiceImpl implements ConflictService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public List<String> getConflicts(String time) {
        List<TimeSlot> conflictingSlots = meetingRepository.findByTimeSlot(time);
        List<String> conflicts = new ArrayList<>();
        
        for (TimeSlot slot : conflictingSlots) {
            conflicts.addAll(slot.getParticipants()); // Aggregate participants from each TimeSlot
        }
        return conflicts; // Return the list of conflicts (participants)
    }
}
