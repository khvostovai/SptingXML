package ru.kortez.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "login")
    private String login;
    @Column (name = "name")
    private String name;
    @Column (name = "surname")
    private String surname;
    @Column (name = "passwd")
    private String passwd;
    @Column (name = "permission")
    private boolean permission;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "author")
    private List<Theme> themes;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "author")
    private List<Message> messages;

    public User(){}

    public User(String login, String name, String surname, String passwd, boolean permission) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.passwd = passwd;
        this.permission = permission;
        this.themes = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void addTheme(Theme theme){
        if(theme != null)
            this.themes.add(theme);
    }

    public void removeTeme(Theme theme) {
        if(theme != null)
            this.themes.remove(theme);
    }

    public void addMessages(Message message) {
        if (message != null)
            this.messages.add(message);
    }

    public void removeMessage(Message message) {
        if(message != null)
            this.messages.remove(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", passwd='" + passwd + '\'' +
                ", permission=" + permission +
                ", themes=" + themes +
                ", messages=" + messages +
                '}';
    }
}
