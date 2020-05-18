package org.codecool.ccms.modules;

public class Employee extends User {
    private int income;

    public Employee(int id,String name, String surname, String email, String password, int income){
        super(id, name, surname, email, password);
        this.income = income;
    }

    public int getIncome() {
        return income;
    }
}