package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.view.UI;

public class Session {
    private UserDao userDao;
    private UI ui;
    private IO io;
    private MenuController menuController;

    public Session() {
        this.userDao = new UserDao();
        this.ui = new UI();
        this.io = new IO();
        this.menuController = new MenuController();
        ui.welcomeMessage();
        loginRegisterMenu();
    }

    private void loginRegisterMenu() {
        String[] headers = {"no", "action"};
        ui.displayMenu(headers, menuController.toStringTable());
    }


}
