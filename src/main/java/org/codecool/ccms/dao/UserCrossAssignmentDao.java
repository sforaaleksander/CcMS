package org.codecool.ccms.dao;

import org.codecool.ccms.models.Assignment;
import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCrossAssignmentDao extends SQLDao {
    private final String[] columns = {"id", "userId", "assignmentId", "isPassed", "answer"};

    @Override
    public void update(String ... values) {
        executeUpdate("UserCrossAssignment", id, column, newValue);
    }


    @Override
    public void remove(String id) {
        executeRemove("UserCrossAssignment", id);
    }

    @Override
    public void insert(String... values) {
        String[] columns = {"userId", "assignmentId", "answer"};
        executeInsert("UserCrossAssignment", columns, values);
    }

    @Override
    public List<Displayable> getObjects(String columnName, String columnValue) {
        return null;
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
}
