package org.codecool.ccms.dao;


import org.codecool.ccms.models.Displayable;

import java.util.List;

public interface IDao {
    String DB_NAME = "src/main/resources/cCMS_JAT.db";
    String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;


    void update(String id, String... values);

    void remove(String id);

    void insert(String... values);

    List<Displayable> getObjects(String columnName, String columnValue);
}
