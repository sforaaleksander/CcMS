package org.codecool.ccms.controllers;


import org.codecool.ccms.modules.Role;
import org.codecool.ccms.session.LoginActions;
import org.codecool.ccms.session.Session;

import java.util.HashMap;
import java.util.Map;


public class MenuController {

    private final Session session;
    private Map<Integer, MenuOption> actionMap;

    public MenuController(Session session, LoginActions loginActions){
        this.session = session;
        actionMap = new HashMap<>();
        actionMap.put(1, new MenuOption(1, "Login", loginActions::handleLogin));
        actionMap.put(0, new MenuOption(0, "Exit", this.session::exit));
    }

    public Map<Integer, MenuOption> getActionMap() {
        return actionMap;
    }

    public void menuMapUpdate() {
        actionMap.clear();
        Role role = session.getUser().getRole();
        new ActionAssembler(session, role);
        //TODO
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
