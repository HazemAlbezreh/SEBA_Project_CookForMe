package controllers;

import play.*;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.*;
import play.mvc.results.Result;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import models.*;

/**
 * 
 */
//@With(Security.class)
public class Meals extends Controller {

	/*@Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = Security.getConnectedUser();
            renderArgs.put("user", user);
        }
    }*/
    
    public static void browse(String category) {
    	if (category == null) {
    		category = ((Category)Category.findById((long)1)).name;
    	} else if (category.equals("")) {
    		category = ((Category)Category.findById((long)1)).name;
    	}
    	List<Meal> meals = Meal.findMeals("", category, "");
        //List<Meal> meals = Meal.findAll();
        List<Category> categories = Category.findAll();
        List<Meal> popularMeals = Meal.findAll();
        render(meals, categories, popularMeals);
    }

    public static void offer() {
        List<Meal> meals = Meal.findAll();
        List<Category> categories = Category.findAll();
        List<PriceCategory> prices = PriceCategory.findAll();
        render(meals,categories,prices);
    }

    public static void delete(long MealId) {
        Meal meal = Meal.findById(MealId);
        meal.delete();
        browse( ((Category)Category.findById(1)).name );
    }

    public static void create(String name,String ingredients,String category,String price,String fromDate,String tillDate,File image)
         {
    	validation.required(name);
    	validation.required(ingredients);
    	validation.required(category);
    	validation.required(price);
    	validation.required(fromDate);
    	validation.required(tillDate);

    	
    	if(validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            Meals.offer();
        }
    	
        Meal meal = new Meal(name,ingredients,Categories.find(category),Prices.find(price),fromDate,tillDate);

        if (image != null) {
            meal.image = new Blob();
            try {
				meal.image.set(new FileInputStream(image),
				        MimeTypes.getContentType(image.getName()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			}
        }
        meal.create();
        meal.save();
        browse( ((Category)Category.findById(1)).name );
    }
    
    
    public static void renderImage(long mealId) {
        Meal meal = Meal.findById(mealId);

        response.setContentTypeIfNotSet(meal.image.type());
        InputStream cover = meal.image.get();

        if (cover != null) {
            renderBinary(cover);
        } else {
        	if (meal.imagePath !=null){
        		renderBinary(new File("public/images/"+meal.imagePath));
        	}else{
        		renderBinary(new File("public/images/defaultCover.png"));
        	}
        }
    }

    public static void find(String name) {
        List<Meal> found = Meal.findByName(name);
        renderJSON(found);
    }
    
    public static void search(String searchToken, String category, String ingredients) {
    	if (category == null)  category = "";
    	if (ingredients == null)  ingredients = "";
    	
    	List<Meal> meals = Meal.findMeals(searchToken, category, ingredients);
    	List<Category> categories = Category.findAll();
    	categories.add(0, new Category(""));
        render(searchToken, meals, categories, category, ingredients);
    }
}