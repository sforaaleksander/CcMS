package org.codecool.ccms.controllers;


import java.util.Map;

public class MenuController {
    private Map<Integer, Object[]> actionMap;

    public MenuController(){
        actionMap.put(1, {"Login", login()});
        actionMap.put(2, {"Register", register()});
        actionMap.put(3, {"Exit", exit()});
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
}
