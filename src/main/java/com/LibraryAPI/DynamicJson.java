package com.LibraryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson 
{
//Parameterize the API test with multiple inputs
	@Test (dataProvider="BooksData")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
	String response=given().log().all().header("Content-Type","application/json").
		body(Payload1.addbook(isbn,aisle)).
		when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();

	JsonPath js=new JsonPath(response);
	String id= js.get("ID");
	System.out.println(id);
	}
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"amol","1425"},{"shubam","7854"},{"Ravi","5632"}};
	}
}
