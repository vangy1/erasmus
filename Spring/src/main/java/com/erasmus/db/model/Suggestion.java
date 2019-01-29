package com.erasmus.db.model;

import javax.persistence.*;

@Entity
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String mealName;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Suggestion() {
    }

    public Suggestion(String mealName, User user) {
        this.mealName = mealName;
        this.user = user;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
