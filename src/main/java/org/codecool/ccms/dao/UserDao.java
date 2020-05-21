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

    public List<Displayable> getStudentByName(String Name){
        return getUsers("SELECT * FROM User WHERE surname LIKE \'%" + Name + "%\' AND roleId = 4");
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
            statement.close();
            for (Displayable assigment: assignments) {
                int id = ((Assignment)assigment).getId();
                if (statement.executeQuery("SELECT * FROM UserCrossAssignment WHERE userId = " + user + " AND assignmentId = " + id + "").equals(null))
                    ((Assignment) assigment).setPassed(true);
            }

            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return assignments;
    }

    public List<Displayable> viewAllMentors(){
        return getUsers("SELECT * FROM User WHERE roleId = 2");
    }

    public List<Displayable> viewStudentsContact(){
        return getUsers("SELECT * FROM User WHERE roleId = 4");
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

    public void addAttendance(int userId, int workDayId){
        String[] columns = {"userId", "workDayID"};
        String[] values = { String.valueOf(userId), String.valueOf(workDayId)};
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
        insert("Assignment", columns, values);
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

    public WorkDay getWorkDayIdByDate(String findDate) {
        connect();
        String query = "SELECT * FROM WorkDay WHERE Date = '"+findDate+"';";
        WorkDay workDay = null;
        try {
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int id = results.getInt("id");
                String stringDate = results.getString("Date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
                formatter = formatter.withLocale(Locale.ENGLISH);
                LocalDate date = LocalDate.parse(stringDate, formatter);
                workDay = new WorkDay(date, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workDay;
    }
}
