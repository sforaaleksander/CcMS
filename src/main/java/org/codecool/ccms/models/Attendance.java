package org.codecool.ccms.models;

import java.time.LocalDate;

public class Attendance implements Displayable{
    private final String id;
    private final LocalDate date;
    private final String firstName;
    private final String surName;
    private final Role role;

    public Attendance(String id, LocalDate date, String firstName, String surName, Role role){
        this.id = id;
        this.date = date;
        this.firstName = firstName;
        this.surName = surName;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String[] toStringList() {
        String[] stringList = {this.date.toString(), this.firstName, this.surName, this.role.toString()};
        return stringList;
    }
}
