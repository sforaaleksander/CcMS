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
        this.welcome();
//        this.loginRegisterChoice();
        this.menuController = new MenuController();

        menuController
    }

    private void welcome() {
        ui.welcomeMessage();
        loginRegisterChoice();
    }

    private void loginRegisterChoice() {
            boolean registered = false;
            do {
                ui.displayLoginOrRegistrationMenu();
                String input = ui.gatherInput("What to do?: ");
                if (input.equals("2")) {
                    new Registration(userDao);
                } else if (input.equals("1")) {
                    registered = true;
                }
            } while (!registered);
        }
}
