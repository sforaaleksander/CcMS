package org.codecool.ccms.modules;

import org.codecool.ccms.dao.UserDao;

public class UserFactory {
    private UserDao userDao;

    public UserFactory(UserDao userDao) {
        this.userDao = userDao;
    }

    public User makeUser(int id, String name, String surname, String email, String password, int roleId) {

        //TODO make enums with roles
        switch (roleId){
            case 1:
                Module module = Module.ADVANCED;
                //TODO get student module
                User student = new Student(id, name, surname, email, password, roleId, module);
                return student;

            case 2:
                int managerIncome = 0;
                //TODO get income
                User manager = new Manager(id, email, password, name, surname, roleId, managerIncome);
                return manager;

            case 3:
                int mentorIncome = 0;
                //TODO get income
                User mentor = new Mentor(id, email, password, name, surname, roleId, mentorIncome);
                return mentor;

            case 4:
                int employeeIncome = 0;
                //TODO get income
                User employee = new Mentor(id, email, password, name, surname, roleId, employeeIncome);
                return employee;

            default:
                return null;
            }
    }
}

