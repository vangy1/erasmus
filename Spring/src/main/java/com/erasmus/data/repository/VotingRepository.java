package com.erasmus.data.repository;

import com.erasmus.data.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotingRepository extends JpaRepository<Voting, Long> {
    Voting findById(String id);

    List<Voting> findAll();
}

