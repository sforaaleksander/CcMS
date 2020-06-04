package org.codecool.ccms.session;

import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.dao.UserSQLDao;
import org.codecool.ccms.inputProvider.InputProvider;
import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.User;
import java.util.List;

public class Login {
    private final InputProvider inputProvider;
    private final UserSQLDao userSQLDao;
    private final Session session;

    public Login(Session session){
        this.session = session;
        this.userSQLDao = session.getUserDao();
        this.inputProvider = session.getInputProvider();
    }

    public boolean loginAttempt() {
        String userEmail = inputProvider.gatherInput("Provide your email: ");
        String userPassword = inputProvider.gatherInput("Provide your password: ");
        List<User> users;
        users = userSQLDao.getObjects("email", userEmail);

        if (users != null && users.isEmpty()){
            session.getView().displayMessage("No matching user in database.");
            return false;
        }
        assert users != null;
        User user = users.get(0);
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