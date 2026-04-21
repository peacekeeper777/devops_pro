package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {
    @Test
    void shouldCalculateMonthlyEmi() {
        App app = new App();
        double emi = app.calculateMonthlyEmi(500000, 11.5, 36);

        assertEquals(16488.00, emi, 0.1);
    }

    @Test
    void shouldApproveLowRiskApplicant() {
        App app = new App();
        LoanProfile profile = new LoanProfile("Aman", 85000, 25000, 8000, 782, 500000, 11.5, 36);

        LoanAssessment assessment = app.assessLoan(profile);

        assertEquals("APPROVED", assessment.status());
        assertEquals("LOW", assessment.riskLevel());
        assertEquals(500000, assessment.recommendedLoanAmount(), 0.1);
    }

    @Test
    void shouldGiveConditionalApprovalForMediumRiskApplicant() {
        App app = new App();
        LoanProfile profile = new LoanProfile("Riya", 70000, 23000, 9000, 720, 700000, 12.0, 48);

        LoanAssessment assessment = app.assessLoan(profile);

        assertEquals("CONDITIONAL APPROVAL", assessment.status());
        assertEquals("MEDIUM", assessment.riskLevel());
        assertEquals(630000, assessment.recommendedLoanAmount(), 0.1);
    }

    @Test
    void shouldRejectApplicantWithLowCreditScore() {
        App app = new App();
        LoanProfile profile = new LoanProfile("Kabir", 90000, 28000, 10000, 610, 400000, 10.5, 24);

        LoanAssessment assessment = app.assessLoan(profile);

        assertEquals("REJECTED", assessment.status());
        assertEquals(0.0, assessment.recommendedLoanAmount(), 0.1);
        assertTrue(assessment.message().contains("Credit score"));
    }

    @Test
    void shouldGenerateReadableSummary() {
        App app = new App();
        LoanProfile profile = new LoanProfile("Aman", 85000, 25000, 8000, 782, 500000, 11.5, 36);

        LoanAssessment assessment = app.assessLoan(profile);
        String summary = app.generateSummary(profile.applicantName(), assessment);

        assertTrue(summary.contains("Applicant: Aman"));
        assertTrue(summary.contains("Status: APPROVED"));
        assertTrue(summary.contains("Risk: LOW"));
    }
}
