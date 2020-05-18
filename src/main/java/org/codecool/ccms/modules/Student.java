package org.codecool.ccms.modules;

public class Student extends User {
    private Module module;

    public Student(String name, String surname, String email, String password, Attendance attendance, Module module){
        super(name, surname, email, password, attendance);
        this.module = module;
    }
}
