package com.meetingcalendar.service;

import java.util.List;

public interface ConflictService {
    List<String> getConflicts(String time);
}
