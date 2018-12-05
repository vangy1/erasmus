package com.erasmus.data.repository;

import com.erasmus.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findById(String id);

    List<Admin> findAll();
}
