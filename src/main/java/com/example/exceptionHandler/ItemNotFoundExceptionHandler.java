package com.example.exceptionHandler;


import com.example.exceptions.ItemNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {ItemNotFoundException.class, ExceptionHandler.class})
public class ItemNotFoundExceptionHandler implements ExceptionHandler<ItemNotFoundException, HttpResponse> {

    private final ErrorResponseProcessor<?> errorResponseProcessor;

    public ItemNotFoundExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    @Override
    public HttpResponse handle(HttpRequest request, ItemNotFoundException e) {
        return errorResponseProcessor.processResponse
                (ErrorContext.builder(request)
                        .cause(e)
                        .errorMessage(e.getMessage())
                        .build(), HttpResponse.badRequest());
    }

}