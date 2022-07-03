package com.example.steps.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "com.example.steps.api",
        plugin = {  "pretty", "json:src/test/resources/features/api/cucumber.json"}
)
public class ApiRunnerTest {

    int stcd;
    String rsbd;
    @When("I make request")
    public void i_make_request() {

        Response response = given().port(8081).when().get("/greet/Ivan");
        int status_code = response.statusCode();
        String body = response.getBody().print();

        stcd = status_code;
        rsbd = body;
    }

    @Then("Response code is correct")
    public void response_code_is_correct() {
        Assertions.assertEquals(200, stcd);
    }
    @Then("Response body is correct")
    public void response_body_is_correct() {
        Assertions.assertEquals("Hi, nice to see you here Ivan", rsbd);
    }
}