package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefinations extends Utils {

    RequestSpecification resp;
    ResponseSpecification resspec;
    Response response;
    static String place_id;


    TestDataBuild data=new TestDataBuild();



    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String add) throws IOException {

         resp=given().spec(requestSpecification())
                .body(data.addPlacePayload(name,language,add));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource,String method) {
        ApiResources resourceAPI=ApiResources.valueOf(resource);
        System.out.println(resourceAPI.getResources());
        resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST"))
            response = resp.when().post(resourceAPI.getResources());
            else if (method.equalsIgnoreCase("GET"))
                response = resp.when().get(resourceAPI.getResources());

    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1)
    {
        assertEquals(response.statusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String Expectedvalue)
    {
        assertEquals(getJsonPath(response,keyValue),Expectedvalue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions
         place_id=getJsonPath(response,"place_id");
        resp=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_post_http_request(resource,"GET");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName, expectedName);
    }

    @Given("Deleteplace Payload")
    public void deleteplace_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        resp=given().spec(requestSpecification()).body(data.deletePlacePlayload(place_id));
    }
}
