package com.erasmus;

import com.erasmus.db.model.*;
import com.erasmus.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@SpringBootApplication
public class ErasmusApplication {
    private VotingRepository votingRepository;
    private VotesRepository votesRepository;
    private MealRepository mealRepository;
    private UserRepository userRepository;
    private SuggestionRepository suggestionRepository;
    private CurrentVotingRepository currentVotingRepository;

    @Autowired
    public ErasmusApplication(VotingRepository votingRepository, VotesRepository votesRepository, MealRepository mealRepository, UserRepository userRepository, SuggestionRepository suggestionRepository, CurrentVotingRepository currentVotingRepository) {
        this.votingRepository = votingRepository;
        this.votesRepository = votesRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.suggestionRepository = suggestionRepository;
        this.currentVotingRepository = currentVotingRepository;
        initialDataCreation();
    }

    private void initialDataCreation() {
        if (currentVotingRepository.findById(1) != null) return;

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC"); // 12345
        userRepository.save(admin);

        Voter voter1 = createVoter("voter1", "voter1chip", "Philip Portner");
        Voter voter2 = createVoter("voter2", "voter2chip", "Amanda Sanders");
        Voter voter3 = createVoter("voter3", "voter3chip", "William Reed");

        currentVotingRepository.save(new CurrentVoting());

        Meal meal1 = createMeal("Thai Peanut Chicken", "meal-1.jpg");
        Meal meal2 = createMeal("Pork Chops in Lemon-Butter Sauce", "meal-2.jpg");
        Meal meal3 = createMeal("Fruit Salad with Grilled Chicken", "meal-3.jpg");
        Meal meal4 = createMeal("Smoked Salmon Carbonara with Lemon and Dill", "meal-4.jpg");
        Meal meal5 = createMeal("Tofu Kabobs with Barbecue Sauce", "meal-5.jpg");

        Voting voting1 = createVoting(meal1, new Date(Long.parseLong("1518994800000")));
        Voting voting2 = createVoting(meal2, new Date(Long.parseLong("1518908400000")));
        Voting voting3 = createVoting(meal3, new Date(Long.parseLong("1518735600000")));
        Voting voting4 = createVoting(meal4, new Date(Long.parseLong("1518562800000")));
        Voting voting5 = createVoting(meal5, new Date(Long.parseLong("1518476400000")));
        Voting voting6 = createVoting(meal2, new Date(Long.parseLong("1518390000000")));

        createVote(voter1, voting1, 3);
        createVote(voter2, voting1, 5);
        createVote(voter1, voting2, 1);
        createVote(voter2, voting2, 4);
        createVote(voter1, voting3, 4);
        createVote(voter2, voting3, 5);
        createVote(voter3, voting3, 5);
        createVote(voter1, voting4, 1);
        createVote(voter2, voting4, 3);
        createVote(voter1, voting5, 1);
        createVote(voter2, voting5, 2);
        createVote(voter1, voting6, 3);
        createVote(voter2, voting6, 2);


        suggestionRepository.save(new Suggestion("Golden Beet & Beet Greens Pasta W/ Ricotta and Feta Cheese", voter1));
        suggestionRepository.save(new Suggestion("Healthy Chicken Burgers with Spinach Basil Pesto & Mozzarella", voter2));
        suggestionRepository.save(new Suggestion("Grilled chicken with chilli & sesame seeds", voter2));
        suggestionRepository.save(new Suggestion("Steak Sandwiches with Caramelized Peppers, Onions and Garlic", voter3));

    }

    private Voter createVoter(String voter22, String voter2chip, String s) {
        Voter voter2 = new Voter();
        voter2.setUsername(voter22);
        voter2.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC"); // 12345
        voter2.setChipId(voter2chip);
        voter2.setName(s);
        userRepository.save(voter2);
        return voter2;
    }

    private Meal createMeal(String mealName, String pictureUrl) {
        Meal meal1 = new Meal();
        meal1.setName(mealName);
        meal1.setDescription("INGREDIENTS:\n" +
                "Lorem\n" +
                "Ipsum\n" +
                "Dolor Sit\n" +
                "Amet\n" +
                "\n" +
                "ALERGENS: \n" +
                "consectetur adipiscing (1)\n" +
                "elit sed (7)\n" +
                "do eiusmod tempor (3)\n");
        meal1.setPictureUrl(pictureUrl);
        mealRepository.save(meal1);
        return meal1;
    }

    private Voting createVoting(Meal meal, Date date) {
        Voting voting = new Voting();
        voting.setDateOfTheMeal(date);
        voting.setMeal(meal);
        votingRepository.save(voting);
        return voting;
    }

    private void createVote(Voter voter1, Voting voting1, int rating) {
        Vote vote1 = new Vote();
        vote1.setUser(voter1);
        vote1.setVoting(voting1);
        vote1.setRating(rating);
        votesRepository.save(vote1);
    }

    public static void main(String[] args) {
        SpringApplication.run(ErasmusApplication.class, args);
    }

    @GetMapping("/")
    private String home() {
        return "index";
    }
}
