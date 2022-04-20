package org.example;

public class Payload {

    public static String AddPlace(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.380000,\n" +
                "    \"lng\": 33.410000\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Model house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, Nagarabavi , Puttanna Complex\",\n" +
                "  \"types\": [\n" +

                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }
}
