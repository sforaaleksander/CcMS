package org.codecool.ccms;

import org.codecool.ccms.session.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Session session = Session.getSession();
        while (session.getIsRunning()){
            session.nextFrame();
        }
    }
}
