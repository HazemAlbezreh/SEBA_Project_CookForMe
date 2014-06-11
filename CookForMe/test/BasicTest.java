import org.joda.time.DateTime;
import org.junit.*;

import java.io.File;
import java.util.*;

import play.test.*;
import models.*;
import controllers.*;



public class BasicTest extends UnitTest {
 //Execute doJob before each test (Loading Models)
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
	
	 
	 //testing if the models are loaded properly
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
	 
	 
    //testing of meal creation
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
    
    //testing search meals functionality (By Category as an example)
    @Test
    public void SearchMealTestByCategory() {
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
       
        List<Meal> foundMeals=Meal.findMeals("",category,"","","");
        long foundMealCount = foundMeals.size();
        assertEquals(foundMealCount,2);
    }
    
    //testing search meals functionality (By Category and ingredients)
    @Test
    public void SearchMealTestByCategoryAndIngredients() {
        Meal.deleteAll();
        //adding two meals with Spanish category that contain chicken, 
        //and then retrieve them.
        String category = "Spanish";
        	//First meal
        Meal meal1 = new Meal("Paella","Chicken,spices,Olive Oil,chorizo,onion",Categories.find(category),Prices.find("15€"),"2014/06/10","2014/06/18");
        meal1.create();
        meal1.save();
        	//Second meal
        Meal meal2 = new Meal("Potato Omelet","Potato,Eggs,Olive Oil",Categories.find(category),Prices.find("10€"),"2014/06/11","2014/06/20");
        meal2.create();
        meal2.save();
       
        List<Meal> foundMeals=Meal.findMeals("",category,"Chicken","","");
        long foundMealCount = foundMeals.size();
        assertEquals(foundMealCount,1);
    }
    
    
    
    
    
    

}
