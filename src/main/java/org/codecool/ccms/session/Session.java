package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.modules.User;
import org.codecool.ccms.view.UI;
import org.codecool.ccms.view.View;

import java.util.stream.Collectors;


public class Session {
    private UserDao userDao;
    private User user;
    private UI ui;
    private IO io;
    private View view;
    private MenuController menuController;
    private Boolean isRunning;

    public Session() {
        this.isActive = true;
        this.userDao = new UserDao();
        this.ui = new UI();
        this.io = new IO();
      
        this.view = new View();
        setUpLogin();
        this.menuController = new MenuController(this);
        this.view.setCommandList(menuController.getActionsMap().values().stream().collect(Collectors.toList()));
        ui.welcomeMessage();
    }

    public void nextFrame(){
        this.view.displayContent();
        int action = ui.gatherIntInput("Choose action:", 0, menuController.getActionsMap().size());
        menuController.getActionsMap().get(action).getAction().run();

    }
  
      private void setUpLogin(){
        Login login = new Login(this, io, userDao);
        this.menuController = new MenuController(login, this);

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void exit() {
        isRunning = false;
    }
}
