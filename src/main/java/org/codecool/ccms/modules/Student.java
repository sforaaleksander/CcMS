package org.codecool.ccms.modules;

public class Student extends User {
    private final Module module;

    public Student(int id, String name, String surname, String email, String password, Role role, Attendance attendance, Module module){
        super(id ,name, surname, email, password, role, attendance);
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

    @Override
    public String[] toStringList() {
        return new String[]{String.valueOf(this.getId()), this.getFirstName(), this.getSurname(), this.getEmail()};
    }
}
