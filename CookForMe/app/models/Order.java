package models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "order")
public class Order extends Model{

	@Column(name = "email", nullable = false)
	public String email;
	
	public Basket orderedBasket;
	
	public Order(String email, Basket orderedBasket) {
		this.email = email;
		this.orderedBasket = orderedBasket;
	}
	
	public Order(String userid) {
		//this.userid = userid;
	}
	
	public void addBasket(Basket orderedBasket){
		//this.orderedBasket = orderedBasket;
	}
	
	public void processOrder(){
		System.out.println("Your order is being processed");
	}
	
	public static Order findByEmail(String email) {
		return find("byEmail", email).first();
	}
}
