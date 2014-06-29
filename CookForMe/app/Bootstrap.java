
import models.Category;
import models.Meal;
import models.PriceCategory;
import models.User;
import models.Item;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * Executed on start up of the application. Put some sample 
 * data in the database. 
 */
@OnApplicationStart
public class Bootstrap extends Job {

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
			/*if(Item.count() == 0) {
            	Fixtures.loadModels("initial-data.yml");
        	}*/	
			
        } catch (Throwable e) {
            // :(
        }
    }
}
