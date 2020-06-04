package org.codecool.ccms.dao;

import org.codecool.ccms.models.Module;
import org.codecool.ccms.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends SQLDao implements IDao{

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


    public void insertAssignment(String name, String description, int moduleId) {
        String[] columns = {"name", "description", "moduleId"};
        String[] valuesRaw = {name, description, String.valueOf(moduleId)};
        String[] values = new String[valuesRaw.length];
        for (int i=0; i<values.length; i++) {
            values[i] = "'"+valuesRaw[i]+"'";
        }
        insertRecord("Assigment", columns, values);
    }
}
