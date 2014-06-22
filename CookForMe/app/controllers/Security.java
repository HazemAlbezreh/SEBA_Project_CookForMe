package controllers;

import java.util.ArrayList;

import play.mvc.Before;
import play.mvc.Controller;
import models.User;

/**
 * A controller that acts as a request intercepter. It is responsible
 * for the user management
 */
public class Security extends Controller {

    /**
     * Executed before each request
     */
    @Before
    static void setConnectedUser() {
    	ArrayList<String> requireLogin = new ArrayList<String>();
    	requireLogin.add("Meals.offer");
        if (!isConnected() && requireLogin.contains(request.action)) {
            Users.login(null);
        }
    }

    /**
     * @return whether there is a logged in user in the current session
     */
    static boolean isConnected() {
        return getConnected() != null;
    }

    /**
     * Sets the logged in user in the current session
     * 
     * @param email
     */
    static void setConnected(String email) {
        session.put("email", email);
    }

    /**
     * @return the email of the logged in user in the current session
     */
    static String getConnected() {
        return session.get("email");
    }

    /**
     * @return the logged in user in the current session
     */
    static User getConnectedUser() {
        return User.find("byEmail", getConnected()).first();
    }
}
