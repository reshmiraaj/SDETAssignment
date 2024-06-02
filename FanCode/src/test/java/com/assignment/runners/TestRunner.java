package com.assignment.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/Features",
		glue = "com.assignment.stepdefinition",
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber.json"}
		)
public class TestRunner extends AbstractTestNGCucumberTests {
}	