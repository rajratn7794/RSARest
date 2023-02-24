package com.RSA;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class MultipleApibasic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	
	String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.addpayload()).
		when().post("maps/api/place/add/json").
		then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
	System.out.println("The response is"+response);
	JsonPath js=new  JsonPath(response);
	String placeid=js.getString("place_id");
	System.out.println("The place id is:- "+placeid);
	
	//update api
	String newAddress="Banglore";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "    \"place_id\": \""+placeid+"\",\r\n"
			+ "    \"address\":\""+newAddress+"\",\r\n"
			+ "    \"key\":\"qaclick123\"\r\n"
			+ "}").
	when().put("maps/api/place/update/json").
	then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
	//get place
	String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", placeid)
	.when().get("maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=new JsonPath(getPlaceResponse);
	String actualAddress=js1.getString("address");
	System.out.print("actual address is:-"+ actualAddress);
	Assert.assertEquals(actualAddress,newAddress);
	}
	

}
