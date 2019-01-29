package com.erasmus.service;

import com.erasmus.db.model.Meal;
import com.erasmus.db.model.Suggestion;
import com.erasmus.db.model.Vote;
import com.erasmus.db.model.Voting;
import com.erasmus.dto.MealDto;
import com.erasmus.dto.SuggestionDto;
import com.erasmus.dto.VotingDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UtilityService {
    @Value("${url.backend}")
    String backendUrl;

    public List<SuggestionDto> transformSuggestionsIntoDto(List<Suggestion> suggestions) {
        List<SuggestionDto> suggestionsDto = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            suggestionsDto.add(new SuggestionDto(String.valueOf(suggestion.getId()), suggestion.getMealName(), suggestion.getUser().getUsername()));
        }
        Collections.reverse(suggestionsDto);
        return suggestionsDto;
    }

    public List<VotingDto> transformVotingsIntoDto(List<Voting> votings) {
        List<VotingDto> votingsDto = new ArrayList<>();
        for (Voting voting : votings) {
            votingsDto.add(new VotingDto(String.valueOf(voting.getId()), voting.getDateOfTheMeal().getTime(), voting.getMeal().getName(), getRatingOfVoting(voting), String.valueOf(voting.getVotes().size())));
        }
        votingsDto.sort((voting1, voting2) -> {
            if (voting1.getDateOfTheMeal() == voting2.getDateOfTheMeal()) return 0;
            return voting1.getDateOfTheMeal() > voting2.getDateOfTheMeal() ? -1 : 1;
        });
        return votingsDto;
    }

    public String getRatingOfVoting(Voting voting) {
        String rating = null;
        List<Vote> votes = voting.getVotes();
        if (!votes.isEmpty()) {
            double ratingSum = 0;
            for (Vote vote : votes) {
                ratingSum += vote.getRating();
            }

            rating = String.valueOf(ratingSum / votes.size());
        }
        return rating;
    }

    public List<MealDto> transformMealsIntoDto(List<Meal> meals) {
        List<MealDto> mealsDto = new ArrayList<>();
        for (Meal meal : meals) {
            mealsDto.add(new MealDto(String.valueOf(meal.getId()), meal.getName(), meal.getPictureUrl() != null && !meal.getPictureUrl().equals("") ? backendUrl + "/meal-image/" + meal.getPictureUrl() : null, meal.getDescription(), getRatingOfMeal(meal)));
        }

        mealsDto.sort((meal1, meal2) -> {
            double meal1Rating = meal1.getRating() == null ? 0 : Double.valueOf(meal1.getRating());
            double meal2Rating = meal2.getRating() == null ? 0 : Double.valueOf(meal2.getRating());
            if (meal1Rating == meal2Rating) {
                return Integer.parseInt(meal1.getId()) > Integer.parseInt(meal2.getId()) ? -1 : 1;
            }
            return meal1Rating > meal2Rating ? -1 : 1;
        });
        return mealsDto;
    }

    public String getRatingOfMeal(Meal meal) {
        String mealRating = null;
        double mealRatingSum = 0;
        int votingCount = 0;
        List<Voting> votings = meal.getVotings();
        for (Voting voting : votings) {
            String rating = getRatingOfVoting(voting);
            if (rating != null) {
                mealRatingSum += Double.parseDouble(rating);
                votingCount += 1;
            }
        }
        if (votingCount != 0) {
            mealRating = String.valueOf(mealRatingSum / votingCount);
        }

        return mealRating;
    }
}
