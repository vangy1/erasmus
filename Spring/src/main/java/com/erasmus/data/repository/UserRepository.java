package com.erasmus.data.repository;

import com.erasmus.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String id);

    List<User> findAll();
}
