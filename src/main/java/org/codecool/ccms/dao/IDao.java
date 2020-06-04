package org.codecool.ccms.dao;


import org.codecool.ccms.models.Displayable;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    void update(T object) throws SQLException;

    void remove(T object) throws SQLException;

    void insert(T object);

    List<T> getObjects(String columnName, String columnValue);
}
