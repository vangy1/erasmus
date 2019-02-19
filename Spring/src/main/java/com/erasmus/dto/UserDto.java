package com.erasmus.dto;

public class UserDto {
    private String name;
    private String currentVote;

    public UserDto(String name, String currentVote) {
        this.name = name;
        this.currentVote = currentVote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public void setCurrentVote(String currentVote) {
        this.currentVote = currentVote;
    }
}
