package org.codecool.ccms.modules;

public class Manager extends Employee {

    public Manager(int id, String name, String surname, String email, String password, Role role, Attendance attendance, int income){
        super(id, name, surname, email, password, role, attendance, income);
    }
}
