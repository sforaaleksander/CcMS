package org.codecool.ccms.dao;


public interface IDao {
    String DB_NAME = "src/main/resources/cCMS_JAT.db";
    String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;


    void update(String id, String... values);

    void remove(String id);

    void insert(String... values);
}
