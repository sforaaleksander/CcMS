package org.codecool.ccms.session;

import org.codecool.ccms.dao.*;
import org.codecool.ccms.inputProvider.InputProvider;
import org.codecool.ccms.modules.User;

import java.sql.SQLException;
import java.util.List;

public class Login {
    private InputProvider inputProvider;
    private UserDao userDao;
    private Session session;

    public Login(Session session){
        this.session = session;
        this.userDao = session.getUserDao();
        this.inputProvider = session.getInputProvider();
    }

    public boolean loginAttempt() {
        String userEmail = inputProvider.gatherInput("Provide your email: ");
        String userPassword = inputProvider.gatherInput("Provide your password: ");
        userDao.connect();
        List<User> users = null;
        try {
            users = userDao.getUserBy("email", userEmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users != null && users.isEmpty()){
            // TODO return info that user was not found
            return false;
        }
        assert users != null;
        if (!users.get(0).getPassword().equals(userPassword)) {
            return false;
        }
        session.setUser(users.get(0));
        session.getMenuController().menuMapUpdate();
        System.out.println("Logged in as" + users.get(0).getFirstName());
        return true;
    }
}