# Smart Loan Advisor with Jenkins Pipeline

This repository contains a Java Maven project that evaluates a loan application using easy-to-follow financial rules, plus a small website for demo purposes and a Jenkins declarative pipeline for CI.

## Project Idea

The project simulates a basic loan screening system. It analyzes:

1. Monthly disposable income
2. Estimated EMI
3. Debt-to-income ratio
4. Credit score
5. Risk level
6. Final loan recommendation

The final decision can be:

- `APPROVED`
- `CONDITIONAL APPROVAL`
- `REJECTED`

## Java Logic

The Java application is inside:

- `src/main/java/com/example/App.java`
- `src/main/java/com/example/LoanProfile.java`
- `src/main/java/com/example/LoanAssessment.java`

Main responsibilities:

1. Calculate EMI using the standard loan EMI formula
2. Measure disposable income after expenses and existing EMIs
3. Compute debt-to-income ratio
4. Assign applicant risk as `LOW`, `MEDIUM`, or `HIGH`
5. Recommend a final decision and a safe sanctioned amount

## Website Demo

The demo website is inside:

- `web/index.html`
- `web/styles.css`
- `web/app.js`

Open `web/index.html` in a browser to test the same loan logic visually.

## Tests

Unit tests are written in:

- `src/test/java/com/example/AppTest.java`

The tests verify:

1. EMI calculation
2. Full approval case
3. Conditional approval case
4. Rejection case
5. Summary generation

## Jenkins Pipeline

The pipeline is defined in:

- `Jenkinsfile`

Stages:

1. Checkout
2. Build
3. Test
4. Package

After the build, Jenkins publishes JUnit reports and archives the generated JAR.

## Local Run

Run from the project root:

```powershell
mvn clean test package
java -cp target\smart-loan-advisor-1.0-SNAPSHOT.jar com.example.App
```

## Website Run

Open this file directly in your browser:

```text
web/index.html
```
