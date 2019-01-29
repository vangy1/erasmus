package com.erasmus.db.repository;

import com.erasmus.db.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findById(long id);

    List<Meal> findAll();
}
