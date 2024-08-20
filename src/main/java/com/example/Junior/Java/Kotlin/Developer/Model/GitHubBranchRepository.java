package com.example.Junior.Java.Kotlin.Developer.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface GitHubBranchRepository extends JpaRepository<GitHubBranch, Integer> {
    boolean existsById(Integer id);

}
