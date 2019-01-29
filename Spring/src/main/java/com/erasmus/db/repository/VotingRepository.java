package com.erasmus.db.repository;

import com.erasmus.db.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotingRepository extends JpaRepository<Voting, Long> {
    Voting findById(long id);

    List<Voting> findAll();
}

