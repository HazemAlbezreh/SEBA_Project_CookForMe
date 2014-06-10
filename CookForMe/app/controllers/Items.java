package controllers;

import java.util.List;

import models.Basket;
import models.BasketItem;
import models.Item;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;


/**
 * @author Akshit
 */
public class Items extends Controller {

	/**
	 * In reality would have a login module that is used to authenticate users but
	 * that is not covered in this app so we just hard code a username to the
	 * session here.
	 * 
	 * @throws Throwable
	 */
	@Before
	static void initUser() throws Throwable {
		if (!session.contains("username")) {
			session.put("username", "bob");
		}
	}

	private static String getUsername() {
		return session.get("username");
	}
	
	public static void index() {
		List<Item> items = Item.find("order by name").from(1).fetch(100);
		
		Logger.debug("In index()"+items.size());
		for(Item item:items){
			Logger.debug(item.name+",");
		}
		render(items);
	}

	public static void addItemToBasket(Long id) {
		Logger.debug("In addItemToBasket");
		Item item = Item.findById(id);
		notFoundIfNull(item);
		Basket basket = Basket.findByUserid(getUsername());
		if (basket == null) {
			basket = new Basket(getUsername());
		}
		basket.addItem(item);
		basket.save();

		Logger.debug("Item in basket = "+item.name);
		
		Logger.debug("Basket = "+basket.basketItems);
		
		for(BasketItem bItem:basket.basketItems){
			Logger.debug(bItem.item.name+",");
		}
		
		renderXml(basket.basketItems);
		

	}

	public static void removeItemFromBasket(Long id) {
		Logger.debug("In removeItemFromBasket");
		Item item = Item.findById(id);
		notFoundIfNull(item);
		Basket basket = Basket.findByUserid(getUsername());
		notFoundIfNull(basket);
		basket.removeItem(item);
		basket.save();
		
		renderXml(basket.basketItems);
		
	}

	public static void checkout() {
		Logger.debug("In checkout");
		Basket basket = Basket.findByUserid(getUsername());
		notFoundIfNull(basket);
		List<BasketItem> basketItems = basket.basketItems;
		double total = 0;
		for (BasketItem basketItem : basketItems) {
			total = total + basketItem.price;
		}
		render(basketItems, total);
	}

	public static void processPayment() {
		Logger.debug("In processPayment");
		if (params.get("_pay") != null) {
			render();
		} else if (params.get("_cancel") != null) {
			Basket basket = Basket.findByUserid(getUsername());
			basket.delete();
			index();
		}
	}

	public static void restartShopping() {
		Logger.debug("In restartShopping");
		Basket basket = Basket.findByUserid(getUsername());
		basket.delete();
		index();
	}
}
