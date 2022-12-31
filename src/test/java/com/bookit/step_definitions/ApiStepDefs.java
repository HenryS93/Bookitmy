package com.bookit.step_definitions;

import com.bookit.pages.SelfPage;
import com.bookit.utilities.BookitUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.DB_Util;
import com.bookit.utilities.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiStepDefs {
        String token;
        Response response;
    @Given("I logged Bookit api as a {string}")
    public void i_logged_bookit_api_as_a(String role) {

        token = BookitUtils.generateTokenByRole(role);
        System.out.println("token = " + token);


    }





    @When("I sent get request to {string} endpoint")
    public void i_sent_get_request_to_endpoint(String endpoint) {


         response = given().accept(ContentType.JSON)
                .header("Authorization", token)
                .when().get(ConfigurationReader.getProperty("base_url") + endpoint);


    }





    @Then("status code should be {int}")
    public void status_code_should_be(Integer expectedStatusCode) {

        System.out.println("response.statusCode()= " + response.statusCode());

        Integer actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);



    }



    @Then("content type is {string}")
    public void content_type_is(String expectedContentType) {

        String actualContentType = response.getContentType();
        Assert.assertEquals(expectedContentType,actualContentType);
        System.out.println("actualContentType = " + actualContentType);



    }




    @Then("role is {string}")
    public void role_is(String expectedRole) {

        Object actualRole = response.path("role");
        System.out.println("response.path(\"role\")"+response.path("role"));
        Assert.assertEquals(expectedRole,actualRole);







    }

































}
