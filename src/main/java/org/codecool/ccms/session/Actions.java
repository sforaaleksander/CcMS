package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.dao.UserDao;

import java.util.List;

public abstract class Actions {

    private final Session session;

    public Actions(Session session) {
        this.session = session;
    }

    public abstract List<MenuOption> returnActions();

    public Session getSession() {
        return session;
    }

}
