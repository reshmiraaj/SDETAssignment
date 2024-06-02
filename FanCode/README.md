## Project Setup

1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn clean install` to install dependencies.
4. Run tests using `mvn test`.

## Overview

This project automates the scenario where users from the city 'FanCode' should have more than half of their todo tasks completed. It uses Rest Assured for API automation, Cucumber for BDD, and TestNG for testing.

## Files

- `fancode.feature`: Gherkin feature file describing the test scenario.
- `UsersTodosTaskCompletion.java`: Step definitions for the Cucumber steps.
- `User.java`: POJO for user data.
- `Todo.java`: POJO for todo data.
- `TestRunner.java`: Runner class to execute the tests.

## Running the Tests

Execute the following command to run the tests:
```bash
mvn test

Execute the src/test/java/com/assignment/runners/TestRunner.java

After running the tests, the HTML report will be generated in the target/cucumber-html-report directory & in test-output/emailable-report.html.
