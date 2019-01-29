package com.erasmus.service;

import com.erasmus.db.model.*;
import com.erasmus.db.repository.*;
import com.erasmus.dto.MealDeletionDto;
import com.erasmus.dto.MealDto;
import com.erasmus.dto.SuggestionDto;
import com.erasmus.dto.VotingDto;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class WebService {
    private UserRepository userRepository;
    private VotingRepository votingRepository;
    private VotesRepository votesRepository;
    private MealRepository mealRepository;
    private CurrentVotingRepository currentVotingRepository;
    private SuggestionRepository suggestionRepository;
    private StorageService storageService;
    private UtilityService utilityService;

    @Autowired
    public WebService(UserRepository userRepository,
                      VotingRepository votingRepository,
                      VotesRepository votesRepository,
                      CurrentVotingRepository currentVotingRepository,
                      MealRepository mealRepository,
                      SuggestionRepository suggestionRepository,
                      StorageService storageService,
                      UtilityService utilityService) {
        this.userRepository = userRepository;
        this.votingRepository = votingRepository;
        this.votesRepository = votesRepository;
        this.mealRepository = mealRepository;
        this.currentVotingRepository = currentVotingRepository;
        this.suggestionRepository = suggestionRepository;
        this.storageService = storageService;
        this.utilityService = utilityService;
    }

    public String getVote() {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Voting voting = currentVotingRepository.findById(1).getVoting();
        if (voting == null) return null;

        List<Vote> userVotes = user.getVotes();
        for (Vote userVote : userVotes) {
            if (userVote.getVoting().getId() == voting.getId()) {
                return String.valueOf(userVote.getRating());
            }
        }
        return null;
    }

    public VotingDto vote(int rating) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Voting voting = currentVotingRepository.findById(1).getVoting();
        List<Vote> userVotes = user.getVotes();
        for (Vote userVote : userVotes) {
            if (userVote.getVoting().getId() == voting.getId()) {
                userVote.setRating(rating);
                votesRepository.save(userVote);
                return getCurrentVoting();
            }
        }

        votesRepository.save(new Vote(rating, voting, user));

        return getCurrentVoting();
    }

    public VotingDto getCurrentVoting() {
        Voting voting = currentVotingRepository.findById(1).getVoting();
        if (voting == null) {
            return null;
        }

        return new VotingDto(String.valueOf(voting.getId()), voting.getDateOfTheMeal().getTime(), voting.getMeal().getName(), utilityService.getRatingOfVoting(voting), String.valueOf(voting.getVotes().size()));
    }

    public VotingDto createVoting(String mealId) {
        Voting voting = new Voting(new Date(), mealRepository.findById(Long.parseLong(mealId)));
        votingRepository.save(voting);
        CurrentVoting currentVoting = currentVotingRepository.findById(1);
        currentVoting.setVoting(voting);
        currentVotingRepository.save(currentVoting);

        return new VotingDto(String.valueOf(voting.getId()), voting.getDateOfTheMeal().getTime(), voting.getMeal().getName(), null, "0");
    }

    public List<VotingDto> endVoting() {
        CurrentVoting currentVoting = currentVotingRepository.findById(1);
        currentVoting.setVoting(null);
        currentVotingRepository.save(currentVoting);

        return getVotingHistory(null);
    }

    public List<VotingDto> getVotingHistory(String amount) {
        List<Voting> votings = votingRepository.findAll();
        votings.remove(currentVotingRepository.findById(1).getVoting());
        return utilityService.transformVotingsIntoDto(votings);
    }

    public List<VotingDto> deleteVoting(String id) {
        votingRepository.deleteById(Long.parseLong(id));

        return getVotingHistory(null);
    }

    public void createMeal(String name, String pictureUrl, String description) {
        mealRepository.save(new Meal(name, pictureUrl, description, new Date()));
    }

    public MealDeletionDto deleteMeal(String id) {
        Meal mealToDelete = mealRepository.findById(Long.parseLong(id));
        removeAllVotingsOfMeal(mealToDelete);
        mealRepository.delete(mealToDelete);

        return new MealDeletionDto(getMeals(null), getVotingHistory(null), getCurrentVoting());
    }

    private void removeAllVotingsOfMeal(Meal mealToDelete) {
        List<Voting> mealVotings = mealToDelete.getVotings();
        CurrentVoting currentVoting = currentVotingRepository.findById(1);
        for (Voting voting : mealVotings) {
            if (currentVoting.getVoting() == voting) {
                currentVoting.setVoting(null);
                currentVotingRepository.save(currentVoting);
            }
            votingRepository.delete(voting);
        }
    }

    public List<MealDto> getMeals(String amount) {
        return utilityService.transformMealsIntoDto(mealRepository.findAll());
    }

    public List<SuggestionDto> getMealSuggestion(String amount) {
        return utilityService.transformSuggestionsIntoDto(suggestionRepository.findAll());
    }

    public void createMealSuggestion(String mealName) {
        suggestionRepository.save(new Suggestion(mealName, userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    public List<SuggestionDto> deleteMealSuggestion(String id) {
        suggestionRepository.deleteById(Long.parseLong(id));

        return utilityService.transformSuggestionsIntoDto(suggestionRepository.findAll());
    }

    public String uploadPicture(MultipartFile picture) throws IOException {
        String filename = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(48), FilenameUtils.getExtension(picture.getOriginalFilename()));
        storageService.storePicture(picture, filename);

        return filename;
    }
}
