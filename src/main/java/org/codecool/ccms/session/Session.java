package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.InputProvider;
import org.codecool.ccms.modules.User;
import org.codecool.ccms.view.UI;
import org.codecool.ccms.view.View;

import java.util.stream.Collectors;


public class Session {
    private UserDao userDao;
    private User user;
    private UI ui;
    private InputProvider inputProvider;
    private View view;
    private MenuController menuController;
    private Boolean isRunning;

    public Session() {
        this.isRunning = true;
        this.userDao = new UserDao();
        this.ui = new UI();
        this.inputProvider = new InputProvider();
      
        this.view = new View();
        setUpLogin();
        this.menuController = new MenuController(this);
        this.view.setCommandList(menuController.getActionMap().values().stream().collect(Collectors.toList()));
        ui.welcomeMessage();
    }

    public void nextFrame(){
        this.view.displayContent();
        int action = ui.gatherIntInput("Choose action:", 0, menuController.getActionMap().size());
        menuController.getActionMap().get(action).getAction().run();

    }
  
      private void setUpLogin() {
          Login login = new Login(this);
          this.menuController = new MenuController(this);
      }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
