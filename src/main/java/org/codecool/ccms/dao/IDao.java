package org.codecool.ccms.dao;


import org.codecool.ccms.models.Displayable;

import java.sql.SQLException;
import java.util.List;

public interface IDao {

    void update(String ... values) throws SQLException;

    void remove(String id);

    void insert(String... values);

    List<Displayable> getObjects(String columnName, String columnValue);
}
