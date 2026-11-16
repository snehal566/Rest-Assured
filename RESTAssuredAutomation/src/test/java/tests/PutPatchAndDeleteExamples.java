package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchAndDeleteExamples {
	
	
	//@Test
	public void testPut(){
		
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
		header("ContentType","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		put("/users/2").
		then().
		statusCode(200).
		log().all();
		
	}


	//@Test
	public void testPatch(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		  map.put("name", "Snehal"); 
		  map.put("job", "HomeMaker");
		  
		  //System.out.println("Map:= " + map);
		 
		
		JSONObject request = new JSONObject(map);
		System.out.println("Request :=" + request);
		
		/*
		 * request.put("name", "Snehal"); 
		 * request.put("job", "Teacher");
		 */
		
		baseURI = "https://reqres.in/";
		
		given().
		header("ContentType","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).	
		when().
		patch("api/users/2").
		then().
		statusCode(200).
		log().all();
		
	}

	
	@Test
	public void testDelete(){
		
		
		
		baseURI = "https://reqres.in/";
		
		
		when().
		delete("api/users/2").
		then().
		statusCode(204).
		log().all();
		
	}
}
