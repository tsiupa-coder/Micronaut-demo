package com.example.exceptions;

import static com.example.generall.Constraints.ITEM_NOT_FOUND;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String msg) {
        super(msg);
    }

    public ItemNotFoundException() {
        super(ITEM_NOT_FOUND);
    }
}