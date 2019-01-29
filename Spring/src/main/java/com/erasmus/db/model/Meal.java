package com.erasmus.db.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private long id;
    private String name;

    @Lob
    private String description;
    private String pictureUrl;
    private Date creationDate = new Date(System.currentTimeMillis());

    @OneToMany(mappedBy = "meal")
    private List<Voting> votings;

    public Meal() {
    }

    public Meal(String name, String pictureUrl, String description, Date creationDate) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Voting> getVotings() {
        return votings;
    }

    public void setVotings(List<Voting> votings) {
        this.votings = votings;
    }
}
