package org.codecool.ccms.dao;

import org.codecool.ccms.modules.*;
import org.codecool.ccms.modules.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public List<Displayable> getUserBy(String columnName, String value) {
        return getUsers(
                "SELECT * FROM User WHERE " + columnName + " = '" + value + "';");
    }

    public List<Displayable> getGradesByStudentId(int user){
        List<Displayable> assignments = new ArrayList<>();
        connect();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT asg.id, asg.name, asg.description, Module.id as moduleId, uca.isPassed FROM UserCrossAssignment as uca" +
                                                            " LEFT JOIN Assigment as asg ON asg.id = uca.assignmentId " +
                                                            " LEFT JOIN Module ON Module.id = asg.moduleId " +
                                                            " WHERE uca.id = '" + user + "'" );
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Module module = Module.valueOf(resultSet.getInt("moduleId"));
                boolean isPassed = resultSet.getBoolean("isPassed");
                assignments.add(new Assignment(id, description, name, module, isPassed));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return assignments;
    }

    public List<Displayable> viewAllMentors(){
        return getUsers("SELECT id, first_name, surname, email FROM User WHERE roleId = 2");
    }

    public void removeMentor(int id) {
        connect();
        try {
            statement.executeQuery("DELETE FROM User WHERE id = '" + id + "' AND roleId = 2");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddMentor(String name, String surname, String email, String password, String roleId) {
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

    public void insertAssignment(String name, String description) {
        String[] columns = {"name", "description", "isPassed"};
        String[] values = {name, description, "0"};
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
        WorkDay workDay = null;
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM WorkDay WHERE Date = '"+findDate+"';");
            while (results.next()) {
                int id = results.getInt("id");
                String sDate = results.getString("Date");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
                workDay = new WorkDay(date, id);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return workDay;
    }
}
