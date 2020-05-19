package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.controllers.SessionController;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.view.UI;

public class Session {
    private UserDao userDao;
    private UI ui;
    private MenuController menuController;

    public Session() {
        this.userDao = new UserDao();
        this.ui = new UI();
        SessionController sessionController = new SessionController();
        this.menuController = new MenuController(sessionController);
        ui.welcomeMessage();
        loginRegisterMenu();
    }

    private void loginRegisterMenu() {
        ui.displayMenu(menuController.toStringTable());
    }
}
