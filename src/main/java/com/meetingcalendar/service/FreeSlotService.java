package com.meetingcalendar.service;

import java.util.List;

public interface FreeSlotService {
    List<String> getFreeSlots(Long emp1, Long emp2);
}
