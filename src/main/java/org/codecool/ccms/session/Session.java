package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.InputProvider;
import org.codecool.ccms.modules.User;
import org.codecool.ccms.view.UI;
import org.codecool.ccms.view.View;

import java.util.stream.Collectors;


public class Session {
    private final UserDao userDao;
    private User user;
    private final UI ui;
    private final InputProvider inputProvider;
    private final View view;
    private final MenuController menuController;
    private Boolean isRunning;

    public Session() {
        this.isRunning = true;
        this.userDao = new UserDao();
        this.ui = new UI();
        this.inputProvider = new InputProvider();
      
        this.view = new View();
        LoginActions loginActions = new LoginActions(this);
        this.menuController = new MenuController(this, loginActions);
        this.view.setCommandList(menuController.getActionMap().values().stream().collect(Collectors.toList()));
        ui.welcomeMessage();
    }

    public void nextFrame(){
        this.view.displayContent();
        int action = ui.gatherIntInput("Choose action:", 0, menuController.getActionMap().size());
        menuController.getActionMap().get(action).getAction().run();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //TODO find better place for exit method
    public void exit() {
        isRunning = false;
    }

    public Boolean getIsRunning() {
        return isRunning;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public InputProvider getInputProvider() {
        return inputProvider;
    }
}
