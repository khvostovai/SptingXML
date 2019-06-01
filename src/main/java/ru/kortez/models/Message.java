package ru.kortez.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "messages")
public class Message {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "message")
    private String message;

    @Column (name = "date")
    private Date date;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "theme_id")
    private Theme theme;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "author_id")
    private User author;

    public Message() {
    }

    public Message(String text, Theme theme, User author) {
        this.message = text;
        this.date = new Date();
        this.theme = theme;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Theme: "+ theme.getTitle() +" Message: " + message + " Author: " + author.getLogin();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
