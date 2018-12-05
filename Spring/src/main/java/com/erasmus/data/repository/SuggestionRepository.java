package com.erasmus.data.repository;

import com.erasmus.data.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    Suggestion findById(String id);

    List<Suggestion> findAll();
}
