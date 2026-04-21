package com.example;

public class App {
    public double calculateDisposableIncome(LoanProfile profile) {
        return profile.monthlyIncome() - profile.monthlyExpenses() - profile.existingEmis();
    }

    public double calculateMonthlyEmi(double principal, double annualInterestRate, int tenureMonths) {
        double monthlyRate = annualInterestRate / 12.0 / 100.0;

        if (monthlyRate == 0) {
            return principal / tenureMonths;
        }

        double growthFactor = Math.pow(1 + monthlyRate, tenureMonths);
        return principal * monthlyRate * growthFactor / (growthFactor - 1);
    }

    public double calculateDebtToIncomeRatio(LoanProfile profile, double estimatedEmi) {
        return ((profile.existingEmis() + estimatedEmi) / profile.monthlyIncome()) * 100.0;
    }

    public String determineRiskLevel(int creditScore, double debtToIncomeRatio) {
        if (creditScore >= 750 && debtToIncomeRatio <= 35.0) {
            return "LOW";
        }
        if (creditScore >= 700 && debtToIncomeRatio <= 45.0) {
            return "MEDIUM";
        }
        return "HIGH";
    }

    public LoanAssessment assessLoan(LoanProfile profile) {
        double estimatedEmi = calculateMonthlyEmi(
            profile.requestedLoanAmount(),
            profile.annualInterestRate(),
            profile.tenureMonths()
        );
        double disposableIncome = calculateDisposableIncome(profile);
        double debtToIncomeRatio = calculateDebtToIncomeRatio(profile, estimatedEmi);
        String riskLevel = determineRiskLevel(profile.creditScore(), debtToIncomeRatio);

        if (profile.creditScore() < 650) {
            return new LoanAssessment(
                estimatedEmi,
                disposableIncome,
                debtToIncomeRatio,
                0.0,
                "HIGH",
                "REJECTED",
                "Credit score is too low for safe lending."
            );
        }

        if (disposableIncome < estimatedEmi) {
            return new LoanAssessment(
                estimatedEmi,
                disposableIncome,
                debtToIncomeRatio,
                0.0,
                "HIGH",
                "REJECTED",
                "Monthly cash flow is not enough to cover the EMI."
            );
        }

        if (debtToIncomeRatio <= 35.0 && profile.creditScore() >= 750) {
            return new LoanAssessment(
                estimatedEmi,
                disposableIncome,
                debtToIncomeRatio,
                profile.requestedLoanAmount(),
                riskLevel,
                "APPROVED",
                "Applicant is financially strong and eligible for the full amount."
            );
        }

        if (debtToIncomeRatio <= 45.0 && profile.creditScore() >= 700) {
            return new LoanAssessment(
                estimatedEmi,
                disposableIncome,
                debtToIncomeRatio,
                profile.requestedLoanAmount() * 0.90,
                riskLevel,
                "CONDITIONAL APPROVAL",
                "Loan can be approved with a slightly reduced amount."
            );
        }

        if (debtToIncomeRatio <= 50.0 && profile.creditScore() >= 670) {
            return new LoanAssessment(
                estimatedEmi,
                disposableIncome,
                debtToIncomeRatio,
                profile.requestedLoanAmount() * 0.75,
                riskLevel,
                "CONDITIONAL APPROVAL",
                "Applicant needs a smaller sanctioned amount to stay within safe limits."
            );
        }

        return new LoanAssessment(
            estimatedEmi,
            disposableIncome,
            debtToIncomeRatio,
            0.0,
            riskLevel,
            "REJECTED",
            "Debt-to-income ratio is too high for approval."
        );
    }

    public String generateSummary(String applicantName, LoanAssessment assessment) {
        return String.format(
            "Applicant: %s | Status: %s | Risk: %s | EMI: %.2f | Disposable Income: %.2f | DTI: %.2f%% | Recommended Amount: %.2f | Note: %s",
            applicantName,
            assessment.status(),
            assessment.riskLevel(),
            assessment.estimatedEmi(),
            assessment.disposableIncome(),
            assessment.debtToIncomeRatio(),
            assessment.recommendedLoanAmount(),
            assessment.message()
        );
    }

    public static void main(String[] args) {
        App app = new App();
        LoanProfile profile = new LoanProfile("Aman", 85000, 25000, 8000, 782, 500000, 11.5, 36);
        LoanAssessment assessment = app.assessLoan(profile);
        System.out.println(app.generateSummary(profile.applicantName(), assessment));
    }
}
