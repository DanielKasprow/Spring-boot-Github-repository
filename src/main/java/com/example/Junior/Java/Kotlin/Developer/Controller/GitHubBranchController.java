package com.example.Junior.Java.Kotlin.Developer.Controller;


import com.example.Junior.Java.Kotlin.Developer.API.ListGitHubBranches;
import com.example.Junior.Java.Kotlin.Developer.API.ListGitHubRepositories;
import com.example.Junior.Java.Kotlin.Developer.API.UserNotFound;
import com.example.Junior.Java.Kotlin.Developer.Model.GitHub;
import com.example.Junior.Java.Kotlin.Developer.Model.GitHubBranch;
import com.example.Junior.Java.Kotlin.Developer.Model.GitHubRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/github_branches")
public class GitHubBranchController {
    private final ApplicationEventPublisher eventPublisher;
    private final GitHubRepository gitHubRepository;

    GitHubBranchController(ApplicationEventPublisher eventPublisher, GitHubRepository gitHubRepository) {
        this.eventPublisher = eventPublisher;
        this.gitHubRepository = gitHubRepository;
    }

}
