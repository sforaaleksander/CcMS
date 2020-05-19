package org.codecool.ccms.dao;

import org.codecool.ccms.modules.User;
import org.codecool.ccms.modules.UserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao{

    public List<User> getUsers(String query) {
        List<User> users = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("first_name");
                String surname = results.getString("surname");
                String email = results.getString("email");
                String password = results.getString("password");
                int roleId = results.getInt("roleId");

                User user = UserFactory.makeUser(id, firstName, surname, email, password, roleId);
                users.add(user);

            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public void insertUser(String[] values) {
        String[] columns = { "name", "surname", "email", "password", "phone", "Id_role" };

        for (int i = 0; i < 5; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        insert("User", columns, values);
    }

    public void updateUser(String id, String column, String newValue) {
        newValue = String.format("'%s'", newValue);
        update("User", id, column, newValue);
    }
}
