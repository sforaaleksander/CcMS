package org.codecool.ccms.models;

public class Employee extends User {
    private final int income;

    public Employee(int id,String name, String surname, String email, String password, Role role, byte[] salt, int income){
        super(id, name, surname, email, password, role, salt);
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