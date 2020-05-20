package org.codecool.ccms.modules;

public class Student extends User {
    private Module module;

    public Student(int id, String name, String surname, String email, String password, Role role, Attendance attendance, Module module){
        super(id ,name, surname, email, password, role, attendance);
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

    @Override
    public String[] toStringList() {
        String[] stringList = super.toStringList();
        String[] thisStringList = new String[stringList.length +1];
        thisStringList[thisStringList.length-1] = this.module.toString();
        return thisStringList;
    }
}
