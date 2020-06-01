package org.codecool.ccms.dao;


public interface IDao {
    String DB_NAME = "src/main/resources/cCMS_JAT.db";
    String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;


    void update(String table, String id, String column, String newValue);

    void remove(String table, String id);

    void insert(String table, String[] columns, String[] values);
}
