package org.example;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class GoogleMapAPIs {

    String place_id = "";
    String new_address = "";

    @BeforeClass
    public void setUp(){
    new_address = "Samata Raj Colony";
    }

    @Test
    public void AddPlace(){

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-type", "application/json")
                .body(Payload.AddPlace())
                .when()
                .post("maps/api/place/add/json")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);

        place_id = js.getString("place_id");

        System.out.println("PLACE ID: " + place_id);


    }

    @Test
    public void UpdatePlace(){

        given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(          "{" +
                        "\"place_id\": \""
                        +place_id
                        +"\"," +
                        "\"address\":\""+new_address+"\"," +
                        "\"key\":\"qaclick123\"," +
                        "\"types\": \"park\""+
                        "}").when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

    }

    @Test(dependsOnMethods = "UpdatePlace")
    public void GetPlace(){



        String response = given().queryParam("key", "qaclick123").queryParam("place_id",place_id)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        System.out.println("GET RESPONSE: ");

        System.out.println(response);

        String response_address = new JsonPath(response).getString("address");

        System.out.println("CHANGE ADDRESS: "+new_address);
        System.out.println("RESPONSE ADDRESS: "+response_address);

        Assert.assertEquals(new_address,response_address);

    }

}
