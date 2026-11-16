package tests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SOAPXMLRequest {
	
	@Test
	public void validateSOAPXML() throws IOException {
		
		
		File file  = new File("src/test/resources/soapRequest.xml");
		
		if(file.exists()) 
			System.out.println(">>>>>  File exists>>>>");
		
		
		FileInputStream fileInputStream = new FileInputStream(file);
		
		String requestBody = IOUtils.toString(fileInputStream,"UTF-8");
		
		baseURI = "http://www.dneonline.com/";
		
		given().contentType("text/xml").
		accept(ContentType.XML).
		body(requestBody).
		when().
		post("/calculator.asmx").
		then().
		statusCode(200).
		and().
		body("//*AddResult.text()", equalTo("5")). 
		log().all();
	}

}
