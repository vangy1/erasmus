package com.erasmus.db.repository;

import com.erasmus.db.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Suggestion findById(long id);

    List<Suggestion> findAll();
}
