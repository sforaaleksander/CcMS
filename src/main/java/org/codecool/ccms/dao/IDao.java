package org.codecool.ccms.dao;


import org.codecool.ccms.models.Displayable;

import java.util.List;

public interface IDao {

    void update(String ... values);

    void remove(String id);

    void insert(String... values);

    List<Displayable> getObjects(String columnName, String columnValue);
}
