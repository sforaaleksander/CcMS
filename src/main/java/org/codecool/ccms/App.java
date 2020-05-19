package org.codecool.ccms;

import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.session.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Session session = new Session();
        while (session.isActive){
            session.nextFrame();
        }
    }
}
