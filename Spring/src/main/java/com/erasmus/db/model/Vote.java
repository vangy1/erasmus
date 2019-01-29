package com.erasmus.db.model;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "voting_id", nullable = false)
    private Voting voting;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Vote() {
    }

    public Vote(int rating, Voting voting, User user) {
        this.rating = rating;
        this.voting = voting;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
