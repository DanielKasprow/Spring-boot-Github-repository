package com.example.Junior.Java.Kotlin.Developer.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListGitHubBranches {
    public String name;
    public Commit commit;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit{
        public String sha;
    }
}
