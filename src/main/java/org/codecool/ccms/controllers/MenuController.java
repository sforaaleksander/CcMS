package org.codecool.ccms.controllers;


import org.codecool.ccms.modules.User;
import org.codecool.ccms.session.Session;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuController {
    private Map<Integer, Object[]> actionMap;

    public MenuController(){
        actionMap = new HashMap<>();
        Object[] login = {"Login", login()};
        Object[] register = {"Register", register()};
        Object[] exit = {"Exit", exit()};
        actionMap.put(1, login);
        actionMap.put(2, register);
        actionMap.put(0, exit);
    }

    public void menuMapUpdate(Map<Integer, Object[]> actionMap) {
        actionMap.clear();
        // TODO populate with new menu methods
    }

    public String[][] toStringTable(){
        final int ID = 0;
        final int ACTION = 1;
        final int ACTION_NAME = 0;
        final int NUMBER_OF_COLUMNS = 2;
        String[][] table = new String[actionMap.size()][NUMBER_OF_COLUMNS];
        for (Map.Entry<Integer, Object[]> entry : actionMap.entrySet()) {
                table[entry.getKey()][ID] = Integer.toString(entry.getKey());
                table[entry.getKey()][ACTION] = (String) entry.getValue()[ACTION_NAME];
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
