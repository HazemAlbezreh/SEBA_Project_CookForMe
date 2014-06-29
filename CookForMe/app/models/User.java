package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class User extends Model {

    public @NotEmpty
    String email;
    public @NotEmpty
    String password;
    public @NotEmpty
    String name;
    
    
    public User(String email,String password,String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    public static User findByEmail(String email) {
        return find("byEmail", email).first();
    }

    public static User findByEmailAndPassword(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
