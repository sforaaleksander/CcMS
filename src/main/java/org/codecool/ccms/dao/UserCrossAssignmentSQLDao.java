package org.codecool.ccms.dao;

import org.codecool.ccms.models.Assignment;
import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCrossAssignmentSQLDao extends SQLDao<Assignment> implements IDao<Assignment>{
    public UserCrossAssignmentSQLDao() {
        this.table = "UserCrossAssignment";
        this.columns = new String[]{"id", "userId", "assignmentId", "isPassed", "answer"};
    }


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
    public void update(Assignment assignment) {
        updateRecord(objectToArray(assignment));
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
    public List<Assignment> getObjects(String columnName, String columnValue) {
        return getAssignments(columnValue);
    }

    // TODO pass assigment
//    public void passAssignment(int id){
//        createStatement();
//        try {
//            statement.executeUpdate("UPDATE UserCrossAssignment SET isPassed = 1 WHERE id = " + id);
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    // TODO get grades by student
//    public List<Assignment> getGradesByStudentId(int user){
//        List<Displayable> assignments = new ArrayList<>();
//        createStatement();
//        try {
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM Assigment");
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String description = resultSet.getString("description");
//                Module module = Module.valueOf(resultSet.getInt("moduleId"));
//                boolean isPassed = false;
//                assignments.add(new Assignment(id, description, name, module, isPassed));
//            }
//            resultSet.close();
//            statement.close();
//            for (Displayable assignment: assignments) {
//                int id = ((Assignment)assignment).getId();
//                if (statement.executeQuery("SELECT * FROM UserCrossAssignment WHERE userId = " + user + " AND assignmentId = " + id).equals(null))
//                    ((Assignment) assignment).setPassed(true);
//            }
//            connection.close();
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//        return assignments;
//    }

    private List<Assignment> getAssignments(String userId) {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT uca.id, name, answer, isPassed, moduleId " +
                "FROM UserCrossAssignment as uca LEFT JOIN Assignment" +
                " ON uca.assignmentId = Assignment.id WHERE uca.userId LIKE ?" +
                " AND uca.isPassed = 1;";
        try {
            ResultSet resultSet = executeQuery(query, new String[]{userId});
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String answer = resultSet.getString("answer");
                boolean isPassed = resultSet.getBoolean("isPassed");
                String name = resultSet.getString("name");
                Module module = Module.valueOf(resultSet.getInt("moduleId"));
                assignments.add(new Assignment(id, name, answer, module, isPassed));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return assignments;
    }

    public List<Assignment> getAllAssignments(){
        return getAssignments("%");
    }

    public List<Assignment> getPassedAssignmentsByStudentID(int studentId){
        return getAssignments(Integer.toString(studentId));
    }

    public Module getStudentModuleBasedOnPassedAssignments(int studentId) {
        List<Assignment> assignments = getAllAssignments()
                .stream()
                .collect(Collectors.toList());

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
