package org.codecool.ccms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLDao implements IDao {
    protected Connection connection;
    protected Statement statement;
    protected final String DB_NAME = "src/main/resources/cCMS_JAT.db";
    protected final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public void connect() {
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


    protected void executeUpdate(String table, String[] columns, String[] newValues) throws SQLException {
        String id = newValues[0];
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");

        for (String column : columns) {
            query.append(column).append(" = ?");
        }
        query.append(" WHERE Id = ").append(id).append(";");

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

        for (int i=0; i<newValues.length; i++) {
            preparedStatement.setString(i, newValues[i]);
        }
        preparedStatement.executeUpdate();
    }

    protected void executeRemove(String table, String id) {
        String query = "DELETE FROM " + table + " WHERE Id = " + id + ";";
        executeQuery(query);
    }

    protected void executeInsert(String table, String[] columns, String[] values){
        String query = "INSERT INTO " + table
                + " ( " + String.join(", " , columns) + " ) "
                + " VALUES " + " ( " + String.join(", " , values) + " );";
        executeQuery(query);
    }
}