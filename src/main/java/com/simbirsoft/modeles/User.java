package com.simbirsoft.modeles;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "SELECT c FROM User c"),
        @NamedQuery(name = "User.findByName", query = "SELECT c FROM User c WHERE c.login=:login"),
        @NamedQuery(name = "User.authorizeSearch", query = "SELECT c FROM User c WHERE c.login=:login And c.password=:password"),
})
@Table(name = "accounts")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    private String role;

    public User(){

    }

    public User(String login){
        this.login=login;
    }

    public User(String login, String password, String role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
