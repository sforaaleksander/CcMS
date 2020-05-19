package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.view.UI;
import org.codecool.ccms.view.View;


public class Session {
    private UserDao userDao;
    private UI ui;
    private IO io;
    private View view;
    private MenuController menuController;

    public Session() {
        this.userDao = new UserDao();
        this.ui = new UI();
        this.io = new IO();
        this.view = new View();
        this.menuController = new MenuController();
        ui.welcomeMessage();
        loginRegisterMenu();
    }

    private void loginRegisterMenu() {
        ui.displayMenu(menuController.toStringTable());
    }


}
