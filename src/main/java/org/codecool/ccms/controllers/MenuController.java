package org.codecool.ccms.controllers;


import org.codecool.ccms.modules.User;
import org.codecool.ccms.session.Login;

import org.codecool.ccms.session.LoginActions;
import org.codecool.ccms.session.Session;

import java.util.HashMap;
import java.util.Map;

import static org.codecool.ccms.session.LoginActions.handleLogin;

public class MenuController {

    private Map<Integer, MenuOption> actionMap;

    public MenuController(Session session){
        actionMap = new HashMap<>();
        actionMap.put(1, new MenuOption(1, "Login", handleLogin(session)));
        actionMap.put(0, new MenuOption(0, "Exit", session::exit));
    }

    public Map<Integer, MenuOption> getActionMap() {
        return actionMap;
    }

    public void menuMapUpdate(Map<Integer, MenuOption> actionMap) {
        actionMap.clear();
        // TODO populate with new menu methods
    }

    public String[][] toStringTable(){
        final int NUMBER_OF_COLUMNS = 2;
        String[][] table = new String[actionMap.size()][NUMBER_OF_COLUMNS];
        for (Map.Entry<Integer, MenuOption> entry : actionMap.entrySet()) {
                table[entry.getKey()] = entry.getValue().toStringList();
        }
        return table;
    }
}
