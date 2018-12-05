package com.erasmus;

import com.erasmus.data.model.*;
import com.erasmus.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    private VotingRepository votingRepository;
    private VotesRepository votesRepository;
    private MealRepository mealRepository;
    private UserRepository userRepository;
    private SuggestionRepository suggestionRepository;

    @Autowired
    public TestController(VotingRepository votingRepository, VotesRepository votesRepository, MealRepository mealRepository, UserRepository userRepository, SuggestionRepository suggestionRepository) {
        this.votingRepository = votingRepository;
        this.votesRepository = votesRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.suggestionRepository = suggestionRepository;
    }

    @GetMapping("/")
    private String index() {
        return "index";
    }

    private void testValueCreation() {
        Admin user1 = new Admin();
        user1.setUsername("username1");
        user1.setPassword("password1");
        userRepository.save(user1);

        Voter user2 = new Voter();
        user2.setUsername("username2");
        user2.setPassword("password2");
        userRepository.save(user2);

        Suggestion suggestion1 = new Suggestion(user1);
        suggestion1.setMeal("meal1");
        suggestionRepository.save(suggestion1);

        Suggestion suggestion2 = new Suggestion(user1);
        suggestion2.setMeal("meal2");
        suggestionRepository.save(suggestion2);

        Suggestion suggestion3 = new Suggestion(user2);
        suggestion3.setMeal("meal3");
        suggestionRepository.save(suggestion3);

        Meal meal1 = new Meal();
        meal1.setName("name1");
        meal1.setShortname("shortname1");
        meal1.setDescription("description1");
        meal1.setImageUrl("imageUrl1");
        meal1.setTimesInVoting(1);
        mealRepository.save(meal1);

        Meal meal2 = new Meal();
        meal2.setName("name2");
        meal2.setShortname("shortname2");
        meal2.setDescription("description2");
        meal2.setImageUrl("imageUrl2");
        meal2.setTimesInVoting(2);
        mealRepository.save(meal2);

        Meal meal3 = new Meal();
        meal3.setName("name3");
        meal3.setShortname("shortname3");
        meal3.setDescription("description3");
        meal3.setImageUrl("imageUrl3");
        meal3.setTimesInVoting(3);
        mealRepository.save(meal3);

        Voting voting1 = new Voting(meal1);
        votingRepository.save(voting1);

        Voting voting2 = new Voting(meal2);
        votingRepository.save(voting2);

        Voting voting3 = new Voting(meal3);
        votingRepository.save(voting3);

        Vote vote1 = new Vote(user1, voting2);
        vote1.setRating(1);
        votesRepository.save(vote1);

        Vote vote2 = new Vote(user1, voting3);
        vote2.setRating(2);
        votesRepository.save(vote2);

        Vote vote3 = new Vote(user1, voting1);
        vote3.setRating(3);
        votesRepository.save(vote3);

        Vote vote4 = new Vote(user1, voting1);
        vote4.setRating(4);
        votesRepository.save(vote4);

        Vote vote5 = new Vote(user2, voting1);
        vote5.setRating(5);
        votesRepository.save(vote5);
    }

}
