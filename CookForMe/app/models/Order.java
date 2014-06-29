package models;


import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Order extends Model{

	
	
	public Order() {
		
	}
	
	public void processOrder(){
		System.out.println("Your order is being processed");
	}

}
