package com.example;

public record LoanProfile(
    String applicantName,
    double monthlyIncome,
    double monthlyExpenses,
    double existingEmis,
    int creditScore,
    double requestedLoanAmount,
    double annualInterestRate,
    int tenureMonths
) {
}
