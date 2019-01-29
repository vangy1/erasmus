package com.erasmus.db.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id")
    private long id;
    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Vote> votes;

    @OneToMany(mappedBy = "user")
    private List<Suggestion> suggestions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
