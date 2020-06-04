package org.codecool.ccms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLDao<T> {
    protected Connection connection;
    protected Statement statement;
    protected final String DB_NAME = "src/main/resources/cCMS_JAT.db";
    protected final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
    protected String[] columns;
    protected String table;


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

    private void executeQuery(PreparedStatement preparedStatement) throws SQLException {
        connect();
        preparedStatement.executeQuery();
        this.connection.close();
    }

    protected void updateRecord(String[] newValues) throws SQLException {
        String id = newValues[0];
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");

        for (String column : columns) {
            query.append(column).append(" = ?");
        }
        query.append(" WHERE Id = ").append(id).append(";");

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

        for (int i=1; i<=newValues.length; i++) {
            preparedStatement.setString(i, newValues[i]);
        }
        executeQuery(preparedStatement);
    }

    protected void removeRecord(String id) throws SQLException {
        String query = "DELETE FROM ?  WHERE Id =  ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, table);
        preparedStatement.setString(2, id);
        executeQuery(preparedStatement);
    }

    protected void insertRecord(String[] values) throws SQLException {
        String columnsString = " ( " + String.join(", " , columns) + " ) ";
        String query = "INSERT INTO " + table + columnsString + " VALUES ( ? ";
        for (int i=1; i<columns.length; i++){ query +=  ", ?"; }
        query += ")";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 1; i<= values.length; i++){
            preparedStatement.setString(i, values[i]);
        }
        executeQuery(preparedStatement);
    }

    protected abstract String[] objectToArray(T t);
}