package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Blob;
import play.db.jpa.Model;
import play.db.jpa.GenericModel.JPAQuery;

@Entity
public class Meal extends Model {

    public String name;
    public String ingredients;
    
    // bi-directional one-to-many, owning side
    @ManyToOne( cascade= CascadeType.ALL)
    public Category category;
    
    @ManyToOne( cascade= CascadeType.ALL)
    public PriceCategory priceCategory;
    
    @ManyToOne( cascade= CascadeType.ALL)
    public User user;
    
    public String comment;
    public Blob image;
    public String imagePath;
    public Date fromDate;
    public Date tillDate;
    public int numOrderedtimes;
    
    
    public Meal(String name, String ingredients,Category category,PriceCategory price,String fromDate,String tillDate, User user) {
    	SimpleDateFormat temp = new SimpleDateFormat("yyyy/MM/dd");
    	try {
			this.fromDate=temp.parse(fromDate);
			this.tillDate=temp.parse(tillDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
        this.priceCategory = price;
        this.user = user;
        this.numOrderedtimes = 0;
    }
    

    public static List<Meal> findByName(String name) {
        return find("byNameLike", "%" + name + "%").fetch();
    }
    
    public static List<Meal> findByIngredients(String ing) {
        return find("byIngredientsLike", "%" + ing + "%").fetch();
    }
    
    public static List<Meal> findMeals(String name, String category, String ing, String user) {
        return find("SELECT m FROM Meal m "+
        		    "WHERE UPPER(m.name) LIKE ? AND UPPER(m.category.name) LIKE ? AND UPPER(m.ingredients) LIKE ? AND UPPER(m.user.name) LIKE ?",
        		    "%"+name.toUpperCase()+"%", "%"+category.toUpperCase()+"%", "%"+ing.toUpperCase()+"%", "%"+user.toUpperCase()+"%").fetch();
    }
    
}
