package controllers;

import play.*;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import javax.validation.Valid;

import models.*;

public class Users extends Controller {

    public static void login(@Valid User user) {
        if (user == null) {
            render();
        }

        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            render();
        }
        User found = User.findByEmailAndPassword(user.email, user.password);
        if (found != null) {
            Security.setConnected(found.email);
            Meals.index();
        } else {
            render();
        }
    }

    public static void logout() {
        Security.setConnected(null);
        login(null);
    }
}