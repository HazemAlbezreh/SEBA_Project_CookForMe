package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import play.db.jpa.Model;

/** 
 * @author Akshit
 */
@Entity
@Table(name="item")
public class Item extends Model {
	@Column(name = "name", nullable = false)
	public String name;
	@Column(name = "description")
	public String description;
	@Column(name = "price", nullable = false, precision = 22, scale = 0)
	public double price;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	//@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	public List<BasketItem> basketItems = new ArrayList<BasketItem>(0);
 
	public Item(String name, String description, double price) {
		this.description = description;
		this.name = name;
		this.price = price;
	}
	
	public void addBasketItem(BasketItem basketItem) {
		basketItems.add(basketItem);
		
	}
	public static List<Item> findByName(String name) {
        return find("byNameLike", "%" + name + "%").fetch();
    }
	
}
