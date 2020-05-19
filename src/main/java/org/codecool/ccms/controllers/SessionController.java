package org.codecool.ccms.controllers;

import org.codecool.ccms.inputProvider.IO;
import org.codecool.ccms.session.Login;


public class SessionController {
    private IO io;


    public SessionController(){
        this.io = new IO();
    }

    public Runnable handleLogin(){
        String userEmail = io.gatherInput("Provide your email: ");
        String userPassword = io.gatherInput("Provide your password: ");
        return () -> new Login().loginAttempt(userEmail, userPassword);
    }
}
