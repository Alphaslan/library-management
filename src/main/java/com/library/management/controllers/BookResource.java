package com.library.management.controllers;

import com.library.management.entities.Book;
import com.library.management.dao.BookRepository;
import com.library.management.dao.IssuedBooksRepository;
import com.library.management.util.BookValidator;
import com.library.management.exception.BookBadRequest;
import com.library.management.exception.BookNotFoundException;
import com.library.management.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/books")
public class BookResource {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssuedBooksRepository rentRepository;

    @Autowired
    private BookRepository repository;

    @Autowired
    BookValidator validator;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<Book> findAll() {
        LOGGER.info("findAll called");
        List<Book> list = new ArrayList<Book>();
        try {
            list = repository.findAll();
            if (list.size() == 0) {
                LOGGER.severe("No book found");
                throw new BookNotFoundException(5);
            }
        } catch (BookNotFoundException exc) {
            LOGGER.severe(exc.toString());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Book Found", exc);
        }
        return list;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newBook) {

        if (validator.isValid(newBook))
            return repository.save(newBook);
        else {
            LOGGER.severe("newBook is not valid");
            throw new BookBadRequest();

        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/author")
    public List<Book> searchBooksByAuthor(@RequestParam(value = "author") String author) {
        List<Book> books = bookRepository.findAll();
        ArrayList<Book> list = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) list.add(book);
        }
        return list;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/subject")
    public List<Book> searchBooksBySubject(@RequestParam(value = "subject") String subject) {
        List<Book> books = bookRepository.findAll();
        ArrayList<Book> list = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getSubject().equals(subject)) list.add(book);
        }
        return list;
    }
}
