package com.assignment.stepdefinition;


/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;*/
import com.pojo.api.Todo;
import com.pojo.api.User;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.stream.Collectors;
public class UsersTodosTaskCompletion {


	private final String BASE_URL = "http://jsonplaceholder.typicode.com";
	private List<User> fanCodeUsers;
	//public static ExtentReports extent=null;
	//public static ExtentTest test=null;

	/*
	 * @BeforeTest public void setUp() { ExtentHtmlReporter htmlReporter = new
	 * ExtentHtmlReporter("extent-report.html"); extent = new ExtentReports();
	 * extent.attachReporter(htmlReporter); }
	 * 
	 * @AfterTest public void tearDown() { extent.flush(); }
	 */

	@Given("User has the todo tasks")
	public void users_has_the_todo_tasks() {
		//test = extent.createTest("Users belong to the city FanCode");
		// Get users
		Response response = RestAssured.get(BASE_URL + "/users");
		List<User> users = response.jsonPath().getList("", User.class);

		// Log the response
		Reporter.log("Users Response: " + response.getBody().asString());
		//test.log(Status.INFO, "Users Response: " + response.getBody().asString());

		// Filter users from FanCode city
		fanCodeUsers = users.stream()
				.filter(user -> isFanCodeCity(user.getAddress().getGeo().getLat(), user.getAddress().getGeo().getLng()))
				.collect(Collectors.toList());

		// Log the filtered users
		Reporter.log("FanCode Users: " + fanCodeUsers.toString());
		// test.log(Status.INFO, "FanCode Users: " + fanCodeUsers.toString());

	}

	@And("User belongs to the city FanCode")
	public void user_belongs_to_the_city_fancode() {
		for (User user : fanCodeUsers) {
			checkUserTodos(user.getId());
		}
	}

	@Then("User Completed task percentage should be greater than 50%")
	public void the_completed_task_percentage_should_be_greater_than_50() {
		// Validation is performed in checkUserTodos method
	}


	private boolean isFanCodeCity(String lat, String lng) {
		double latitude = Double.parseDouble(lat);
		double longitude = Double.parseDouble(lng);
		return latitude >= -40 && latitude <= 5 && longitude >= 5 && longitude <= 100;
	}

	private void checkUserTodos(int userId) {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.queryParam("userId", userId)
				.get(BASE_URL + "/todos");
		// Log the response
		Reporter.log("Todos Response for User " + userId + ": " + response.getBody().asString());
		// test.log(Status.INFO, "Todos Response for User " + userId + ": " + response.getBody().asString());



		List<Todo> todos = response.jsonPath().getList("", Todo.class);
		long completedCount = todos.stream().filter(Todo::isCompleted).count();
		double completionPercentage = ((double) completedCount / todos.size()) * 100;
		// Log the completion percentage
		Reporter.log("User " + userId + " Completion Percentage: " + completionPercentage);
		//test.log(Status.INFO, "User " + userId + " Completion Percentage: " + completionPercentage);


		Assert.assertTrue(completionPercentage > 50, "User " + userId + " has less than 50% tasks completed.");
		/*
		 * if (completionPercentage > 50) { test.log(Status.PASS, "User " + userId +
		 * " has more than 50% tasks completed."); } else { test.log(Status.FAIL,
		 * "User " + userId + " has less than 50% tasks completed."); }
		 */
	}
}