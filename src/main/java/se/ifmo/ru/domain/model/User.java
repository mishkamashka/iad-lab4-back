package se.ifmo.ru.domain.model;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    private String name;

    private User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = DigestUtils.md5Hex(password);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(DigestUtils.md5Hex(password));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
