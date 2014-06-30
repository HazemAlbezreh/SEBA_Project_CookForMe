package controllers;

import groovy.json.JsonBuilder;

import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonSerializationContext;

import models.Basket;
import models.BasketItem;
import models.Category;
import models.Item;
import models.Meal;
import models.Order;
import models.User;
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
		if (Security.isConnected()) {
			User user = Security.getConnectedUser();
			renderArgs.put("user", user);
		}
	}

	private static String getUsername() {
		return session.get("email");
	}
	
	@Before
	static void loadItems() {
		List<Meal> meals = Meal.findAll(); 
		List<Item> items = new ArrayList<Item>(); 
		for(Meal m: meals) 
		{ 
			double price = Double.parseDouble(m.priceCategory.name.replace('€', ' ')); 
			Item item = new Item(m.name, m.ingredients, price,m.id);
			item.create();
			item.save();
			items.add(item); 
		}
		
		Basket basket = Basket.findByUserid(getUsername());
		
		if(basket!=null){
			List<BasketItem> basketItems = basket.basketItems;

			for (BasketItem basketItem : basketItems) {
				Logger.debug("In index: + basketItem.item.name="+basketItem.item.name);
			}
			Logger.debug("Basketcount= "+basket.getTotalItemsInBasketCount());

		}
		renderArgs.put("basket", basket);
		renderArgs.put("items", items);
		renderArgs.put("meals", meals);
	}
	
	public static void index() {		
		/*List<Meal> meals = Meal.findAll(); 
		List<Item> items = new ArrayList<Item>(); 
		for(Meal m: meals) 
		{ 
			double price = Double.parseDouble(m.priceCategory.name.replace('€', ' ')); 
			Item item = new Item(m.name, m.ingredients, price); 
			items.add(item); 
		}*/
		List<Item> items = Item.findAll();
		
		Logger.debug("In index()="+items.size());
		
		Basket basket = Basket.findByUserid(getUsername());
		
		if(basket!=null){
			List<BasketItem> basketItems = basket.basketItems;

			for (BasketItem basketItem : basketItems) {
				Logger.debug("In index: + basketItem.item.name="+basketItem.item.name);
			}
			Logger.debug("Basketcount= "+basket.getTotalItemsInBasketCount());

		}

		render(items);
	}

	public static void addItemToBasket(Long id) {
		Logger.debug("In addItemToBasket");
		Item item = Item.findById(id);
		//notFoundIfNull(item);
		System.out.println("user="+getUsername());
		if(getUsername()==null){
			//render(Users.login());
			//TODO: make it only if the user is logged in.
			return;			
		}
		
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
		
		if(basket==null){
			Meals.browse( ((Category)Category.findById((long)1)).name );
		}else{
		
			List<BasketItem> basketItems = basket.basketItems;
			double total = 0;
			for (BasketItem basketItem : basketItems) {
				Logger.debug("In checkout: + basketItem.price="+basketItem.basketItemPrice);
				total = total + basketItem.basketItemPrice;
			}
			Logger.debug("In checkout:Total="+total);
			render(basketItems, total);
		}
	}

	public static void confirmPayment() {
		Logger.debug("In confirmPayment");
		
		Basket basket = Basket.findByUserid(getUsername());

		Logger.debug("basket = "+basket.basketItems);
		User connectedUser = Security.getConnectedUser();
		
		List<BasketItem> basketItems = basket.basketItems;
		List<Meal> meals = new ArrayList<Meal>();
		for (BasketItem bItem :basketItems){
			Meal meal=Meal.findById(bItem.item.mealID);
			meal.numOrderedtimes +=bItem.quantity;
			System.out.println("name =" + meal.name );
			meals.add(meal);
		}
		
		Order order = new Order();
		
		order.processOrder();
		
		//connectedUser.orders.add(order);
					
		String user = getUsername();
		render(user, basketItems);
		
		//render();
	}

	public static void restartShopping() {
		Logger.debug("In restartShopping");
		Basket basket = Basket.findByUserid(getUsername());
		basket.delete();
		index();
	}
	
	public static void reviewShipping(){
		Logger.debug("In restartShopping");
		//render(reviewOrder);
	}
	
	
	public static void reviewOrder(){
		if (params.get("_pay") != null) {
			Basket basket = Basket.findByUserid(getUsername());

			Logger.debug("basket = "+basket.basketItems);
			
			List<BasketItem> basketItems = basket.basketItems;
			
			double total = basket.getTotalBasketPrice();
			
			for(BasketItem bItem:basket.basketItems){
				Logger.debug(bItem.item.name+",");
			}
			
			
			render(basketItems, total);
		} else if (params.get("_cancel") != null) {
			Basket basket = Basket.findByUserid(getUsername());
			basket.delete();
			Meals.browse( ((Category)Category.findById((long)1)).name );
		}
		
		
	}
	
	public static void reviewBilling(){
		
		//renderJSON();
		//request.get().
		System.out.println("In reviewBilling:"+params.get("body").toString());
		
		String shippingDetails = params.get("body");
		
		
		//Map<String, String> shippingDetailsMap = params.get("body").;
		
		
		System.out.println("In reviewBilling:"+shippingDetails);
	}
	
	public static void reviewShipping(String firstname, String lastname){
		
		//renderJSON();
		System.out.println("In reviewShipping"+firstname);
	}
	
	public static void getItem(Long id) {
		Item item = Item.findById(id);
     
        renderJSON(item);
        
	}
	
	public static int getTotalCartCount(){
		
		Basket basket = Basket.findByUserid(getUsername());
		
		if(basket!=null){
			return basket.getTotalItemsInBasketCount();
		}else{
			return 0;
		}
		
	}
	
	public static void getAddedItemsInBasket(){
		
		Basket basket = Basket.findByUserid(getUsername());
		
		List<BasketItem> basketItems = basket.basketItems;
			
		renderXml(basketItems);		
	}
	
	
}
