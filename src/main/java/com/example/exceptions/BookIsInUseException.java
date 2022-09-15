package com.example.exceptions;

public class BookIsInUseException extends RuntimeException{

    BookIsInUseException(String msg){
        super(msg);
    }

    public BookIsInUseException() {
        super("Book is already in use or do not exist");
    }
}
