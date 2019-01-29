package com.erasmus.dto;

public class RaspberryDto {
    private VotingDto currentVoting;
    private MealDto mealDto;

    public RaspberryDto(VotingDto currentVoting, MealDto mealDto) {
        this.currentVoting = currentVoting;
        this.mealDto = mealDto;
    }

    public VotingDto getCurrentVoting() {
        return currentVoting;
    }

    public void setCurrentVoting(VotingDto currentVoting) {
        this.currentVoting = currentVoting;
    }

    public MealDto getMealDto() {
        return mealDto;
    }

    public void setMealDto(MealDto mealDto) {
        this.mealDto = mealDto;
    }
}
