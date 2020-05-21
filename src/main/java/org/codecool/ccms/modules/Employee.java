package org.codecool.ccms.modules;

public class Employee extends User {
    private int income;

    public Employee(int id,String name, String surname, String email, String password, Role role, Attendance attendance, int income){
        super(id, name, surname, email, password, role, attendance);
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    @Override
    public String[] toStringList() {
        return new String[]{String.valueOf(this.getId()), this.getFirstName(), this.getSurname(), this.getEmail()};
    }
}