package com.erasmus.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String meal;
    private Date dateOfTheMeal = new Date(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Suggestion() {
    }

    public Suggestion(User user) {
        this.user = user;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public Date getDateOfTheMeal() {
        return dateOfTheMeal;
    }

    public void setDateOfTheMeal(Date dateOfTheMeal) {
        this.dateOfTheMeal = dateOfTheMeal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
