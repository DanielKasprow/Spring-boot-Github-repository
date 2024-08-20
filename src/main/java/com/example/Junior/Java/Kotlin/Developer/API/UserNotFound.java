package com.example.Junior.Java.Kotlin.Developer.API;

public class UserNotFound {
    public int StatusCode;
    public String Message;

    public UserNotFound(int statusCode, String message) {
        StatusCode = statusCode;
        Message = message;
    }
}
