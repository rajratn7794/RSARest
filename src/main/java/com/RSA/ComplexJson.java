package com.RSA;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      
	
		JsonPath js=new JsonPath(Payload.courses());
	//print no of courses returned by api
		int courseCount=js.getInt("courses.size()");
	System.out.println("The course count is:-"+courseCount);
	
	//print purchase amount
	int purchAmount=js.getInt("dashboard.purchaseAmount");
	System.out.println("Total purchase amount is:-"+ purchAmount);
	
	//print title of first course
	String titleFirstCourse=js.get("courses[2].title");
	System.out.println("First course title is:-"+ titleFirstCourse);
	
	//print all course titles and their respective price
	for(int i=0;i<courseCount;i++)
	{
	   System.out.println(js.get("courses["+i+"].title"));
	   System.out.println(js.getInt("courses["+i+"].price"));
	}
	//print no of copies sold by RPA course
	System.out.println("No of copies sold by RPA course");
	for(int i=0;i<courseCount;i++)
	{
	   String courseTitles=(js.get("courses["+i+"].title"));
	   if(courseTitles.equalsIgnoreCase("RPA"))
	   {
		   int copies=js.getInt("courses["+i+"].copies");
		   System.out.println(copies);
		   break;//code optimization it will break the iteration only once we find the data
	   }
	}
	
}

	
}