package org.codecool.ccms.session;

import org.codecool.ccms.dao.*;
import org.codecool.ccms.modules.User;

import java.sql.SQLException;
import java.util.List;

public class Login {
    private UserDao userDao;

    Login(UserDao userDao){
        this.userDao = userDao;
    }

    public User loginAttempt(String userEmail, String userPassword) {
        userDao.connect();
        List<User> users = null;
        try {
            users = getMatchingUser(userEmail, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users.isEmpty() ? null : users.get(0);

    }

    private List<User> getMatchingUser(String userEmail, String userPassword) throws SQLException {
        return userDao.getUsers(
                "SELECT * FROM Users WHERE email = '" + userEmail + "' AND password = '" + userPassword + "';");
    }
}