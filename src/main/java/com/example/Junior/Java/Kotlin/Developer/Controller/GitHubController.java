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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/github")
public class GitHubController {
    private final ApplicationEventPublisher eventPublisher;
    private final GitHubRepository gitHubRepository;

    GitHubController(ApplicationEventPublisher eventPublisher, GitHubRepository gitHubRepository) {
        this.eventPublisher = eventPublisher;
        this.gitHubRepository = gitHubRepository;

    }
    @GetMapping("/{owner}")
    ResponseEntity<List<?>> getRepositoriesFromGitHub(@PathVariable String owner, @RequestHeader("Authorization") String authorizationHeader) throws IOException, InterruptedException {
        String finalToken = null;
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            finalToken = authorizationHeader;
        }
        List<GitHub> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();

        //get all repository from user
        HttpRequest requestListRepositories = httpRequest("https://api.github.com/users/" + owner + "/repos", finalToken);
        HttpResponse<String> responseListRepo = client.send(requestListRepositories, HttpResponse.BodyHandlers.ofString());

        //if user not found
        if(responseListRepo.statusCode() == 404 ){
            return new ResponseEntity<>(badRequest(responseListRepo.statusCode(), "User not found"),HttpStatus.NOT_FOUND);
        }
        //if Token is wrong
        else if(responseListRepo.statusCode() == 401 ){
            return new ResponseEntity<>(badRequest(responseListRepo.statusCode(), "Wrong token"),HttpStatus.BAD_REQUEST);
        }

        //list of Repositories
        List<ListGitHubRepositories> resultListRepo = mapper.readValue(responseListRepo.body(), new TypeReference<>() {});

        for (ListGitHubRepositories repo : resultListRepo) {
            List<GitHubBranch> listBranches = new ArrayList<>();
            if (!repo.fork) {
                List<ListGitHubBranches> resultListBranches = requestBranches("https://api.github.com/repos/"+ owner+"/" + repo.name + "/branches" , mapper, client, finalToken);
                //get all branches from repo
                for(ListGitHubBranches branch : resultListBranches){
                    listBranches.add(new GitHubBranch(branch.name,branch.commit.sha));
                }
            }
            result.add(new GitHub(repo.owner.login ,repo.name,listBranches));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Get all branches from repository
    List<ListGitHubBranches> requestBranches(String Url, ObjectMapper mapper, HttpClient client, String finalToken) throws IOException, InterruptedException {
        HttpResponse<String> responseListBranches = client.send(httpRequest(Url, finalToken), HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(responseListBranches.body(), new TypeReference<>() {});
    }
    //get http Request
    HttpRequest httpRequest(String Url, String finalToken){
        return HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, finalToken)
                .uri(URI.create(Url))
                .build();
    }
    List<UserNotFound> badRequest(int statusCode, String message){
        List<UserNotFound> userNotFound = new ArrayList<>();
        userNotFound.add(new UserNotFound(statusCode,message));
        return userNotFound;
    }
}
