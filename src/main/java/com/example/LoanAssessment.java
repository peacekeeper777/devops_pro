package com.example;

public record LoanAssessment(
    double estimatedEmi,
    double disposableIncome,
    double debtToIncomeRatio,
    double recommendedLoanAmount,
    String riskLevel,
    String status,
    String message
) {
}
