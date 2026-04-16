package com.example;

public class App {
    public String getMessage() {
        return "Hello from Maven + Jenkins!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getMessage());
    }
}
