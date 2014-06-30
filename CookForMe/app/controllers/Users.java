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
            Application.index();
        } else {
            render();
        }
    }
    
    public static void userMeals(){
    	User currentUser = Security.getConnectedUser();
        List<Meal> offeredMeals = Meal.findMeals("", "", "", currentUser.name);
        
       // Basket basket = Basket.findByUserid(.email);
        
        //Order order = Order.findByEmail(currentUser.email);
        //List<Order> order = Order.findAll();
        List<Meal> orderedMeals = new ArrayList<Meal>();
        
        if(currentUser !=null){
        	//Basket basket = order.orderedBasket;
        	System.out.println("USERSMEAL="+currentUser.basket);
        	if(currentUser.basket!=null){
        		List<BasketItem> basketItems = currentUser.basket.basketItems;
        		System.out.println("USERSMEAL="+basketItems);
        		for (BasketItem bItem :basketItems){
        			Meal meal=Meal.findById(bItem.item.mealID);
        			orderedMeals.add(meal);
        		}
        	}
        }
		
        render(offeredMeals,orderedMeals);
    }
    
   
    
    public static void signUp(String email,String name, String passwd,String passwdConfirm) {
    	validation.required(email);
    	validation.required(passwd);
    	validation.required(passwdConfirm);
    	validation.equals(passwd, passwdConfirm);
    	User found = User.findByEmail(email);
    	if (found !=null){
			validation.addError(email,"Email","Email is already used");
    	}
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            render();
        }
        User user = new User(email,passwd,name);
        user.create();
        user.save();       
    }

    public static void logout() {
        Security.setConnected(null);
        login(null);
    }
}