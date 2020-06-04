package org.codecool.ccms.models;

import org.codecool.ccms.dao.UserCrossAssignmentSQLDao;

public class UserFactory {

    public UserFactory(){
    }

    public User makeUser(int id, String name, String surname, String email,
                         String password, Role role, byte[] salt) {

        switch (role.toString()){
            case "STUDENT":
                Module module = new UserCrossAssignmentSQLDao().getStudentModuleBasedOnPassedAssignments(id);
                Student student = new Student(id, name, surname, email, password, role, salt, module);
                return student;

            case "MANAGER":
                int managerIncome = 0;
                //TODO get income
                User manager = new Manager(id, name, surname, email, password, role, salt, managerIncome);
                return manager;

            case "MENTOR":
                int mentorIncome = 0;
                //TODO get income
                User mentor = new Mentor(id, name, surname, email, password, role, salt, mentorIncome);
                return mentor;

            case "EMPLOYEE":
                int employeeIncome = 0;
                //TODO get income
                User employee = new Mentor(id, name, surname, email, password, role, salt, employeeIncome);
                return employee;

            default:
                return null;
            }
    }
}

