package com.library.management.util;


import com.library.management.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {

    public boolean isValid(Book book) {
        return !book.getTitle().equals("") && book.getTitle() != null;
    }
}
