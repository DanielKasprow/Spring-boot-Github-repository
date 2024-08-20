package com.example.Junior.Java.Kotlin.Developer.Model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GitHubRepository extends JpaRepository<GitHub, Integer> {
    List<GitHub> findAll();
    Page<GitHub> findAll(Pageable page);
    List<GitHub> findByOwner(String owner);
    boolean existsById(Integer id);
    boolean existsByOwner(String owner);
    GitHub save(GitHub entity);
}
