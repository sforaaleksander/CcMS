package org.codecool.ccms.modules;

import org.codecool.ccms.dao.UserDao;

public class UserFactory {
    private UserDao userDao;

    public UserFactory(UserDao userDao) {
        this.userDao = userDao;
    }

    public User makeUser(int id, String name, String surname, String email,
                         String password, Role role, Attendance attendance) {

        //TODO make enums with roles
        switch (role.toString()){
            case "STUDENT":
                Module module = Module.ADVANCED;
                //TODO get student module
                Student student = new Student(id, name, surname, email, password, role, attendance, module);
                return student;

            case "MANAGER":
                int managerIncome = 0;
                //TODO get income
                User manager = new Manager(id, email, password, name, surname, role, attendance, managerIncome);
                return manager;

            case "MENTOR":
                int mentorIncome = 0;
                //TODO get income
                User mentor = new Mentor(id, email, password, name, surname, role, attendance, mentorIncome);
                return mentor;

            case "EMPLOYEE":
                int employeeIncome = 0;
                //TODO get income
                User employee = new Mentor(id, email, password, name, surname, role, attendance, employeeIncome);
                return employee;

            default:
                return null;
            }
    }
}

