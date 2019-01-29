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
    }

    @GetMapping("/")
    private String home() {
        return "index";
    }

    @GetMapping("/create")
    private String create() {
        testValueCreation();
        return "index";
    }

    private void testValueCreation() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC");
        userRepository.save(admin);

        Voter voter1 = new Voter();
        voter1.setUsername("voter1");
        voter1.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC");
        voter1.setChipId("voter1chip");
        voter1.setName("Philip Portner");
        userRepository.save(voter1);

        Voter voter2 = new Voter();
        voter2.setUsername("voter2");
        voter2.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC");
        voter2.setChipId("voter2chip");
        voter2.setName("Amanda Sanders");
        userRepository.save(voter2);

        Voter voter3 = new Voter();
        voter3.setUsername("voter3");
        voter3.setPassword("$2a$10$PPnnLSSEFKTXt0RDrAZy.e8tU4VGj4xDRhIyvRHaJ9t..IYM9uWCC");
        voter3.setChipId("voter3chip");
        voter3.setName("William Reed");
        userRepository.save(voter3);

        Meal meal1 = new Meal();
        meal1.setName("Thai Peanut Chicken");
        meal1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                " Eu volutpat odio facilisis mauris. Donec massa sapien faucibus et molestie ac feugiat. Leo integer malesuada nunc vel risus commodo viverra." +
                " Sem viverra aliquet eget sit amet. Suscipit tellus mauris a diam maecenas sed enim. Egestas congue quisque egestas diam in." +
                " Dui nunc mattis enim ut tellus elementum sagittis vitae et. Eu lobortis elementum nibh tellus molestie." +
                " Etiam dignissim diam quis enim. Dictum fusce ut placerat orci nulla pellentesque dignissim." +
                " Interdum velit euismod in pellentesque. Amet purus gravida quis blandit. Turpis nunc eget lorem dolor sed viverra ipsum nunc.");
        mealRepository.save(meal1);

        Meal meal2 = new Meal();
        meal2.setName("Pork Chops Romano in Lemon-Butter Sauce");
        meal2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                " Eu volutpat odio facilisis mauris. Donec massa sapien faucibus et molestie ac feugiat. Leo integer malesuada nunc vel risus commodo viverra." +
                " Sem viverra aliquet eget sit amet. Suscipit tellus mauris a diam maecenas sed enim. Egestas congue quisque egestas diam in." +
                " Dui nunc mattis enim ut tellus elementum sagittis vitae et. Eu lobortis elementum nibh tellus molestie.");
        mealRepository.save(meal2);

        Meal meal3 = new Meal();
        meal3.setName("Fruit Salad with Grilled Chicken and Citrus Poppy Seed Dressing");
        meal3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                " Eu volutpat odio facilisis mauris. Donec massa sapien faucibus et molestie ac feugiat. Leo integer malesuada nunc vel risus commodo viverra." +
                " Sem viverra aliquet eget sit amet. Suscipit tellus mauris a diam maecenas sed enim. Egestas congue quisque egestas diam in." +
                " Dui nunc mattis enim ut tellus elementum sagittis vitae et. Eu lobortis elementum nibh tellus molestie." +
                " Etiam dignissim diam quis enim. Dictum fusce ut placerat orci nulla pellentesque dignissim.");
        meal3.setPictureUrl("https://spoonacular.com/recipeImages/530106-556x370.jpg");
        mealRepository.save(meal3);

        Meal meal4 = new Meal();
        meal4.setName("Smoked Salmon Carbonara with Lemon and Dill");
        meal4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                " Eu volutpat odio facilisis mauris. Donec massa sapien faucibus et molestie ac feugiat. Leo integer malesuada nunc vel risus commodo viverra." +
                " Sem viverra aliquet eget sit amet. Suscipit tellus mauris a diam maecenas sed enim. Egestas congue quisque egestas diam in." +
                " Dui nunc mattis enim ut tellus elementum sagittis vitae et. Eu lobortis elementum nibh tellus molestie." +
                " Etiam dignissim diam quis enim. Dictum fusce ut placerat orci nulla pellentesque dignissim. Interdum velit euismod in pellentesque." +
                " Amet purus gravida quis blandit. Turpis nunc eget lorem dolor sed viverra ipsum nunc.\n" +
                "\n" +
                "Elementum nibh tellus molestie nunc non. Et netus et malesuada fames ac. Convallis posuere morbi leo urna molestie at." +
                " Libero nunc consequat interdum varius sit. Ante in nibh mauris cursus mattis molestie a iaculis at." +
                " Eget egestas purus viverra accumsan in nisl nisi scelerisque eu. Nunc mi ipsum faucibus vitae aliquet nec ullamcorper sit." +
                " Duis ut diam quam nulla porttitor massa id. Est lorem ipsum dolor sit. Massa eget egestas purus viverra accumsan.");
        meal4.setPictureUrl("https://spoonacular.com/recipeImages/783868-556x370.jpg");
        mealRepository.save(meal4);


        Meal meal5 = new Meal();
        meal5.setName("Tofu Kabobs with Cherry Barbecue Sauce");
        meal5.setDescription("Elementum nibh tellus molestie nunc non. Et netus et malesuada fames ac. Convallis posuere morbi leo urna molestie at." +
                " Libero nunc consequat interdum varius sit. Ante in nibh mauris cursus mattis molestie a iaculis at." +
                " Eget egestas purus viverra accumsan in nisl nisi scelerisque eu. Nunc mi ipsum faucibus vitae aliquet nec ullamcorper sit." +
                " Duis ut diam quam nulla porttitor massa id. Est lorem ipsum dolor sit. Massa eget egestas purus viverra accumsan.");
        meal5.setPictureUrl("imageUrl2");
        mealRepository.save(meal2);

        currentVotingRepository.save(new CurrentVoting());

        suggestionRepository.save(new Suggestion("Golden Beet & Beet Greens Pasta W/ Ricotta and Feta Cheese", voter1));
        suggestionRepository.save(new Suggestion("Healthy Chicken Burgers with Spinach Basil Pesto & Mozzarella", voter2));
        suggestionRepository.save(new Suggestion("Grilled chicken with chilli & sesame seeds", voter2));
        suggestionRepository.save(new Suggestion("Steak Sandwiches with Caramelized Peppers, Onions and Garlic", voter3));
    }

}
