package com.erasmus.service;

import com.erasmus.db.model.Meal;
import com.erasmus.db.model.Vote;
import com.erasmus.db.model.Voter;
import com.erasmus.db.model.Voting;
import com.erasmus.db.repository.CurrentVotingRepository;
import com.erasmus.db.repository.VoterRepository;
import com.erasmus.db.repository.VotesRepository;
import com.erasmus.dto.MealDto;
import com.erasmus.dto.RaspberryDto;
import com.erasmus.dto.VotingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaspberryService {
    CurrentVotingRepository currentVotingRepository;
    VoterRepository voterRepository;
    VotesRepository votesRepository;
    UtilityService utilityService;

    @Autowired
    public RaspberryService(CurrentVotingRepository currentVotingRepository, VoterRepository voterRepository, VotesRepository votesRepository, UtilityService utilityService) {
        this.currentVotingRepository = currentVotingRepository;
        this.voterRepository = voterRepository;
        this.votesRepository = votesRepository;
        this.utilityService = utilityService;
    }


    public RaspberryDto getCurrentVoting() {
        Voting voting = currentVotingRepository.findById(1).getVoting();
        if (voting == null) {
            return null;
        }

        Meal meal = voting.getMeal();
        return new RaspberryDto(
                new VotingDto(String.valueOf(voting.getId()), voting.getDateOfTheMeal().getTime(), voting.getMeal().getName(), utilityService.getRatingOfVoting(voting), String.valueOf(voting.getVotes().size())),
                new MealDto(String.valueOf(meal.getId()), meal.getName(), meal.getPictureUrl(), meal.getDescription(), utilityService.getRatingOfMeal(meal)));
    }

    public RaspberryDto vote(String authorization, String rating) {
        Voting voting = currentVotingRepository.findById(1).getVoting();
        if (voting == null) {
            return null;
        }

        Voter voter = voterRepository.findByChipId(authorization);
        Meal meal = voting.getMeal();

        List<Vote> userVotes = voter.getVotes();
        for (Vote userVote : userVotes) {
            if (userVote.getVoting().getId() == voting.getId()) {
                userVote.setRating(Integer.parseInt(rating));
                votesRepository.save(userVote);
                return new RaspberryDto(
                        new VotingDto(String.valueOf(voting.getId()), voting.getDateOfTheMeal().getTime(), voting.getMeal().getName(), utilityService.getRatingOfVoting(voting), String.valueOf(voting.getVotes().size())),
                        new MealDto(String.valueOf(meal.getId()), meal.getName(), meal.getPictureUrl(), meal.getDescription(), utilityService.getRatingOfMeal(meal)));
            }
        }

        votesRepository.save(new Vote(Integer.parseInt(rating), currentVotingRepository.findById(1).getVoting(), voter));

        System.out.println("aaaa");
        return new RaspberryDto(
                new VotingDto(String.valueOf(voting.getId()), voting.getDateOfTheMeal().getTime(), voting.getMeal().getName(), utilityService.getRatingOfVoting(voting), String.valueOf(voting.getVotes().size())),
                new MealDto(String.valueOf(meal.getId()), meal.getName(), meal.getPictureUrl(), meal.getDescription(), utilityService.getRatingOfMeal(meal)));
    }
}
