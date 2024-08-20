package com.example.Junior.Java.Kotlin.Developer.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListGitHubRepositories {
    public String name;
    public Owner owner;
    public boolean fork;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner{
        public String login;
    }
}




