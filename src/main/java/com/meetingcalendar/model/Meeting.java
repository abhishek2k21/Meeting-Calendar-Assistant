package com.meetingcalendar.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Meeting {

    @NotNull(message = "Enter Employee ID")
    private Long employee; // Employee ID as Long

    @NotNull(message = "Enter The Meeting Date")
    @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$", message = "Enter Date In Correct Format (dd/MM/yyyy)")
    private String date; // Keep as String for regex validation

    @NotNull(message = "Enter The Meeting Time")
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Enter Time In 24 Hour Format")
    private String time; // Time as String in 24-hour format

    public Meeting() {
        super();
    }

    public Meeting(Long employee, String date, String time) {
        this.employee = employee;
        this.date = date;
        this.time = time;
    }

    public Long getEmployee() {
        return employee; // Getter for employee
    }

    public void setEmployee(Long employee) {
        this.employee = employee; // Setter for employee
    }

    public String getDate() {
        return date; // Getter for date
    }

    public void setDate(String date) {
        this.date = date; // Setter for date
    }

    public String getTime() {
        return time; // Getter for time
    }

    public void setTime(String time) {
        this.time = time; // Setter for time
    }
}
