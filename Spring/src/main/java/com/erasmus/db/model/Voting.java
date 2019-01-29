package com.erasmus.db.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voting_id")
    private long id;

    private Date dateOfTheMeal = new Date();

    @OneToMany(mappedBy = "voting")
    private List<Vote> votes;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @OneToOne(mappedBy = "voting")
    private CurrentVoting currentVoting;

    public Voting() {
    }

    public Voting(Date dateOfTheMeal, Meal meal) {
        this.dateOfTheMeal = dateOfTheMeal;
        this.meal = meal;
    }

    public Date getDateOfTheMeal() {
        return dateOfTheMeal;
    }

    public void setDateOfTheMeal(Date dateOfTheMeal) {
        this.dateOfTheMeal = dateOfTheMeal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
