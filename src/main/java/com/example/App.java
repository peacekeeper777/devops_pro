package com.example;

public class App {
    public int calculateTotal(int mathMarks, int scienceMarks, int englishMarks) {
        return mathMarks + scienceMarks + englishMarks;
    }

    public double calculateAverage(int mathMarks, int scienceMarks, int englishMarks) {
        return calculateTotal(mathMarks, scienceMarks, englishMarks) / 3.0;
    }

    public String findResult(double averageMarks) {
        if (averageMarks >= 40.0) {
            return "PASS";
        }
        return "FAIL";
    }

    public String generateReport(String studentName, int mathMarks, int scienceMarks, int englishMarks) {
        int total = calculateTotal(mathMarks, scienceMarks, englishMarks);
        double average = calculateAverage(mathMarks, scienceMarks, englishMarks);
        String result = findResult(average);

        return String.format(
            "Student: %s | Total: %d | Average: %.2f | Result: %s",
            studentName,
            total,
            average,
            result
        );
    }

    public static void main(String[] args) {
        App app = new App();
        String report = app.generateReport("Aman", 78, 85, 74);
        System.out.println(report);
    }
}
