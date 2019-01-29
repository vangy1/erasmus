package com.erasmus.dto;

public class SuggestionDto {
    private String id;
    private String mealName;
    private String username;

    public SuggestionDto(String id, String mealName, String username) {
        this.id = id;
        this.mealName = mealName;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
