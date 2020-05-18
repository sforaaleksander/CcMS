package org.codecool.ccms.modules;

public class Employee extends User {
    private int income;

    public Employee(String name, String surname, String email, String password, Attendance attendance, int income){
        super(name, surname, email, password, attendance);
        this.income = income;
    }

    public int getIncome() {
        return income;
    }
}