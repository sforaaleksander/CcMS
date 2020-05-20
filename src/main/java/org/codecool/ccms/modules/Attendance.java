package org.codecool.ccms.modules;

import java.time.LocalDateTime;

public class Attendance implements Displayable{
    private LocalDateTime date;
    private String firstName;
    private String surName;
    private Role role;

    public Attendance(LocalDateTime date, String firstName, String surName, Role role){
        this.date = date;
        this.firstName = firstName;
        this.surName = surName;
        this.role = role;
    }

    public LocalDateTime getDate() {
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
