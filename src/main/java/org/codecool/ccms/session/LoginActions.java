package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;

import java.util.ArrayList;
import java.util.List;

public class LoginActions extends Actions{

    public LoginActions(Session session) {
        super(session);
    }

    public List<MenuOption> returnActions(){
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption(0, "Login", this::handleLogin));
        return options;
    };

    public Boolean handleLogin() {
        return new Login(this.getSession()).loginAttempt();
    }
}
