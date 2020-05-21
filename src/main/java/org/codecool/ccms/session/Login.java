package org.codecool.ccms.session;

import org.codecool.ccms.dao.*;
import org.codecool.ccms.inputProvider.InputProvider;
import org.codecool.ccms.modules.Displayable;
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
        List<Displayable> users;
        users = userDao.getUserBy("email", userEmail);

        if (users != null && users.isEmpty()){
            session.getView().displayMessage("No matching user in database.");
            return false;
        }
        User user = (User) users.get(0);
        if (!user.getPassword().equals(userPassword)) {
            System.out.println(user.getPassword());
            session.getView().displayMessage("Wrong password!");
            return false;
        }
        session.setUser(user);
        System.out.println("Logged in as " + user.getFirstName());
        session.getMenuController().menuMapUpdate();
        return true;
    }
}