package com.erasmus.db.repository;

import com.erasmus.db.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotesRepository extends JpaRepository<Vote, Long> {
    Vote findById(long id);

    List<Vote> findAll();
}
