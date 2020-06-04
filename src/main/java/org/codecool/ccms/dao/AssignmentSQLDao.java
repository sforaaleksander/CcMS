package org.codecool.ccms.dao;

import org.codecool.ccms.models.Assignment;
import org.codecool.ccms.models.Module;
import org.codecool.ccms.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentSQLDao extends SQLDao<Assignment> implements IDao<Assignment> {

    public AssignmentSQLDao() {
        this.table = "Assignment";
        this.columns = new String[]{"name", "description", "moduleId"};
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
        insertRecord(objectToArray(assignment));
    }

    @Override
    public List getObjects(String columnName, String columnValue) {
        List<Assignment> assignments = new ArrayList<>();
        try {
            ResultSet resultSet = getRecords(columnName, columnValue);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Module module = Module.valueOf(resultSet.getInt("moduleId"));
                assignments.add(new Assignment(id, name, description, module, false));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return assignments;
    }

    @Override
    protected String[] objectToArray(Assignment assignment) {
            String id = Integer.toString(assignment.getId());
            String name = assignment.getName();
            String description = assignment.getContent();
            String moduleId = Integer.toString(assignment.getModule().getValue());
            return new String[]{id, name, description, moduleId};
    }
}
