package models;

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

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User findByEmailAndPassword(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
