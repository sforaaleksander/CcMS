package org.codecool.ccms;
import org.codecool.ccms.session.Session;

public class App 
{
    public static void main( String[] args ) {
        String[] arg = {"commandsTest.txt"};
        Session session = Session.getSession(arg);
        while (session.getIsRunning()){
            session.nextFrame();
        }
        session.getView().displayMessage("Thanks for using our product mate. Arrivederci!");
    }
}