package com.example.controller;

import com.example.model.Book;
import com.google.gson.reflect.TypeToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class BookControllerTest {


    @Test
    void testInitDB(RequestSpecification spec) {
        spec
                .when()
                .get("/book/init")
                .then()
                .statusCode(200);
    }

    @Test
    void testGetBooks(RequestSpecification spec) {
        Response res = spec
                .when()
                .get("/book")
                .andReturn();

        res.then().statusCode(200);

        List<Book> list = res.body().as(new TypeToken<ArrayList<Book>>() {
        }.getType());

        assertTrue(list.size() == 7 || list.size() == 8);
    }

}