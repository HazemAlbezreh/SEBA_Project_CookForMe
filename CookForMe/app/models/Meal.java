package models;

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
    
    public String comment;
    public Blob image;
    public String imagePath;

    public Meal(String name, String ingredients,Category category,String comment, Blob image) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
        this.comment = comment;
        this.image = image;
    }
    
    public Meal(String name, String ingredients,Category category) {
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
}
