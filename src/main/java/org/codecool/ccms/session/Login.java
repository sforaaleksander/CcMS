package org.codecool.ccms.session;

import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.InputProvider;
import org.codecool.ccms.modules.Displayable;
import org.codecool.ccms.modules.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public class Login {
    private final InputProvider inputProvider;
    private final UserDao userDao;
    private final Session session;

    public Login(Session session){
        this.session = session;
        this.userDao = session.getUserDao();
        this.inputProvider = session.getInputProvider();
    }

    public boolean loginAttempt() {
        String userEmail = inputProvider.gatherInput("Provide your email: ");
        String userPassword = inputProvider.gatherInput("Provide your password: ");
        List<Displayable> users;
        users = userDao.getUserBy("email", userEmail);
        if (users != null && users.isEmpty()){
            session.getView().displayMessage("No matching user in database.");
            return false;
        }
        assert users != null;
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

    private byte[] hashPassword(String pass, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytePass = pass.getBytes(StandardCharsets.UTF_8);
        md.update(salt);
        return  md.digest(bytePass);
    }

    private byte[] hashPassword(String pass) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        byte[] salt = secureRandom.generateSeed(16);
        return hashPassword(pass, salt);
    }

}