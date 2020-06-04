package org.codecool.ccms.dao;
import org.codecool.ccms.models.Displayable;
import java.util.List;

public interface IDao<T> {

    void update(T object);

    void remove(T object);

    void insert(T object);

    List<Displayable> getObjects(String columnName, String columnValue);
}
