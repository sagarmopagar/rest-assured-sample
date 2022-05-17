package org.example.jsonqueries;

import io.restassured.path.json.JsonPath;

public class MainClass {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(DummyAPI.getResponse());

        System.out.println(js.getString("courses"));


        int course_size = js.getInt("courses.size()");
        System.out.println(course_size);

        int pur_amt = js.getInt("dashboard.purchaseAmount");
        System.out.println(pur_amt);

        String firstTitle = js.get("courses[0].title");
        System.out.println("First Title: "+firstTitle);

        System.out.println("\nAll Courses and Their Titles\n");

        for(int i=0;i<course_size;i++){

            String Title = js.get("courses["+i+"].title");
            System.out.println("Title: "+Title);
        }

        System.out.println("Total cost === Cumulative cost");
        int total = 0;
        for (int i=0;i<course_size;i++){

            int item_price = 0;
            int item_copies = 0;

            int item_net = 0;

            item_price = js.getInt("courses["+i+"].price");
            item_copies = js.getInt("courses["+i+"].copies");

            item_net = item_price * item_copies;

            System.out.println("Item "+i+" Net: " + item_price + " * " + item_copies + " = " + item_net);
            total = total + item_net;


        }

        System.out.println("Cumulative Value: "+total);

        System.out.println("\n\nRPA Copies Sold");

        for(int i=0;i<course_size;i++){

            String courseTitle = js.getString("courses["+i+"].title");
            if (courseTitle.equalsIgnoreCase("cypress")){
                String courseCopies = js.getString("courses["+i+"].copies");
                System.out.println(courseCopies);
            }


        }

    }
}
