package org.codecool.ccms;

import org.codecool.ccms.modules.Attendance;

public class Manager extends Employee {

    public Manager(String name, String surname, String email, String password, Attendance attendance, int income){
        super(name, surname, email, password, attendance, income);
    }
}
