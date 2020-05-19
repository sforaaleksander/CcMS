package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.modules.User;
import org.codecool.ccms.view.UI;

public class Session {
    private UserDao userDao;
    private User user;
    private UI ui;
    private IO io;
    private MenuController menuController;

    public Session() {
        this.userDao = new UserDao();
        this.ui = new UI();
        this.io = new IO();
        sessionRun();
    }

    private void setUpLogin(){
        Login login = new Login(this, io, userDao);
        this.menuController = new MenuController(login);
        ui.welcomeMessage();
        ui.displayMenu(menuController.toStringTable());
        int userChoice = io.gatherIntInput("");
        menuController.getActionMap().get(userChoice).getRunnable().run();
    }

    private void sessionRun(){
        boolean isRunning = true;
        while (isRunning){
            setUpLogin();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
