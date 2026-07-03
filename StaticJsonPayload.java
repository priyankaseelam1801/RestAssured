package RestAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAssuredfiles.ReusableMethods;
import RestAssuredfiles.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticJsonPayload {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Files.readAllBytes(Paths.get("C:\\Users\\abcom\\Downloads\\addPlace.json"))).when()
				.post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing json
		String placeId = js.getString("place_id");
		System.out.println(placeId);

	}
	//@Test

	// content of file to String -> content of file can convert to Byte array ->
	// Byte array to String
//content of the test case is same as DynamicJson.java, only difference is that we are using static json payload here
/*	public void addlocation() throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Files.readAllBytes(Paths.get("C:\\Users\\abcom\\Downloads\\addPlace.json"))).when()
				.post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response); // for parsing json
		String placeId = js.getString("place_id");
		System.out.println(placeId);

	}*/

}
                                                                 