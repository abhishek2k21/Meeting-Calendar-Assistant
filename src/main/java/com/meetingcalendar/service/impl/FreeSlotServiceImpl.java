package com.meetingcalendar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meetingcalendar.model.Employee;
import com.meetingcalendar.repository.EmployeeRepository;
import com.meetingcalendar.service.FreeSlotService;

@Service
public class FreeSlotServiceImpl implements FreeSlotService {

    @Autowired
    private EmployeeRepository employeeRepository;

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
}
