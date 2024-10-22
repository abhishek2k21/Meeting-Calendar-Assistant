package com.meetingcalendar.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empID;

    private String empName;

    @ElementCollection
    private List<String> meetings;

    public Employee() {
        super();
    }

    public Employee(String empName, List<String> meetings) {
        this.empName = empName;
        this.meetings = meetings;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public List<String> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<String> meetings) {
        this.meetings = meetings;
    }
}
