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
import com.erasmus.dto.UserDto;
import com.erasmus.dto.VotingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaspberryService {
    private CurrentVotingRepository currentVotingRepository;
    private VoterRepository voterRepository;
    private VotesRepository votesRepository;
    private UtilityService utilityService;

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
        return getRaspberryDto(voting, meal);
    }

    private RaspberryDto getRaspberryDto(Voting voting, Meal meal) {
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
                return getRaspberryDto(voting, meal);
            }
        }

        votesRepository.save(new Vote(Integer.parseInt(rating), currentVotingRepository.findById(1).getVoting(), voter));

        return getRaspberryDto(voting, meal);
    }

    public UserDto getUser(String authorization) {
        Voter voter = voterRepository.findByChipId(authorization);
        Vote vote = null;
        for (Vote userVote : voter.getVotes()) {
            if (userVote.getVoting().getId() == currentVotingRepository.findById(1).getVoting().getId()) {
                vote = userVote;
            }
        }
        return new UserDto(voter.getName(), vote != null ? String.valueOf(vote.getRating()) : null);
    }
}
