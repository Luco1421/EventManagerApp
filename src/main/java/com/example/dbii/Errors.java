package com.example.dbii;

public class Errors extends RuntimeException {
    public Errors(String message) {
        super(message);
    }
}