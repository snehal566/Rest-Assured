package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TestsOnLocalAPI {
	
	//@Test
	public void get() {
		
		baseURI = "http://localhost:3000";
		given().get("/users").then().statusCode(200).log().all();
		
	}

	//@Test
	public void post() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "John");
		request.put("lastName", "Foster");
		request.put("subjectId", 1);
		//request.put("id", "4");
		
		baseURI = "http://localhost:3000";
		
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString()).
		when()
		.post("/users")
		.then()
		.statusCode(201);
		
	}
	
	//@Test
	public void put() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "John");
		request.put("lastName", "Foster");
		request.put("subjectId", 2);
		request.put("userId", 4);
		
		baseURI = "http://localhost:3000";
		
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString()).
		when()
		.put("/users/SGreZhLtgCc")
		.then()
		.statusCode(200);
		
	}
	
		//@Test
		public void patch() {
			
			JSONObject request = new JSONObject();
			
			request.put("lastName", "Einstien");
			request.put("id", "4");
			
			given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString()).
			when()
			.patch("/users/onmlCmy6kbk")
			.then()
			.statusCode(200);
			
		}
		
	
	@Test
	public void delete() {
		
		baseURI = "http://localhost:3000";
		given()
		.delete("/users/onmlCmy6kbk")
		.then()
		.statusCode(200);
	}
}
