package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void shouldCalculateTotalMarks() {
        App app = new App();
        assertEquals(237, app.calculateTotal(78, 85, 74));
    }

    @Test
    void shouldCalculateAverageMarks() {
        App app = new App();
        assertEquals(79.0, app.calculateAverage(78, 85, 74));
    }

    @Test
    void shouldReturnPassWhenAverageIsAboveThreshold() {
        App app = new App();
        assertEquals("PASS", app.findResult(79.0));
    }

    @Test
    void shouldGenerateStudentReport() {
        App app = new App();
        assertEquals(
            "Student: Aman | Total: 237 | Average: 79.00 | Result: PASS",
            app.generateReport("Aman", 78, 85, 74)
        );
    }
}
