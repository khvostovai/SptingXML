package ru.kortez.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "theme")
    private List<Message> messages;

    public Theme() {
    }

    public Theme(String title) {
        this.title = title;
    }

    public Theme(String title, User author) {
        this.title = title;
        this.author = author;
        this.messages = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "theme: " + title + " author: " + author.getLogin();
    }

    public void addMessage(Message message) {
        if (message != null)
            this.messages.add(message);
    }

    public void removeMessage(Message message) {
        if (message != null)
            this.messages.remove(message);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}