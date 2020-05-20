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
        String[] stringList = super.toStringList();
        String[] thisStringList = new String[stringList.length +1];
        thisStringList[thisStringList.length-1] = Integer.toString(this.getIncome());
        return thisStringList;
    }
}