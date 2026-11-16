package tests;
import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static org.hamcrest.Matchers.equalTo;

public class XmlSchemaValidation_1 {

    @Test
    public void testXmlResponseAgainstXsd() throws Exception {
        File file = new File("src/test/resources/soapRequest.xml");
        String requestBody = IOUtils.toString(new FileInputStream(file), "UTF-8");
        
        baseURI = "http://www.dneonline.com/";

        // 1. Send the request, check functional assertions, and extract the inner payload as a String
        String addResponsePayload = given()
            .contentType("text/xml")
            .accept(ContentType.XML)
            .body(requestBody)
        .when()
            .post("/calculator.asmx")
        .then()
            .statusCode(200)
            .body("//*AddResult.text()", equalTo("5")).log().all()
        .extract()
            // Extract just the inner AddResponse XML node content as a string
            .xmlPath()
            .getString("Envelope.Body.AddResponse");

        // 2. Validate the extracted inner XML string against your simple calculator.xsd file loaded from the classpath
        given()
            .body(addResponsePayload)
        .then()
            .body(matchesXsd("calculator_1.xsd"));
    }
}