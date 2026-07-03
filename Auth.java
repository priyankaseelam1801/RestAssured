package RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Auth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String response = given().log().all()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println(response);

		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token");
//get details of courses
		String actualresponse = given().log().all().queryParam("access_token", accessToken).when()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().log().all().assertThat()
				.extract().asString();

//System.out.println("Status Code: " + actualresponse.getStatusCode());
//System.out.println(actualresponse.asPrettyString());
		System.out.println(actualresponse);
	}

}
