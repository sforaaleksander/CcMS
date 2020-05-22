package org.codecool.ccms.dao;

import org.codecool.ccms.modules.Module;
import org.codecool.ccms.modules.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class UserDao extends Dao{

    private List<Displayable> getUsers(String query) {
        List<Displayable> users = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("first_name");
                String surname = results.getString("surname");
                String email = results.getString("email");
                String password = results.getString("password");
                Role role = Role.valueOf(results.getInt("roleId"));
                //TODO read attendance from DB
                Attendance attendance = null;

                //TODO create user builder(?)
                User user = new UserFactory(this).makeUser(id, firstName, surname, email, password, role, attendance);
                users.add(user);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void passAssignment(int id){
            connect();
            try {
                statement.executeUpdate("UPDATE UserCrossAssignment SET isPassed = 1 WHERE id = " + id);
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void updateAssignment(int id, int user, String answer){
        connect();
        try {
            statement.executeUpdate("INSERT INTO UserCrossAssignment (userId, assignmentId, answer) " +
                                        "VALUES ("+user+", " + id + ", '" + answer+"')");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Displayable> getAssignments(String query){
        List<Displayable> assignments = new ArrayList<>();
        connect();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String answer = resultSet.getString("answer");
                boolean isPassed = resultSet.getBoolean("isPassed");
                String name = resultSet.getString("name");
                Module module = Module.valueOf(resultSet.getInt("moduleId"));
                assignments.add(new Assignment(id, name, answer, module, isPassed));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return assignments;
    }

    public List<Displayable> getAllAssignments(){
        return getAssignments("SELECT uca.id, name, answer, isPassed, moduleId " +
                "FROM UserCrossAssignment as uca LEFT JOIN Assigment" +
                " ON uca.assignmentId = Assigment.id; ");
    }

    public List<Displayable> getPassedAssignmentsByStudentID(int studentId){
        return getAssignments("SELECT uca.id, name, answer, isPassed, moduleId " +
                "FROM UserCrossAssignment as uca LEFT JOIN Assigment" +
                " ON uca.assignmentId = Assigment.id WHERE uca.userId = " + studentId  +
                " AND uca.isPassed = 1;");
    }


        public List<Displayable> getStudentByName(String Name){
        return getUsers("SELECT * FROM User WHERE surname LIKE '%" + Name + "%' AND roleId = 4");
    }

    public List<Displayable> getUserBy(String columnName, String value) {
        return getUsers(
                "SELECT * FROM User WHERE " + columnName + " = '" + value + "';");
    }

    public List<Displayable> getGradesByStudentId(int user){
        List<Displayable> assignments = new ArrayList<>();
        connect();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Assigment");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Module module = Module.valueOf(resultSet.getInt("moduleId"));
                boolean isPassed = false;
                assignments.add(new Assignment(id, description, name, module, isPassed));
            }
            resultSet.close();
            statement.close();
            for (Displayable assignment: assignments) {
                int id = ((Assignment)assignment).getId();
                if (statement.executeQuery("SELECT * FROM UserCrossAssignment WHERE userId = " + user + " AND assignmentId = " + id).equals(null))
                    ((Assignment) assignment).setPassed(true);
            }
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return assignments;
    }

    public List<Displayable> viewUsersByRoleId(int roleId){
        return getUsers("SELECT * FROM User WHERE roleId = '" + roleId + "'");
    }


    public void removeUser(int id, int roleId) {
        connect();
        try {
            statement.executeUpdate("DELETE FROM User WHERE id = '" + id + "' AND roleId = '" + roleId + "'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddUser(String name, String surname, String email, String password, String roleId) {
        insertUser(new String[]{name, surname, email, password, roleId});
    }

    public void addAttendance(int userId, WorkDay workDay){
        String[] columns = {"userId", "workDayID"};
        String[] values = { String.valueOf(userId), workDay.getDate().toString()};
        insert("Attendance", columns, values);
    }

    public void addWorkDay(String date){
        String[] value = {date};
        String[] columns = {"date"};
        insert("WorkDay", columns, value);
    }

    public void insertAssignment(String name, String description, int moduleId) {
        String[] columns = {"name", "description", "moduleId"};
        String[] valuesRaw = {name, description, String.valueOf(moduleId)};
        String[] values = new String[valuesRaw.length];
        for (int i=0; i<values.length; i++) {
            values[i] = "'"+valuesRaw[i]+"'";
        }
        insert("Assigment", columns, values);
    }

    public void insertUser(String[] values) {
        String[] columns = { "first_name", "surname", "email", "password", "roleId" };

        for (int i = 0; i < 5; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        insert("User", columns, values);
    }

    public void updateUser(String id, String column, String newValue) {
        newValue = String.format("'%s'", newValue);
        update("User", id, column, newValue);
    }

    public WorkDay getWorkDay(String value) {
        connect();
        String query = "SELECT * FROM WorkDay WHERE date = '" +value+ "';";

        WorkDay workDay = null;
        try {
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String stringDate = results.getString("date");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                formatter = formatter.withLocale(Locale.ENGLISH);
                LocalDate date = LocalDate.parse(stringDate, formatter);

                workDay = new WorkDay(date);

            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workDay;
    }

    public Module getStudentModuleBasedOnPassedAssignments(int studentId) {
        List<Displayable> assignmentsAsDisplayable = getAllAssignments();

        List<Assignment> assignments = assignmentsAsDisplayable
                            .stream()
                            .map(obj -> (Assignment) obj).collect(Collectors.toList());

        int basicAssgn = (int) assignments.stream().filter(assignment -> assignment.getModule().toString().equals(Module.PROGBASIC.toString())).count();
        int javaAssgn = (int) assignments.stream().filter(assignment -> assignment.getModule().toString().equals(Module.JAVA.toString())).count();
        int webAssgn = (int) assignments.stream().filter(assignment -> assignment.getModule().toString().equals(Module.WEB.toString())).count();

        int passedAssgn = getPassedAssignmentsByStudentID(studentId).size();

        if (passedAssgn > basicAssgn + javaAssgn + webAssgn) {
            return Module.ADVANCED;
        }
        if (passedAssgn > basicAssgn + javaAssgn) {
            return Module.WEB;
        }
        if (passedAssgn > basicAssgn) {
            return Module.JAVA;
        } else{
            return Module.PROGBASIC;
        }
        }



    public void removeAttendance(int studentID, WorkDay workDay) {
        connect();
        String date = workDay.getDate().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        try {
            statement.executeUpdate("DELETE FROM Attendance WHERE userId = '" + studentID + "' AND workDayId = '" + date + "'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
