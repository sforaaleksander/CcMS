package org.codecool.ccms.controllers;

import org.codecool.ccms.modules.*;
import org.codecool.ccms.modules.Module;
import org.codecool.ccms.session.*;

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
            case "GUEST":
                index = populateMapWithActions(index, actionMap, new LoginActions(session));
                break;
            case "STUDENT":
                index = populateMapWithActions(index, actionMap, new StudentActions(session));
                break;
            case "MENTOR":
                index = populateMapWithActions(index, actionMap, new MentorActions(session));
                break;
            case "MANAGER":
                index = populateMapWithActions(index, actionMap, new ManagerActions(session));
                index = populateMapWithActions(index, actionMap, new EmpAndManActions(session));
                break;
            case "EMPLOYEE":
//                index = populateMapWithActions(index, actionMap, new EmployeeActions(session));
                index = populateMapWithActions(index, actionMap, new EmpAndManActions(session));
                break;
        }
        index = populateMapWithActions(index, actionMap, new UniversalActions(session));

    }

    private int populateMapWithActions(int index, Map<Integer, MenuOption> actionMap, Actions actions){
        for (MenuOption option : actions.returnActions()){
            option.setId(index);
            actionMap.put(index, option);
            index++;
        }
        return index;
    }

    public Map<Integer, MenuOption> getMap(){ return this.actionMap; }

}
