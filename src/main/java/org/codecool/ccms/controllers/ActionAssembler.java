package org.codecool.ccms.controllers;

import org.codecool.ccms.modules.*;
import org.codecool.ccms.modules.Module;
import org.codecool.ccms.session.Actions;
import org.codecool.ccms.session.Session;
import org.codecool.ccms.session.StudentActions;

import javax.swing.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ActionAssembler {
    private Session session;
    private Role role;
    private HashMap actionMap;

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

    public HashMap getMap(){
        return this.actionMap;
    }

}
