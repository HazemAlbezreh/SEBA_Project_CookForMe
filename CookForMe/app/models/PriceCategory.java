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
public class PriceCategory extends Model {

    public String name;
//    @OneToMany(mappedBy ="category" ,cascade= CascadeType.ALL)
  //  public List<Meal> meals;

    public PriceCategory(String name) {
        this.name = name;
 
    }
    
    public static PriceCategory findByName(String name) {
        List<PriceCategory> categories = PriceCategory.findAll();
        for (PriceCategory cat : categories) {
        	String s1=name.trim().toLowerCase();
        	String s2=cat.name.trim().toLowerCase();
        	if (s1.equals(s2)){
        		return cat;
        	}
        }
        return null;
    }

}
