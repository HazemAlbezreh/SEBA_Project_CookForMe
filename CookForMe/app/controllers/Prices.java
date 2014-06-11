package controllers;

import java.util.List;



import models.Meal;
import models.PriceCategory;
import play.mvc.Controller;
import play.*;
import play.mvc.*;
import play.mvc.results.Result;

public class Prices extends Controller {
	
	public static void findAll() {
        List<PriceCategory> found = PriceCategory.findAll();
        render(found);
    }
	
	public static PriceCategory find(String name) {
        return PriceCategory.findByName(name);
    }
}
