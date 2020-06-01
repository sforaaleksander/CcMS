package org.codecool.ccms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao implements IDao {
    protected Connection connection;
    protected Statement statement;

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database" + e.getMessage());
        }
    }

    private void executeQuery(String  query){
        connect();
        try {
            statement.execute(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(String table, String id, String column, String newValue) {
        if (column.toLowerCase().equals("id")) {
            System.out.println("You can't change id");
            return;
        }
        String query = "UPDATE " + table + " SET " + column + " = " + newValue + " WHERE Id = " + id + ";";
        executeQuery(query);
    }

    public void remove(String table, String id) {
        String query = "DELETE FROM " + table + " WHERE Id = " + id + ";";
        executeQuery(query);
    }

    public void insert(String table, String[] columns, String[] values){
        String query = "INSERT INTO " + table
                + " ( " + String.join(", " , columns) + " ) "
                + " VALUES " + " ( " + String.join(", " , values) + " );";
        executeQuery(query);
    }
}