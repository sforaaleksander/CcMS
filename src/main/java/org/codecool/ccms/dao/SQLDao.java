package org.codecool.ccms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SQLDao<T> {
    protected Connection connection;
    protected PreparedStatement statement;
    protected final String DB_NAME = "src/main/resources/cCMS_JAT.db";
    protected final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
    protected String[] columns;
    protected String table;

    private void executeQuery(String query, String[] parameters) {
        try {
            createStatement(query);
            updateParameters(parameters);
            this.statement.execute();
            this.connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Call sqldblite engineers, there is nothing you can do :)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't connect to database, check it's availability or call support");
        }
    }

    public void createStatement(String query) throws ClassNotFoundException, SQLException {
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
        String query = "INSERT INTO " + table + columnsString + " VALUES ( ? ";
        for (int i=1; i<columns.length; i++){ query +=  ", ?"; }
        query += ")";
        executeQuery(query, values);
    }

    protected abstract String[] objectToArray(T t);
}