package org.codecool.ccms.dao;

import org.codecool.ccms.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSQLDao extends SQLDao<User> implements IDao<User> {

    public UserSQLDao() {
        this.table = "User";
        this.columns = new String[]{"id", "first_name", "surname", "email", "passwordHash", "passwordSalt", "roleId"};
    }

    protected String[] objectToArray(User user){
        String id = Integer.toString(user.getId());
        String firstName = user.getFirstName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String passwordHash = user.getPassword();  // change password into hash
        String passwordSalt = user.getSalt().toString();
        String role = user.getRole().toString();
        return new String[]{id, firstName, surname, email, passwordHash, passwordSalt, role};
    }

    @Override
    public void update(User user) {
        updateRecord(objectToArray(user));
    }

    @Override
    public void remove(User user) {
        removeRecord(Integer.toString(user.getId()));
    }

    @Override
    public void insert(User user) {
        updateRecord(objectToArray(user));
    }

    @Override
    public List<User> getObjects(String columnName, String columnValue) {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = getRecords(columnName, columnValue);
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getInt("roleId"));
                //TODO read attendance from DB
                Attendance attendance = null;

                //TODO create user builder(?)
                User user = new UserFactory().makeUser(id, firstName, surname, email, password, role, attendance);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public User getUserById(String userId) {
        return getObjects("id", userId).get(0);
    }
}


