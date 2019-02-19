package com.erasmus.controller;

import com.erasmus.db.model.*;
import com.erasmus.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MockDataController {
    private VotingRepository votingRepository;
    private VotesRepository votesRepository;
    private MealRepository mealRepository;
    private UserRepository userRepository;
    private SuggestionRepository suggestionRepository;
    private CurrentVotingRepository currentVotingRepository;

    @Autowired
    public MockDataController(VotingRepository votingRepository, VotesRepository votesRepository, MealRepository mealRepository, UserRepository userRepository, SuggestionRepository suggestionRepository, CurrentVotingRepository currentVotingRepository) {
        this.votingRepository = votingRepository;
        this.votesRepository = votesRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.suggestionRepository = suggestionRepository;
        this.currentVotingRepository = currentVotingRepository;
//        testValueCreation();
    }


    @GetMapping("/create")
    private String create() {
//        testValueCreation();
        return "index";
    }

    private void testValueCreation() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC"); // 12345
        userRepository.save(admin);

        Voter voter1 = new Voter();
        voter1.setUsername("voter1");
        voter1.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC"); // 12345
        voter1.setChipId("voter1chip");
        voter1.setName("Philip Portner");
        userRepository.save(voter1);

        Voter voter2 = new Voter();
        voter2.setUsername("voter2");
        voter2.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC"); // 12345
        voter2.setChipId("voter2chip");
        voter2.setName("Amanda Sanders");
        userRepository.save(voter2);

        Voter voter3 = new Voter();
        voter3.setUsername("voter3");
        voter3.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC"); // 12345
        voter3.setChipId("voter3chip");
        voter3.setName("William Reed");
        userRepository.save(voter3);

        Meal meal1 = new Meal();
        meal1.setName("Thai Peanut Chicken");
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
        meal1.setPictureUrl("meal-1.jpg");
        mealRepository.save(meal1);

        Meal meal2 = new Meal();
        meal2.setName("Pork Chops Romano in Lemon-Butter Sauce");
        meal2.setDescription("INGREDIENTS:\n" +
                "Lorem\n" +
                "Ipsum\n" +
                "Dolor Sit\n" +
                "Amet\n" +
                "\n" +
                "ALERGENS: \n" +
                "consectetur adipiscing (1)\n" +
                "elit sed (7)\n" +
                "do eiusmod tempor (3)\n");
        mealRepository.save(meal2);

        Meal meal3 = new Meal();
        meal3.setName("Fruit Salad with Grilled Chicken and Citrus Poppy Seed Dressing");
        meal3.setDescription("INGREDIENTS:\n" +
                "Lorem\n" +
                "Ipsum\n" +
                "Dolor Sit\n" +
                "Amet\n" +
                "\n" +
                "ALERGENS: \n" +
                "consectetur adipiscing (1)\n" +
                "elit sed (7)\n" +
                "do eiusmod tempor (3)\n");
        meal3.setPictureUrl("https://spoonacular.com/recipeImages/530106-556x370.jpg");
        meal3.setPictureUrl("meal-3.jpg");
        mealRepository.save(meal3);

        Meal meal4 = new Meal();
        meal4.setName("Smoked Salmon Carbonara with Lemon and Dill");
        meal4.setDescription("INGREDIENTS:\n" +
                "Lorem\n" +
                "Ipsum\n" +
                "Dolor Sit\n" +
                "Amet\n" +
                "\n" +
                "ALERGENS: \n" +
                "consectetur adipiscing (1)\n" +
                "elit sed (7)\n" +
                "do eiusmod tempor (3)\n");
        meal4.setPictureUrl("https://spoonacular.com/recipeImages/783868-556x370.jpg");
        meal4.setPictureUrl("meal-4.jpg");
        mealRepository.save(meal4);


        Meal meal5 = new Meal();
        meal5.setName("Tofu Kabobs with Barbecue Sauce");
        meal5.setDescription("INGREDIENTS:\n" +
                "Lorem\n" +
                "Ipsum\n" +
                "Dolor Sit\n" +
                "Amet\n" +
                "\n" +
                "ALERGENS: \n" +
                "consectetur adipiscing (1)\n" +
                "elit sed (7)\n" +
                "do eiusmod tempor (3)\n");
        meal5.setPictureUrl("imageUrl2");
        meal5.setPictureUrl("meal-5.jpg");
        mealRepository.save(meal5);

        currentVotingRepository.save(new CurrentVoting());

        suggestionRepository.save(new Suggestion("Golden Beet & Beet Greens Pasta W/ Ricotta and Feta Cheese", voter1));
        suggestionRepository.save(new Suggestion("Healthy Chicken Burgers with Spinach Basil Pesto & Mozzarella", voter2));
        suggestionRepository.save(new Suggestion("Grilled chicken with chilli & sesame seeds", voter2));
        suggestionRepository.save(new Suggestion("Steak Sandwiches with Caramelized Peppers, Onions and Garlic", voter3));
    }

}
