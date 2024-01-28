package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {

	RequestSpecification reqAll;
	ResponseSpecification resParam;
	Response res;
	TestDataBuild data = new TestDataBuild();
	static String place_id;

	@Given("Add place Payload with {string} {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language, String phoneNumber)
			throws IOException {

		reqAll = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, address, language, phoneNumber));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resParam = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			res = reqAll.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			res = reqAll.when().get(resourceAPI.getResource());

	}

	@Then("the API call is suceess with status code  {int}")
	public void the_api_call_is_suceess_with_status_code(Integer int1) {
		assertEquals(res.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpValue) {

		assertEquals(getJsonPath(res, KeyValue), ExpValue);

	}

	@Then("verify place_id created map to {string} using {string}")
	public void verify_place_id_created_map_to_using(String name, String resource) throws IOException {
		place_id = getJsonPath(res, "place_id");
		reqAll = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actName = getJsonPath(res, "name");
		assertEquals(actName, name);

	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		reqAll = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	
	}

}
