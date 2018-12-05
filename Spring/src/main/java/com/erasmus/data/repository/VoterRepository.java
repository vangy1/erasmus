package com.erasmus.data.repository;

import com.erasmus.data.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Voter findById(String id);

    List<Voter> findAll();
}
