package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostExamples {
	
	@Test
	public void testGet(){
		
		baseURI = "https://reqres.in/api";
		
		given().get("/users?page=2").
		then().statusCode(200).
		body("data[4].first_name",equalTo("George")).
		body("data.first_name",hasItems("George","Rachel"));
	}
	
	@Test
	public void testPost(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		  map.put("name", "Snehal"); 
		  map.put("job", "Teacher");
		  
		  //System.out.println("Map:= " + map);
		 
		
		JSONObject request = new JSONObject(map);
		System.out.println("Request :=" + request);
		
		/*
		 * request.put("name", "Snehal"); 
		 * request.put("job", "Teacher");
		 */
		
		baseURI = "https://reqres.in/api";
		
		given().
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		post("/users").
		then().
		statusCode(201).
		log().all();
		
	}

}
