package org.example;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        baseURI = "https://jsonplaceholder.typicode.com";
        given().header("Content-Type", "application/json").when().get("todos/1").then().log().all().statusCode(200);
        System.out.println( "Hello World!" );




    }
}
