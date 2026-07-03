package RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
RestAssured.baseURI = "https://priyankaseelam36.atlassian.net/";

		
		String response = given().log().all().header("Content-Type", "application/json")
				.header("Authorization", "Basic cHJpeWFua2FzZWVsYW0zNkBnbWFpbC5jb206QVRBVFQzeEZmR0YwM1pEU21wRWF6QWFmOXJhSFh3TVFpRWFBdFo1WUYwUERvbWd0OHJLbkNyTlV1b2NONUtKcUJpSy1YUl9POHFPdGdjZ25MeXQ5MzFGcXprV2dUdVVZRjdlTXBQSy1PWGlIaFZMeHVaYXhsSVRINERYeUxzWFc2QzBhSXlaWVJJUWx1SWNuMHhvdnJYNU1RblJZYTZhSVptTUtqdzJtWlFjcldOdk5TcG05ekNVPUJGQTA1NTU1")
				.body("{\r\n"
						+ "    \"fields\": {\r\n"
						+ "       \"project\":\r\n"
						+ "       {\r\n"
						+ "          \"key\": \"SCRUM\"\r\n"
						+ "       },\r\n"
						+ "       \"summary\": \"Get  is not working - Automation\",\r\n"
						+ "              \"issuetype\": {\r\n"
						+ "          \"name\": \"Bug\"\r\n"
						+ "       }\r\n"
						+ "   }\r\n"
						+ "}\r\n"
						+ "")
				.post("rest/api/2/issue")
				.then().log().all().assertThat().statusCode(201)
				.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
	String issueId	= js.getString("id"); // for parsing json
		System.out.println(issueId);
	
//Add attachment
		
		File file = new File("C:\\Users\\abcom\\OneDrive\\文档\\Jira Defect screenshot.docx");
		System.out.println(file.exists());
	given().log().all().header("X-Atlassian-Token", "no-check")
	.pathParam("key", issueId)
		.header("Authorization","Basic cHJpeWFua2FzZWVsYW0zNkBnbWFpbC5jb206QVRBVFQzeEZmR0YwM1pEU21wRWF6QWFmOXJhSFh3TVFpRWFBdFo1WUYwUERvbWd0OHJLbkNyTlV1b2NONUtKcUJpSy1YUl9POHFPdGdjZ25MeXQ5MzFGcXprV2dUdVVZRjdlTXBQSy1PWGlIaFZMeHVaYXhsSVRINERYeUxzWFc2QzBhSXlaWVJJUWx1SWNuMHhvdnJYNU1RblJZYTZhSVptTUtqdzJtWlFjcldOdk5TcG05ekNVPUJGQTA1NTU1")
		.multiPart("file",file)
				.log().all().post("rest/api/2/issue/{key}/attachments")
				.then().log().all().assertThat().statusCode(200);
	
	
	//get issue details
	
	given().log().all().header("Content-Type", "application/json")
	.header("Authorization","Basic cHJpeWFua2FzZWVsYW0zNkBnbWFpbC5jb206QVRBVFQzeEZmR0YwM1pEU21wRWF6QWFmOXJhSFh3TVFpRWFBdFo1WUYwUERvbWd0OHJLbkNyTlV1b2NONUtKcUJpSy1YUl9POHFPdGdjZ25MeXQ5MzFGcXprV2dUdVVZRjdlTXBQSy1PWGlIaFZMeHVaYXhsSVRINERYeUxzWFc2QzBhSXlaWVJJUWx1SWNuMHhvdnJYNU1RblJZYTZhSVptTUtqdzJtWlFjcldOdk5TcG05ekNVPUJGQTA1NTU1")
		.pathParam("key", issueId)
		.log().all().get("rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200);
}
}