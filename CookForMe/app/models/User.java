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
    public @NotEmpty
    String name;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.name = email.split("@")[0];
    }

    public static User findByEmailAndPassword(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
