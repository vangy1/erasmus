package com.erasmus.db.repository;

import com.erasmus.db.model.CurrentVoting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentVotingRepository extends JpaRepository<CurrentVoting, Long> {
    CurrentVoting findById(long id);
}
