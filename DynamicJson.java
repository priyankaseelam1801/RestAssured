package RestAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAssuredfiles.ReusableMethods;
import RestAssuredfiles.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test(dataProvider = "BooksData")

	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.Addbook(isbn, aisle)).when().post("/Library/Addbook.php").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);
	}
	
	@DataProvider(name = "BooksData")

	public Object[][] getData() {
		// array = collection of elements
		// multidimensional array = collection of arrays
		return new Object[][] { { "value1", "1235" }, { "value2", "5679" }, { "value3", "91010" } };

	}
	
	
	
	
	@Test(dataProvider = "BooksData1")
	//delete book
	public void deleteBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.Deletebook(isbn, aisle)).when().post("/Library/DeleteBook.php").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);
	}
	
	@DataProvider(name = "BooksData1")
	public Object[][] getData1() {
		// array = collection of elements
		// multidimensional array = collection of arrays
		return new Object[][] { { "value1", "1235" }, { "value2", "5679" }, { "value3", "91010" }};
		}

}
