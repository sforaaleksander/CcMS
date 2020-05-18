package org.codecool.ccms.modules;

public class Student extends User {
    private Module module;

    public Student(int id, String name, String surname, String email, String password, Module module){
        super(id ,name, surname, email, password);
        this.module = module;
    }

    public Module getModule() {
        return module;
    }
}
