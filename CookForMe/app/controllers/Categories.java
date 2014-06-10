package controllers;

import java.util.List;

import models.Category;
import models.Meal;
import play.mvc.Controller;
import play.*;
import play.mvc.*;
import play.mvc.results.Result;

public class Categories extends Controller {
	
	public static void findAll() {
        List<Category> found = Category.findAll();
        render(found);
    }
	
	public static Category find(String name) {
        return Category.findByName(name);
    }
}
