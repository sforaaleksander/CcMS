package org.codecool.ccms.dao;

import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.User;

import java.sql.SQLException;
import java.util.List;

public class userSQLDao extends SQLDao implements IDao<User> {
    @Override
    public void update(User object) throws SQLException {
        // TODO: updates using SQLDao methods

    }

    @Override
    public void remove(User object) {
        // TODO: updates using SQLDao methods
    }

    @Override
    public void insert(User object) {
        // TODO: updates using SQLDao methods
    }

    @Override
    public List<Displayable> getObjects(String columnName, String columnValue) {
        // TODO: select using sqldao
        return null;
    }
}


