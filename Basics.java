package RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import RestAssuredfiles.ReusableMethods;
import RestAssuredfiles.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// validate if Add place API is working as expected
		// Given - all input details
		// when - submit the API - resource, http method
		// then - validate the response
		// Add place -> Update Place with New Address -> Get Place to validate if New
		// Address is present in response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all()
				// .assertThat().statusCode(200).extract().response().asString();
				.assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing json
		String placeId = js.getString("place_id");
		System.out.println(placeId);

		// Update Place
		String newAddress = "70 Summer walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
		// get Place

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);

		// Cucumber JUnit, TestNG, RestAssured, Maven, Git, Jenkins, Docker, Java,
		// Selenium

		// validate if actual address is same as new address

		Assert.assertEquals(actualAddress, newAddress);

		/*
		 * if(actualAddress.equals(newAddress)) {
		 * System.out.println("Address is successfully updated"); } else {
		 * System.out.println("Address is not updated"); }
		 */
	}

}
