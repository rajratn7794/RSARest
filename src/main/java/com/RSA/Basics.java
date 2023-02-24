package com.RSA;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.Payload;
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//given - all input details
		//when -submit API ,resource and http method
		//Then -validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		/*.body("{\r\n"
				+ "    \"location\":\r\n"
				+ "        {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "        },\r\n"
				+ "     \"accuracy\": 50,\r\n"
				+ "      \"name\": \"RSA\",\r\n"
				+ "      \"phone number\":\"(+91)9158023448\",\r\n"
				+ "      \"address\": \"solapur\",\r\n"
				+ "       \"types\":[\r\n"
				+ "           \"shoe park\",\r\n"
				+ "            \"shop\"],\r\n"
				+ "       \"website\": \"https://rahulshettyacademy.com\",\r\n"
				+ "       \"language\": \"english\"   \r\n"
				+ "} ")*/
		.body(Payload.addpayload()).
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)");

	}

}
