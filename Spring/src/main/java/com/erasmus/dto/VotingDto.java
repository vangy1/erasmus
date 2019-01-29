package com.erasmus.dto;

public class VotingDto {
    private String id;
    private long dateOfTheMeal;
    private String mealName;
    private String rating;
    private String voteCount;

    public VotingDto(String id, long dateOfTheMeal, String mealName, String rating, String voteCount) {
        this.id = id;
        this.dateOfTheMeal = dateOfTheMeal;
        this.mealName = mealName;
        this.rating = rating;
        this.voteCount = voteCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDateOfTheMeal() {
        return dateOfTheMeal;
    }

    public void setDateOfTheMeal(long dateOfTheMeal) {
        this.dateOfTheMeal = dateOfTheMeal;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }
}
