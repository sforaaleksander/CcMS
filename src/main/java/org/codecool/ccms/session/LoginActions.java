package org.codecool.ccms.session;

public class LoginActions {
    public Boolean handleLogin(Session session) {
        return new Login(session).loginAttempt();
    }
}
