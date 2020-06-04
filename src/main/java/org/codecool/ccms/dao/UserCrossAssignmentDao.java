package org.codecool.ccms.dao;

import org.codecool.ccms.models.Assignment;
import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCrossAssignmentDao extends SQLDao<Assignment> implements IDao<Assignment>{
    private final String[] columns = {"id", "userId", "assignmentId", "isPassed", "answer"};
    private final String table = "UserCrossAssignment";

    @Override
    protected String[] objectToArray(Assignment assignment) {
        String id = String.valueOf(assignment.getId());
        String name = assignment.getName();
        String module = assignment.getModule().toString();
        String content = assignment.getContent();
        String isPassed = assignment.getPassed().toString();
        return new String[]{id, name, module, content, isPassed};
    }

    @Override
    public void update(Assignment assignment) throws SQLException {
        String[] values = objectToArray(assignment);
        updateRecord(values);
    }


    @Override
    public void remove(Assignment assignment) {
        removeRecord(Integer.toString(assignment.getId()));
    }

    @Override
    public void insert(Assignment assignment) {
        String[] values = objectToArray(assignment);
        insertRecord(values);
    }

    @Override
    public List<Displayable> getObjects(String columnName, String columnValue) {
        return null;
    }

    private List<Displayable> getAssignments(String id) throws SQLException {
        String sqlStatement = "SELECT uca.id, name, answer, isPassed, moduleId " +
                "FROM UserCrossAssignment as uca LEFT JOIN Assignment" +
                " ON uca.assignmentId = Assignment.id WHERE uca.userId LIKE ?" +
                " AND uca.isPassed = 1;";

        List<Displayable> assignments = new ArrayList<>();
        connect();
            ResultSet resultSet = statement.executeQuery();
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

        return assignments;
    }

    public List<Displayable> getAllAssignments(){
        return getAssignments("SELECT uca.id, name, answer, isPassed, moduleId " +
                "FROM UserCrossAssignment as uca LEFT JOIN Assignment" +
                " ON uca.assignmentId = Assignment.id; ");
    }

    public List<Displayable> getPassedAssignmentsByStudentID(int studentId){
        return getAssignments("SELECT uca.id, name, answer, isPassed, moduleId " +
                "FROM UserCrossAssignment as uca LEFT JOIN Assignment" +
                " ON uca.assignmentId = Assignment.id WHERE uca.userId = " + studentId  +
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
