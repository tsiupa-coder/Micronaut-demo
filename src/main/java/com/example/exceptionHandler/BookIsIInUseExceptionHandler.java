package com.example.exceptionHandler;

import com.example.exceptions.BookIsInUseException;
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
@Requires(classes = {BookIsInUseException.class, ExceptionHandler.class})
public class BookIsIInUseExceptionHandler implements ExceptionHandler<BookIsInUseException, HttpResponse> {

    private final ErrorResponseProcessor<?> errorResponseProcessor;

    public BookIsIInUseExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    @Override
    public HttpResponse handle(HttpRequest request, BookIsInUseException e) {
        return errorResponseProcessor.processResponse
                (ErrorContext.builder(request)
                        .cause(e)
                        .errorMessage(e.getMessage())
                        .build(), HttpResponse.badRequest());
    }
}
