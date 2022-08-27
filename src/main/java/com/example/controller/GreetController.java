package com.example.controller;

import com.example.service.GreetingService;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

@Controller("/greet")
public class GreetController {

    @Inject
    private GreetingService greetingService;

    @Version("1.0")
    @Get("/{name}")
    public String greet(String name) {
        return greetingService.getGreeting() + " " + name;
    }

    @Version("1.1")
    @Produces(MediaType.TEXT_PLAIN)
    @Get(value = "/hello")
    public HttpResponse<String> hello(HttpRequest<?> request) {

        String name = request.getParameters()
                .getFirst("name")
                .orElse("Nobody"); //

        return HttpResponse.ok("Hello " + name + "!!")
                .header("X-My-Header", "Hi in headers"); //
    }

    @Version("1.1")
    @Post("/post")
    public String setGreeting(@QueryValue String name) {
        return greetingService.getGreeting() + " " + name;
    }
}