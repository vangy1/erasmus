package com.erasmus.controller;

import com.erasmus.authentication.AuthenticationService;
import com.erasmus.authentication.AuthenticationType;
import com.erasmus.authentication.UnauthorizedException;
import com.erasmus.dto.MealDeletionDto;
import com.erasmus.dto.MealDto;
import com.erasmus.dto.SuggestionDto;
import com.erasmus.dto.VotingDto;
import com.erasmus.service.WebService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class WebController {

    @Value("${url.backend}")
    String backendUrl;

    AuthenticationService authenticationService;
    WebService webService;


    public WebController(AuthenticationService authenticationService, WebService webService) {
        this.authenticationService = authenticationService;
        this.webService = webService;
    }

    @GetMapping("/authentication/info")
    private String getAuthentication() {
        return authenticationService.getAuthentication().toString();
    }

    @GetMapping("/vote")
    private String getVote() {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.VOTER)));
        return webService.getVote();
    }

    private void checkAuthentication(ArrayList allowedRoles) {
        if (!allowedRoles.contains(authenticationService.getAuthentication())) throw new UnauthorizedException();
    }

    @PostMapping("/vote")
    private VotingDto vote(int rating) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.VOTER)));
        return webService.vote(rating);
    }

    @GetMapping("/voting")
    private VotingDto getCurrentVoting() {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.VOTER, AuthenticationType.ADMIN)));
        return webService.getCurrentVoting();
    }

    @PostMapping("/voting/start")
    private VotingDto createVoting(String mealId) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        return webService.createVoting(mealId);
    }

    @PostMapping("/voting/end")
    private List<VotingDto> endVoting() {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        return webService.endVoting();
    }

    @DeleteMapping("/voting")
    private List<VotingDto> deleteVoting(@RequestParam("id") String id) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        return webService.deleteVoting(id);

    }

    @GetMapping("/voting/history")
    private List<VotingDto> getVotingHistory(@RequestParam(value = "amount", required = false) String amount) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.VOTER, AuthenticationType.ADMIN)));
        return webService.getVotingHistory(amount);
    }

    @PostMapping("/meal")
    private void createMeal(@RequestParam("name") String name,
                            @RequestParam("pictureUrl") String pictureUrl,
                            @RequestParam("description") String description) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        webService.createMeal(name, pictureUrl, description);
    }

    @GetMapping("/meal")
    private List<MealDto> getMeals(@RequestParam(value = "amount", required = false) String amount) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.VOTER, AuthenticationType.ADMIN)));
        return webService.getMeals(amount);
    }

    @DeleteMapping("/meal")
    private MealDeletionDto deleteMeal(@RequestParam("id") String id) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        return webService.deleteMeal(id);
    }

    @GetMapping("/suggestion")
    private List<SuggestionDto> getMealSuggestion(@RequestParam(value = "amount", required = false) String amount) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        return webService.getMealSuggestion(amount);
    }

    @PostMapping("/suggestion")
    private void createMealSuggestion(String mealName) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.VOTER)));
        webService.createMealSuggestion(mealName);
    }

    @DeleteMapping("/suggestion")
    private List<SuggestionDto> deleteMealSuggestion(@RequestParam("id") String id) {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        return webService.deleteMealSuggestion(id);
    }

    @PostMapping("/meal/picture")
    private String uploadPicture(@RequestParam("picture") MultipartFile picture) throws IOException {
        checkAuthentication(new ArrayList<>(Arrays.asList(AuthenticationType.ADMIN)));
        return webService.uploadPicture(picture);
    }
}
