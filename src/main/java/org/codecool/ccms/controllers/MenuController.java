package org.codecool.ccms.controllers;


import org.codecool.ccms.modules.User;
import org.codecool.ccms.session.Session;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuController {
    private Map<Integer, String> namesMap;
    private Map<Integer, Runnable> actionsMap;

    public MenuController(){
        actionsMap = new HashMap<>();
        namesMap = new HashMap<>();
        namesMap.put(1, "Login");
        namesMap.put(2, "Register");
        namesMap.put(0, "Exit");

        actionsMap.put(1, this::login);
        actionsMap.put(2, this::register);
        actionsMap.put(0, this::exit);
    }

    public void menuMapUpdate(Map<Integer, Object[]> actionMap) {
        actionMap.clear();
        // TODO populate with new menu methods
    }

    public String[][] toStringTable(){
        final int ID = 0;
        final int ACTION = 1;
        final int NUMBER_OF_COLUMNS = 2;
        String[][] table = new String[actionsMap.size()][NUMBER_OF_COLUMNS];
        for (Map.Entry<Integer, String> entry : namesMap.entrySet()) {
                table[entry.getKey()][ID] = Integer.toString(entry.getKey());
                table[entry.getKey()][ACTION] = entry.getValue();
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
