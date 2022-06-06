package com.library.management.Util;


import com.library.management.DataAccessLayer.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {

    public boolean isValid(Book book) {
        return !book.getTitle().equals("") && book.getTitle() != null;
    }
}
