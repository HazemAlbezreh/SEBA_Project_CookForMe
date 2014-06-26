package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Order extends Model{
	
	
	public Order(List<BasketItem> items, double totalPrice, String address) {
	}
	
	public void processOrder(){
		System.out.println("Your order is being processed");
	}

}
