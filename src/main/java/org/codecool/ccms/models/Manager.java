package org.codecool.ccms.models;

public class Manager extends Employee {

    public Manager(int id, String name, String surname, String email, String password, Role role, byte[] salt, int income){
        super(id, name, surname, email, password, role, salt, income);
    }
}
