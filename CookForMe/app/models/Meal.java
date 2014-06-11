package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.swing.text.DateFormatter;

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
    
    public String comment;
    public Blob image;
    public String imagePath;
    public Date fromDate;
    public Date tillDate;

    public Meal(String name, String ingredients,Category category,String comment, Blob image) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
        this.comment = comment;
        this.image = image;
    }
    
    public Meal(String name, String ingredients,Category category,String fromDate,String tillDate) {
    	SimpleDateFormat temp = new SimpleDateFormat("yyyy/MM/dd");
    	try {
			this.fromDate=temp.parse(fromDate);
			this.tillDate=temp.parse(tillDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
    }
    
    public Meal(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
    
    public Meal(String name) {
        this.name = name;
    }

    
    public static List<Meal> findByName(String name) {
        return find("byNameLike", "%" + name + "%").fetch();
    }
    
    public static List<Meal> findByIngredients(String ing) {
        return find("byIngredientsLike", "%" + ing + "%").fetch();
    }
    
    public static List<Meal> findMeals(String name, String category, String ing, String from, String till) {
    	SimpleDateFormat temp = new SimpleDateFormat("yyyy/MM/dd");
    	Date fromDate = new Date(), tillDate = new Date();
    	try {
    		if (from.isEmpty())
    			fromDate = temp.parse("1970/1/1");
    		else 
    			fromDate = temp.parse(from);
    		if (till.isEmpty())
    			tillDate = temp.parse("9999/12/31");
    		else
    			tillDate = temp.parse(till);
    		
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
        return find("SELECT m FROM Meal m "+
        		    "WHERE LOWER(m.name) LIKE ? AND LOWER(m.category.name) LIKE ? AND LOWER(m.ingredients) LIKE ? " +
        		    "AND m.fromDate >= ? AND m.tillDate <= ?",
        		    "%"+name.toLowerCase()+"%", "%"+category.toLowerCase()+"%", "%"+ing.toLowerCase()+"%", 
        		    fromDate, tillDate).fetch();
    }
}
