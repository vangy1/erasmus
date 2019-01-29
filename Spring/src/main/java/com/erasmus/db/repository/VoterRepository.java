package com.erasmus.db.repository;

import com.erasmus.db.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Voter findByChipId(String id);

    List<Voter> findAll();
}
