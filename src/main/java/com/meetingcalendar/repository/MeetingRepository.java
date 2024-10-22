package com.meetingcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingcalendar.model.TimeSlot;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByTimeSlot(String timeSlot);
}
