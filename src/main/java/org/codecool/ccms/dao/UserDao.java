package org.codecool.ccms.dao;

import org.codecool.ccms.models.Module;
import org.codecool.ccms.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class UserDao extends Dao implements IDao{

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

//    public void updateAssignment(int id, int user, String answer){
//        connect();
//        try {
//            statement.executeUpdate("INSERT INTO UserCrossAssignment (userId, assignmentId, answer) " +
//                                        "VALUES ("+user+", " + id + ", '" + answer+"')");
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


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
        executeInsert("Attendance", columns, values);
    }

    public void addWorkDay(String date){
        String[] value = {date};
        String[] columns = {"date"};
        executeInsert("WorkDay", columns, value);
    }

    public void insertAssignment(String name, String description, int moduleId) {
        String[] columns = {"name", "description", "moduleId"};
        String[] valuesRaw = {name, description, String.valueOf(moduleId)};
        String[] values = new String[valuesRaw.length];
        for (int i=0; i<values.length; i++) {
            values[i] = "'"+valuesRaw[i]+"'";
        }
        executeInsert("Assigment", columns, values);
    }

    public void insertUser(String[] values) {
        String[] columns = { "first_name", "surname", "email", "password", "roleId" };

        for (int i = 0; i < 5; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        executeInsert("User", columns, values);
    }

    public void updateUser(String id, String column, String newValue) {
        newValue = String.format("'%s'", newValue);
        executeUpdate("User", id, column, newValue);
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

    @Override
    public void update(String id, String columnName, String value) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void insert(String... values) {

    }

    @Override
    public List<Displayable> getObjects(String columnName, String columnValue) {
        return null;
    }
}
