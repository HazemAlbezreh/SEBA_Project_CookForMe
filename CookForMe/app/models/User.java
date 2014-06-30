package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="user")
public class User extends Model {

    public @NotEmpty
    String email;
    public @NotEmpty
    String password;
    public @NotEmpty
    String name;
    
    @Column(length =1000000)
    public Basket basket;
    
    public User(String email,String password,String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    public void addBasket(Basket basket){
    	System.out.println("adding in USER Class="+basket);
    	this.basket = basket;
    }
    
    public static User findByEmail(String email) {
        return find("byEmail", email).first();
    }

    public static User findByEmailAndPassword(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
