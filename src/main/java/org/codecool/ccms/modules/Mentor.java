package org.codecool.ccms.modules;

public class Mentor extends Employee {

    public Mentor(int id, String name, String surname, String email, String password, Role role, Attendance attendance, int income){
        super(id, name, surname, email, password, role, attendance, income);
    }
}
