package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.view.UI;
import org.codecool.ccms.view.View;

import java.util.stream.Collectors;


public class Session {
    private UserDao userDao;
    private UI ui;
    private IO io;
    private View view;
    private MenuController menuController;
    public boolean isActive;

    public Session() {
        this.isActive = true;
        this.userDao = new UserDao();
        this.ui = new UI();
        this.io = new IO();
        this.view = new View();
        this.menuController = new MenuController(this);
        this.view.setCommandList(menuController.getActionsMap().values().stream().collect(Collectors.toList()));
        ui.welcomeMessage();
    }

    public void nextFrame(){
        this.view.displayContent();
        int action = ui.gatherIntInput("Choose action:", 0, menuController.getActionsMap().size());
        menuController.getActionsMap().get(action).getAction().run();
    }




}
