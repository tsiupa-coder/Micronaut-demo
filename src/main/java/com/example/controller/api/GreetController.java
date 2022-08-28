package com.example.controller.api;

import com.example.service.BookService;
import com.example.service.GreetingService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.View;
import jakarta.inject.Inject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class GreetController {

    @Inject
    private final GreetingService greetingService;

    @Inject
    private final BookService bookService;

    public GreetController(GreetingService greetingService, BookService bookService) {
        this.greetingService = greetingService;
        this.bookService = bookService;
    }

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
                .orElse("Nobody");

        return HttpResponse.ok("Hello " + name + "!!")
                .header("X-My-Header", "Hi in headers");
    }

    @Produces(MediaType.TEXT_HTML)
    @Get
    public String readMe() {
        return """
                        <!DOCTYPE html>
                        <html>
                            <head>
                                <title>README.md</title>
                            </head>
                            <body>
                                <h1>Micronaut 3.5.1 Documentation </h1>
                                <a href="https://docs.micronaut.io/3.5.1/guide/index.html">User Guide</a>
                                <a href="https://docs.micronaut.io/3.5.1/api/index.html">API Reference</a>
                                <a href="https://docs.micronaut.io/3.5.1/guide/configurationreference.html">Configuration Reference</a>
                                <a href="https://guides.micronaut.io/index.html">Micronaut Guides</a>
                                <h1>Feature http-client documentation</h1>
                                <a href="https://docs.micronaut.io/latest/guide/index.html#httpClient">Micronaut HTTP Client documentation</a>
                            </body>
                        </html>
                """;
    }

    @View("books/books")
    @Get("/test")
    public Map getHome() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        return CollectionUtils.mapOf( "books", bookService.findAll());
    }
}