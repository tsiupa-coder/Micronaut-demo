package com.example.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String msg) {
        super(msg);
    }

    public ItemNotFoundException() {
        super("Item not found");
    }
}