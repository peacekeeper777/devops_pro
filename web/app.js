function calculateMonthlyEmi(principal, annualInterestRate, tenureMonths) {
    const monthlyRate = annualInterestRate / 12 / 100;

    if (monthlyRate === 0) {
        return principal / tenureMonths;
    }

    const growthFactor = Math.pow(1 + monthlyRate, tenureMonths);
    return principal * monthlyRate * growthFactor / (growthFactor - 1);
}

function determineRiskLevel(creditScore, debtToIncomeRatio) {
    if (creditScore >= 750 && debtToIncomeRatio <= 35) {
        return "LOW";
    }
    if (creditScore >= 700 && debtToIncomeRatio <= 45) {
        return "MEDIUM";
    }
    return "HIGH";
}

function assessLoan(profile) {
    const estimatedEmi = calculateMonthlyEmi(profile.loanAmount, profile.interestRate, profile.tenureMonths);
    const disposableIncome = profile.income - profile.expenses - profile.existingEmis;
    const debtToIncomeRatio = ((profile.existingEmis + estimatedEmi) / profile.income) * 100;
    const riskLevel = determineRiskLevel(profile.creditScore, debtToIncomeRatio);

    if (profile.creditScore < 650) {
        return {
            estimatedEmi,
            disposableIncome,
            debtToIncomeRatio,
            recommendedLoanAmount: 0,
            riskLevel: "HIGH",
            status: "REJECTED",
            message: "Credit score is too low for safe lending."
        };
    }

    if (disposableIncome < estimatedEmi) {
        return {
            estimatedEmi,
            disposableIncome,
            debtToIncomeRatio,
            recommendedLoanAmount: 0,
            riskLevel: "HIGH",
            status: "REJECTED",
            message: "Monthly cash flow is not enough to cover the EMI."
        };
    }

    if (debtToIncomeRatio <= 35 && profile.creditScore >= 750) {
        return {
            estimatedEmi,
            disposableIncome,
            debtToIncomeRatio,
            recommendedLoanAmount: profile.loanAmount,
            riskLevel,
            status: "APPROVED",
            message: "Applicant is financially strong and eligible for the full amount."
        };
    }

    if (debtToIncomeRatio <= 45 && profile.creditScore >= 700) {
        return {
            estimatedEmi,
            disposableIncome,
            debtToIncomeRatio,
            recommendedLoanAmount: profile.loanAmount * 0.9,
            riskLevel,
            status: "CONDITIONAL APPROVAL",
            message: "Loan can be approved with a slightly reduced amount."
        };
    }

    if (debtToIncomeRatio <= 50 && profile.creditScore >= 670) {
        return {
            estimatedEmi,
            disposableIncome,
            debtToIncomeRatio,
            recommendedLoanAmount: profile.loanAmount * 0.75,
            riskLevel,
            status: "CONDITIONAL APPROVAL",
            message: "Applicant needs a smaller sanctioned amount to stay within safe limits."
        };
    }

    return {
        estimatedEmi,
        disposableIncome,
        debtToIncomeRatio,
        recommendedLoanAmount: 0,
        riskLevel,
        status: "REJECTED",
        message: "Debt-to-income ratio is too high for approval."
    };
}

function formatNumber(value) {
    return value.toLocaleString("en-IN", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}

function renderAssessment(name, assessment) {
    document.getElementById("summary-name").textContent = name;
    document.getElementById("summary-message").textContent = assessment.message;
    document.getElementById("emi").textContent = formatNumber(assessment.estimatedEmi);
    document.getElementById("disposable-income").textContent = formatNumber(assessment.disposableIncome);
    document.getElementById("dti").textContent = `${formatNumber(assessment.debtToIncomeRatio)}%`;
    document.getElementById("recommended-amount").textContent = formatNumber(assessment.recommendedLoanAmount);
    document.getElementById("risk-level").textContent = assessment.riskLevel;

    const statusPill = document.getElementById("status-pill");
    statusPill.textContent = assessment.status;
    statusPill.className = "status-pill";

    if (assessment.status === "APPROVED") {
        statusPill.classList.add("status-approved");
    } else if (assessment.status === "CONDITIONAL APPROVAL") {
        statusPill.classList.add("status-conditional");
    } else {
        statusPill.classList.add("status-rejected");
    }
}

document.getElementById("loan-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const profile = {
        name: document.getElementById("name").value,
        income: Number(document.getElementById("income").value),
        expenses: Number(document.getElementById("expenses").value),
        existingEmis: Number(document.getElementById("existingEmis").value),
        creditScore: Number(document.getElementById("creditScore").value),
        loanAmount: Number(document.getElementById("loanAmount").value),
        interestRate: Number(document.getElementById("interestRate").value),
        tenureMonths: Number(document.getElementById("tenureMonths").value)
    };

    const assessment = assessLoan(profile);
    renderAssessment(profile.name, assessment);
});
