package org.codecool.ccms.modules;

import java.time.LocalDate;

public class Attendance implements Displayable{
    private final LocalDate date;
    private final String firstName;
    private final String surName;
    private final Role role;

    public Attendance(LocalDate date, String firstName, String surName, Role role){
        this.date = date;
        this.firstName = firstName;
        this.surName = surName;
        this.role = role;
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
