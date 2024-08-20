package com.example.Junior.Java.Kotlin.Developer.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "github_repositories")
public class GitHub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String owner;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gitHub")
    private List<GitHubBranch> gitHubBranch;

    public GitHub(String owner, String name, List<GitHubBranch> gitHubBranch) {
        this.owner = owner;
        this.name = name;
        this.gitHubBranch = gitHubBranch;
    }

    public GitHub(){};

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GitHubBranch> getGitHubBranch() {
        return gitHubBranch;
    }

    public void setGitHubBranch(List<GitHubBranch> gitHubBranch) {
        this.gitHubBranch = gitHubBranch;
    }
}
