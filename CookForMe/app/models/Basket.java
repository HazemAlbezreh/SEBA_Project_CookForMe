package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import play.db.jpa.Model;

/** 
 * @author Akshit
 */
@Entity
@Table(name = "basket")
public class Basket extends Model {
	@Column(name = "userid", nullable = false)
	public String userid;
	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)

	public List<BasketItem> basketItems = new ArrayList<BasketItem>(0);

	public Basket(String userid) {
		this.userid = userid;
	}
	
	public void addItem(Item item) {
		boolean itemExists = false;
		for (BasketItem basketItem : basketItems) {
			if (basketItem.item.getId().equals(item.getId())) {
				basketItem.increaseQuantity();
				itemExists = true;
			}
		}
		if (!itemExists) {
			System.out.println("basket item does not exist addding another basket item");
			BasketItem basketItem = new BasketItem(this, item, 1, item.price);
			basketItems.add(basketItem);
		}
	}

	public void removeItem(Item item) {
		for (Iterator<BasketItem> it = basketItems.iterator(); it.hasNext();) {
			BasketItem basketItem = (BasketItem) it.next();
			if (basketItem.item.getId().equals(item.getId())) {
				if (basketItem.quantity > 1) {
					basketItem.decreaseQuantity();
				} else {
					it.remove();
				}
			}
		}
	}

	public void empty() {
		basketItems = new ArrayList<BasketItem>(0);
	}

	@Transient
	public int getItemCount() {
		return basketItems.size();
	}
	
	public static Basket findByUserid(String userid) {
		return find("userid", userid).first();
	}
}