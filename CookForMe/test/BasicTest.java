import org.junit.*;

import controllers.Items;

import java.util.*;

import play.Logger;
import play.db.jpa.JPA;
import play.mvc.Before;
import play.mvc.Scope.Session;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {	
	
	@Test
	public void testItemInsertAndRetrieve(){
		
		Item item = new Item("Gobhi Aloo", "Potatoes in Cauliflower", 10); //name, description, price	
    	item.create();
    	
    	Item retrieved = Item.findById(item.id);	
    	assertEquals("Gobhi Aloo", retrieved.name);
	}
	
	@Test
    public void addToBasketTest(){
    	Item item = new Item("Gobhi Aloo3", "Potatoes in Cauliflower", 10); //name, description, price	
    	item.create();
    		
       	Basket b = new Basket("akshit2"); //userID
       	b.create();
       	b.addItem(item);
    	
		Basket bRetrieved = Basket.findById(b.id);
		
		assertTrue(checkForItemInBasket(bRetrieved,item));
    	
    }

    @Test
    public void removeFromBasketTest(){
    	
    	Item item = new Item("Gobhi Aloo2", "Potatoes in Cauliflower", 10); //name, description, price	
    	item.create();
    		
       	Basket b = new Basket("akshit"); //userID
       	b.create();
       	
       	b.addItem(item);
       
       	assertTrue(checkForItemInBasket(b,item));
       	
       	b.removeItem(item);
    	
		Basket bRetrieved = Basket.findById(b.id);
		
		assertFalse(checkForItemInBasket(bRetrieved,item));
    	
    }
    
    @Test
    public void testCalculatePrice(){
    	//item (name, description, price)
    	Item item = new Item("Gobhi Aloo", "Potatoes in Cauliflower", 10);     	
    	item.create();
    	
    	Basket b = new Basket("akshit"); //userID
    	
    	b.addItem(item, 100); //with quantity 100
    	
       	assertEquals(1000, b.getTotalBasketPrice());
    }
    
    /**
     * Function to check for a Item in the Basket
     * @param b Basket
     * @param item The Item to be searched
     * @return true if item was found
     */
    public boolean checkForItemInBasket(Basket b, Item item){
		boolean itemExists = false;
		for (BasketItem basketItem : b.getBasketItem()) {
			if (basketItem.item.getId().equals(item.getId())){
				itemExists = true;
			}
		}
		return itemExists;

	}
    
    

}
