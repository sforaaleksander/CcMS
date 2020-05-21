package org.codecool.ccms.controllers;
import org.codecool.ccms.modules.Role;
import org.codecool.ccms.session.LoginActions;
import org.codecool.ccms.session.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuController {

    private final Session session;
    private Map<Integer, MenuOption> actionMap;

    public MenuController(Session session){
        this.session = session;
        actionMap = new HashMap<>();
        actionMap = new ActionAssembler(session, Role.GUEST).getMap();
    }

    public Map<Integer, MenuOption> getActionMap() {
        return actionMap;
    }

    public void menuMapUpdate() {
        actionMap.clear();
        Role role = session.getUser().getRole();
        actionMap = new ActionAssembler(session, role).getMap();
        this.session.getView().setCommandList(new ArrayList<>(this.getActionMap().values()));
    }
}
