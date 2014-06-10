package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

/** 
 * @author Akshit
 */
@Entity
@Table(name = "basket_item")
public class BasketItem extends Model {
	@ManyToOne
	@JoinColumn(name = "basket_id", nullable = false)
	public Basket basket;
	@ManyToOne
	@JoinColumn(name = "items_id", nullable = false)
	public Item item;
	@Column(name = "quantity", nullable = false)
	public int quantity;
	@Column(name = "price", nullable = false, precision = 22, scale = 0)
	public double price;

	public BasketItem(Basket basket, Item item, int quantity, double price) {
		this.basket = basket;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
		item.addBasketItem(this);
	}
	
	public void increaseQuantity() {
		quantity += 1;
		calcPrice();
	}
	
	public void decreaseQuantity() {
		quantity -= 1;
		calcPrice();
	}
	
	private void calcPrice() {
		price = item.price * quantity;		
	}
}
