package com.Ecommerce;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Login;
import com.pojo.LoginResponsePayload;
import com.pojo.OrderDetail;
import com.pojo.Orders;

public class E2E {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	 RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();

	 Login l=new Login();
	 l.setUserEmail("raj1@gmail.com");
	 l.setUserPassword("Raj@gmail.com1");
	 RequestSpecification reqlogin =given().log().all().spec(req).body(l);
	
	 LoginResponsePayload logres= reqlogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponsePayload.class);
	 System.out.println(logres.getToken());
	 String token=logres.getToken();
	 System.out.println(logres.getUserId());
	 String userId=logres.getUserId();

	 //Add Product
	 
	 RequestSpecification addproductBasereq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			 .addHeader("authorization", token)
			 .build();
	 
	 RequestSpecification reqaddProduct= given().log().all().spec(addproductBasereq).param("productName","playstation").param("productAddeBy",userId)
	 .param("productCategory", "fashion").param("productSubCategory","shirts").param("productPrice","5000").param("productDescription","lenovo")
	 .param("productFor", "men")
	 .multiPart("productImage",new File("E:\\p.jpg"));
	 
	 
	 String addproductresponse= reqaddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
	 JsonPath js=new JsonPath(addproductresponse);
	 String productId= js.get("productId");
	 
	 //Create Order
	 RequestSpecification createOrderBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			 .addHeader("authorization",token).setContentType(ContentType.JSON).build();
	
	 OrderDetail orderdetail=new OrderDetail();
	 orderdetail.setCountry("India");
	 orderdetail.setProductOrderId(productId);
	 List<OrderDetail> orderDetailList=new ArrayList<OrderDetail>();
	 orderDetailList.add(orderdetail);
	 
	 Orders order=new Orders();
	order.setOrders(orderDetailList);
	
	RequestSpecification createorderReq= given().log().all().spec(createOrderBaseReq).body(order);
	String responseaddOrder= createorderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	System.out.println(responseaddOrder);
	}
	

}
