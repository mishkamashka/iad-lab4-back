package se.ifmo.ru.domain.model;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String login;
    private String password;
    private String name;

    private User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = DigestUtils.md5Hex(password);
    }

    public boolean checkPassword(String password){
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
