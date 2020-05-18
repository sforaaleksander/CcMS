package org.codecool.ccms;

import org.codecool.ccms.modules.Attendance;
import org.codecool.ccms.modules.User;

public class Employee extends User {
    private int income;

    public Employee(String name, String surname, String email, String password, Attendance attendance, int income){
        super(name, surname, email, password, attendance);
        this.income = income;
    }
}