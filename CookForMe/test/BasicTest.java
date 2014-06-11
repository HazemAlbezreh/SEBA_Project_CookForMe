import org.joda.time.DateTime;
import org.junit.*;

import java.io.File;
import java.util.*;

import play.test.*;
import models.*;
import controllers.*;



public class BasicTest extends UnitTest {

	@Before 
	public void doJob() {
        try {
        	if (PriceCategory.count() == 0) {
                Fixtures.loadModels("prices.yml");
            }
        	if (Category.count() == 0) {
                Fixtures.loadModels("categories.yml");
            }
            if (Meal.count() == 0) {
                Fixtures.loadModels("meals.yml");
            }
            if (User.count() == 0) {
                Fixtures.loadModels("users.yml");
            }
        } catch (Throwable e) {
            // :(
        }
    }
	
	 
	 
	 @Test
	    public void loadModelsTest() {
		   boolean result = true;
	       if( Category.count() <= 0 ) 
	    	   result = false;
	       if( PriceCategory.count() <= 0 ) 
	    	   result = false;
	       if( Meal.count() <= 0 ) 
	    	   result = false;
	       if( User.count() <= 0 ) 
	    	   result = false;
	       assertEquals(result,true);
	    }
	 
	 
    
    @Test
    public void CreateMealTest() {
         long mealsCount = Meal.count();
    	String name="Paella";
    	String ingredients = "Chicken,spices,Olive Oil,chorizo,onion";
    	String category = "Spanish";
    	String price = "15€";
    	String fromDate ="2014/06/10" ;
    	String tillDate = "2014/06/18" ;

       Meal meal = new Meal(name,ingredients,Categories.find(category),Prices.find(price),fromDate,tillDate);
       meal.create();
       meal.save();
       long newMealCount = Meal.count();
       assertEquals(mealsCount +1 ,newMealCount);
    }
    
    
    @Test
    public void SearchMealTestByCategory() {
    	//assertEquals("15€", Prices.find("15€").name);
    	//assertEquals("Spanish", Categories.find("Spanish").name);
        Meal.deleteAll();
        //adding two meals with Spanish category and retrieve them.
        String category = "Spanish";
        	//First meal
        Meal meal1 = new Meal("Paella","Chicken,spices,Olive Oil,chorizo,onion",Categories.find(category),Prices.find("15€"),"2014/06/10","2014/06/18");
        meal1.create();
        meal1.save();
        	//Second meal
        Meal meal2 = new Meal("Potato Omelet","Potato,Eggs,Olive Oil",Categories.find(category),Prices.find("10€"),"2014/06/11","2014/06/20");
        meal2.create();
        meal2.save();
       
      //  Meal.findMeals()
       long newMealCount = Meal.count();
      // assertEquals(mealsCount +1 ,newMealCount);
    }
    
    
    
    
    

}
