package com.LibraryAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Jsonwithexternalinputs {
	
	@Test 
	public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
	//Building json payload with external inputs	
	String response=given().log().all().header("Content-Type","application/json").
		body(Payload1.addbook("kriti","4569")).//sending parameters to payload from test
		when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();

	JsonPath js=new JsonPath(response);
	String id= js.get("ID");
	System.out.println(id);
	}

}
