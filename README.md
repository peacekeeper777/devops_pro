# Basic Maven Project with Jenkins Pipeline

This repository contains a minimal Java Maven application and a Jenkins declarative pipeline.

## Project Structure

- `pom.xml` - Maven build configuration
- `src/main/java/com/example/App.java` - Application entry point
- `src/test/java/com/example/AppTest.java` - Unit test
- `Jenkinsfile` - Jenkins pipeline definition

## What the Pipeline Does

The Jenkins pipeline runs these stages:

1. Checkout the source code
2. Build the project with `mvn clean compile`
3. Run tests with `mvn test`
4. Package the application with `mvn package -DskipTests`
5. Publish JUnit reports and archive the generated JAR

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
