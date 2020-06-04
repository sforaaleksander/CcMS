package org.codecool.ccms.dao;

import java.sql.*;
import java.util.List;

public abstract class SQLDao<T> {
    protected Connection connection;
    protected PreparedStatement statement;
    protected final String DB_NAME = "src/main/resources/cCMS_JAT.db";
    protected final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
    protected String[] columns;
    protected String table;

    protected ResultSet executeQuery(String query, String[] parameters) {
        ResultSet resultSet = null;
        try {
            createStatement(query);
            updateParameters(parameters);
            this.statement.execute();
            resultSet = this.statement.getResultSet();
            this.connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Call sqldblite engineers, there is nothing you can do :)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't connect to database, check it's availability or call support");
        }
        return resultSet;
    }

    private void createStatement(String query) throws ClassNotFoundException, SQLException {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.prepareStatement(query);
    }

    private void updateParameters(String[] parameters) throws SQLException {
        for (int i = 1; i<= parameters.length; i++){
            this.statement.setString(i, parameters[i]);
        }
    }

    protected void updateRecord(String[] newValues) {
        String id = newValues[0];
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");
        for (String column : columns) { query.append(column).append(" = ?"); }
        query.append(" WHERE Id = ").append(id).append(";");
        executeQuery(query.toString(), newValues);
    }

    protected void removeRecord(String id) {
        String query = "DELETE FROM ?  WHERE Id =  ? ";
        executeQuery(query, new String[]{this.table, id});
    }

    protected void insertRecord(String[] values) {
        String columnsString = " ( " + String.join(", " , columns) + " ) ";
        StringBuilder query = new StringBuilder("INSERT INTO " + table + columnsString + " VALUES ( ? ");
        for (int i=1; i<columns.length; i++){ query.append(", ?"); }
        query.append(")");
        executeQuery(query.toString(), values);
    }

    protected  ResultSet getRecords(String column, String value){
        String query = "SELECT * FROM ? WHERE ? LIKE ?";
        String[] parameters = {this.table, column, value};
        return executeQuery(query, parameters);
    }

    protected abstract String[] objectToArray(T t);
}