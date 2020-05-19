package org.codecool.ccms.controllers;


import org.codecool.ccms.modules.User;
import org.codecool.ccms.session.Session;

import java.util.Map;

public class MenuController {
    private Map<Integer, Object[]> actionMap;

    public MenuController(){
        Object[] login = {"Login", login()};
        Object[] register = {"Register", register()};
        Object[] exit = {"Exit", exit()};
        actionMap.put(1, login);
        actionMap.put(2, register);
        actionMap.put(3, exit);
    }

    public void menuMapUpdate(Map<Integer, Object[]> actionMap) {
        actionMap.clear();
        // TODO populate with new menu methods
    }

    public String[][] toStringTable(){
        String[][] table = new String[actionMap.size()][2];

        for (Map.Entry<Integer, Object[]> entry : actionMap.entrySet()) {
                table[entry.getKey()-1][0] = Integer.toString(entry.getKey());
                table[entry.getKey()-1][1] = (String) entry.getValue()[0];
        }
        return table;
    }


    public User login(){
        return null;
    }

    public User register(){
        return null;
    }

    public Object exit(){
        return null;
    }

}
