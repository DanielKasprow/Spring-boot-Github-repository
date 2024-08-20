package com.example.Junior.Java.Kotlin.Developer.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "github_branches")
public class GitHubBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String branch;
    private String lastCommitSha;

    @ManyToOne
    @JoinColumn(name = "github_repositories_id")
    private GitHub gitHub;

    public GitHubBranch(String branch, String lastCommitSha) {
        this.branch = branch;
        this.lastCommitSha = lastCommitSha;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }
}
