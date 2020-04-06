package main.java.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "table_name")
@Proxy(lazy=false)
public class User {
    @Id
    @GenericGenerator(name="123" , strategy="increment")
    @GeneratedValue(generator="123")
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="login")
    private String login;
    @Column(name="password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
