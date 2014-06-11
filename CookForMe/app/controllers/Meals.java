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
@With(Security.class)
public class Meals extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = Security.getConnectedUser();
            renderArgs.put("user", user);
        }
    }

    public static void index() {
        List<Meal> meals = Meal.findAll();
        List<Category> categories = Category.findAll();
        render(meals,categories);
    }

    public static void delete(long MealId) {
        Meal meal = Meal.findById(MealId);
        meal.delete();
        index();
    }

    public static void create(String name,String ingredients,String category,File image)
            throws FileNotFoundException {
    	
        Meal meal = new Meal(name,ingredients,Categories.find(category));

        if (image != null) {
            meal.image = new Blob();
            meal.image.set(new FileInputStream(image),
                    MimeTypes.getContentType(image.getName()));
        }
        meal.create();
        meal.save();
        index();
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