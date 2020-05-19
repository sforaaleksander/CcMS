package org.codecool.ccms.session;

import org.codecool.ccms.dao.*;
import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.modules.User;

import java.sql.SQLException;
import java.util.List;

public class Login {
    private IO io;
    private UserDao userDao;

    public Login(IO io){
        this.io = io;
        this.userDao = new UserDao();
    }

    public User loginAttempt() {
        String userEmail = io.gatherInput("Provide your email: ");
        String userPassword = io.gatherInput("Provide your password: ");
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