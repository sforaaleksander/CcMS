package org.codecool.ccms.session;

import org.codecool.ccms.dao.UserDao;

public class Session {
    private UserDao userDao;

    Session() {
        this.userDao = new UserDao();
    }
}
