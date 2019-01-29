package com.erasmus.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Voter extends User {
    private String name;
    @Column(unique = true)
    private String chipId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }
}
