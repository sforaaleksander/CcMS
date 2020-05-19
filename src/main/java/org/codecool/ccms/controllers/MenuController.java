package org.codecool.ccms.controllers;

import org.codecool.ccms.modules.User;
import org.codecool.ccms.session.Login;
import org.codecool.ccms.session.Session;

import java.util.HashMap;
import java.util.Map;

public class MenuController {
    private Map<Integer, MenuOption> actionMap;

    public MenuController(Login login, Session session){
        actionMap = new HashMap<>();
        actionMap.put(1, new MenuOption("Login", login::loginAttempt));
        actionMap.put(0, new MenuOption("Exit", session::exit));
    }

    public Map<Integer, MenuOption> getActionMap() {
        return actionMap;
    }

    public void menuMapUpdate(Map<Integer, Object[]> actionMap) {
        actionMap.clear();
        // TODO populate with new menu methods
    }

    public String[][] toStringTable(){
        final int ID = 0;
        final int ACTION = 1;
        final int NUMBER_OF_COLUMNS = 2;
        String[][] table = new String[actionMap.size()][NUMBER_OF_COLUMNS];
        for (Map.Entry<Integer, MenuOption> entry : actionMap.entrySet()) {
                table[entry.getKey()][ID] = Integer.toString(entry.getKey());
                table[entry.getKey()][ACTION] = entry.getValue().getOptionName();
        }
        return table;
    }
}
