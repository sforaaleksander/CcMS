package org.codecool.ccms;

import org.codecool.ccms.modules.Attendance;

public class Mentor extends Employee {

    public Mentor(String name, String surname, String email, String password, Attendance attendance, int income){
        super(name, surname, email, password, attendance, income);
    }
}
