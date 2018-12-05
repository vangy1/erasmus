package com.erasmus.data.repository;

import com.erasmus.data.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotesRepository extends JpaRepository<Vote, Long> {
    Vote findById(String id);

    List<Vote> findAll();
}
