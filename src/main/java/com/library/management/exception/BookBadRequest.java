package com.library.management.exception;

public class BookBadRequest extends RuntimeException {

    public BookBadRequest() {
        super("The Request for new Book is invalid");
    }
}
