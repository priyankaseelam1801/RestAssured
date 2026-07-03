package RestAssured;

import RestAssuredfiles.payload;
import io.restassured.path.json.JsonPath;

public class NestedJsonPath {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(payload.CoursePrice());
		// Print No of courses returned by API

		int count = js.getInt("courses.size()");
		System.out.println("No of courses returned by API: " + count);
		// Print purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount: " + totalAmount);

		// print title of first course
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println("Title of first course: " + titleFirstCourse);
		// Print all course titles and their respective prices

		for (int i = 0; i < count; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			int coursePrice = js.getInt("courses[" + i + "].price");
			System.out.println("Course Title: " + courseTitle + ", Price: " + coursePrice);

			// print no of copies sold by RPA Course

			System.out.println("No of copies sold by RPA Course: ");

			for (int j = 0; j < count; j++) {
				String courseTitles = js.get("courses[" + j + "].title");
				if (courseTitles.equalsIgnoreCase("RPA")) {
					int copies = js.getInt("courses[" + j + "].copies");
					System.out.println("Copies sold by RPA Course: " + copies);
					break;
				}

			}
		}
	}
}