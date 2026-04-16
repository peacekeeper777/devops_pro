package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void shouldReturnExpectedMessage() {
        App app = new App();
        assertEquals("Hello from Maven + Jenkins!", app.getMessage());
    }
}
