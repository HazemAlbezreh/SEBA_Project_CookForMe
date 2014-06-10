package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import play.db.jpa.Blob;
import play.db.jpa.Model;
import play.db.jpa.GenericModel.JPAQuery;

@Entity
public class Category extends Model {

    public String name;
//    @OneToMany(mappedBy ="category" ,cascade= CascadeType.ALL)
  //  public List<Meal> meals;

    public Category(String name) {
        this.name = name;

    }
    
    public static Category findByName(String name) {
        List<Category> categories = Category.findAll();
        for (Category cat : categories) {
        	String s1=name.trim().toLowerCase();
        	String s2=cat.name.trim().toLowerCase();
        	if (s1.equals(s2)){
        		return cat;
        	}
        }
        return null;
    }

}
