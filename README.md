# Student Result Calculator with Jenkins Pipeline

This repository contains a simple Java Maven application that calculates a student's total marks, average, and pass or fail result, along with a Jenkins declarative pipeline.

## Project Structure

- `pom.xml` - Maven build configuration
- `src/main/java/com/example/App.java` - Student result calculator application
- `src/test/java/com/example/AppTest.java` - Unit tests
- `Jenkinsfile` - Jenkins pipeline definition

## What the Pipeline Does

The Jenkins pipeline runs these stages:

1. Checkout the source code
2. Build the project with `mvn clean compile`
3. Run tests with `mvn test`
4. Package the application with `mvn package -DskipTests`
5. Publish JUnit reports and archive the generated JAR

## What the Application Does

The application:

1. Calculates the total marks for three subjects
2. Calculates the average marks
3. Decides whether the student passed or failed
4. Prints a formatted student report

## Jenkins Requirements

Make sure your Jenkins agent has:

- Java 17 installed
- Maven installed and available in `PATH`

## Creating the Jenkins Job

1. Push this project to GitHub, GitLab, or another Git repository.
2. In Jenkins, create a new `Pipeline` job.
3. Under `Pipeline`, choose `Pipeline script from SCM`.
4. Select your SCM provider and repository URL.
5. Keep the script path as `Jenkinsfile`.
6. Save and run the job.

## Local Build

Run the following from the project root:

```powershell
mvn clean test package
```
