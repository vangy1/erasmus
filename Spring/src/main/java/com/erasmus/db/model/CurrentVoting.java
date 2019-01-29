package com.erasmus.db.model;

import javax.persistence.*;

@Entity
public class CurrentVoting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "voting_id")
    private Voting voting;

    public CurrentVoting() {
    }

    public CurrentVoting(Voting voting) {
        this.voting = voting;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }
}
