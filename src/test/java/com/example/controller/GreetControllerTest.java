package com.example.controller;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;


@MicronautTest
class GreetControllerTest {

    @Test
    void testHelloWorld(RequestSpecification spec) {
        spec
                .when().get("/greet/Ivan")
                .then().statusCode(200)
                .body(is("Hi, nice to see you here Ivan"));

    }

    @Test
    void testPostHelloWorld(RequestSpecification spec){
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "Ivan");

        spec
                .when()
                    .contentType(ContentType.JSON)
                    .queryParam("name", "Ivan")
                    .post("/greet/post")
                            .then()
                                .statusCode(200)
                                .body(is("Hi, nice to see you here Ivan"));

    }

}