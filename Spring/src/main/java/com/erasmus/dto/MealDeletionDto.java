package com.erasmus.dto;

import java.util.List;

public class MealDeletionDto {
    List<MealDto> meals;
    List<VotingDto> votingHistory;
    VotingDto currentVoting;

    public MealDeletionDto(List<MealDto> meals, List<VotingDto> votingHistory, VotingDto currentVoting) {
        this.meals = meals;
        this.votingHistory = votingHistory;
        this.currentVoting = currentVoting;
    }

    public List<MealDto> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDto> meals) {
        this.meals = meals;
    }

    public List<VotingDto> getVotingHistory() {
        return votingHistory;
    }

    public void setVotingHistory(List<VotingDto> votingHistory) {
        this.votingHistory = votingHistory;
    }

    public VotingDto getCurrentVoting() {
        return currentVoting;
    }

    public void setCurrentVoting(VotingDto currentVoting) {
        this.currentVoting = currentVoting;
    }
}
