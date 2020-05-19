package org.codecool.ccms.controllers;


import org.codecool.ccms.modules.User;
import org.codecool.ccms.session.Session;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuController {
    private Map<Integer, MenuOption> actionsMap;

    public MenuController(){
        actionsMap = new HashMap<>();
        actionsMap.put(1, new MenuOption(1, "Login", this::login));
        actionsMap.put(2, new MenuOption(2, "Register", this::register));
        actionsMap.put(0, new MenuOption(0, "Exit", this::exit));
    }

    public void menuMapUpdate(Map<Integer, MenuOption> actionMap) {
        actionMap.clear();
        // TODO populate with new menu methods
    }

    public String[][] toStringTable(){
        final int NUMBER_OF_COLUMNS = 2;
        String[][] table = new String[actionsMap.size()][NUMBER_OF_COLUMNS];
        for (Map.Entry<Integer, MenuOption> entry : actionsMap.entrySet()) {
                table[entry.getKey()] = entry.getValue().toStringList();
        }
        return table;
    }


    public Boolean login(){
        System.out.println("LOGIN");
        return true;
    }

    public Boolean register(){
        System.out.println("REGISTER");

        return true;
    }

    public Boolean exit(){
        return true;
    }

}
