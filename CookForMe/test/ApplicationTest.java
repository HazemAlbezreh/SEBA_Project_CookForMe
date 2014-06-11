import java.util.List;

import org.junit.*;

import play.Logger;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
    
    @Test
    public void addToBasketTest(){
    	Item item = new Item("Dal2", "Makhani", 10); //name, description, price	
    	Basket b = new Basket("akshit"); //userID
    	b.addItem(item);
    	//b.save();
    	
    	List<Item> items = Item.find("order by name").from(1).fetch(100);
		
		Logger.debug("In index()"+items.size());
		for(Item item1:items){
			Logger.debug(item1.name+",");
		}
    	
    	
    	//Item retrieved = Item.findById(item.id);	
    	//assertEquals("Dal", retrieved.name);
    	
//		assertEquals(new Integer(2013), retrieved.year);
    	
    	
    }
    
}