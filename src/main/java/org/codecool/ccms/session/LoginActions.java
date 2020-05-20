package org.codecool.ccms.session;

public class LoginActions {

    private final Session session;

    public LoginActions(Session session) {
        this.session = session;
    }

    public Boolean handleLogin() {
        return new Login(session).loginAttempt();
    }
}
