package org.codecool.ccms.controllers;

import org.codecool.ccms.modules.*;
import org.codecool.ccms.modules.Module;
import org.codecool.ccms.session.Actions;
import org.codecool.ccms.session.LoginActions;
import org.codecool.ccms.session.Session;
import org.codecool.ccms.session.StudentActions;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ActionAssembler {
    private final Session session;
    private final Role role;
    private final Map<Integer, MenuOption> actionMap;

    public ActionAssembler(Session session, Role role) {
        actionMap = new HashMap<>();
        this.session = session;
        this.role = role;
        mapBuilder();
    }

    private void mapBuilder(){
        int index = 0;
        Actions actions;
        switch (role.toString()){
            case "STUDENT":
                actions = new StudentActions(session);
                for (MenuOption option : actions.returnActions()){
                    option.setId(index);
                    actionMap.put(index, option);
                    index++;
                }
                break;

            case "MENTOR":

                break;

            case "MANAGER":

                break;

            case "EMPLOYEE":

                break;
        }
    }

    public Map<Integer, MenuOption> getMap(){ return this.actionMap; }

}
