package com.erasmus.db.repository;

import com.erasmus.db.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findById(long id);

    List<Admin> findAll();
}
