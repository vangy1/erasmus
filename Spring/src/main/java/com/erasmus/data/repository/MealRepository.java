package com.erasmus.data.repository;

import com.erasmus.data.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findById(String id);

    List<Meal> findAll();
}
