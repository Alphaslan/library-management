package com.library.management.controllers;

import com.library.management.entities.IssuedBooks;
import com.library.management.dao.IssuedBooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/issued-books")
public class IssuedBooksResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(IssuedBooksResource.class);
    @Autowired
    IssuedBooksRepository repository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<IssuedBooks> findAll() {
        return repository.findAll();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBooks issueBook(@RequestBody IssuedBooks issuedBooks) throws Exception {
        try {
            return repository.save(issuedBooks);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user")
    public List<IssuedBooks> searchIssuedBooksByUser(@RequestParam(value = "user") String userId) {
        LOGGER.info("searchIssuedBooksByUser called with userId " + userId);
        List<IssuedBooks> books = repository.findAll();
        ArrayList<IssuedBooks> list = new ArrayList<IssuedBooks>();
        for (IssuedBooks book : books) {
            int id = Integer.parseInt(userId);
            if (book.getUser_id() == id) {
                list.add(book);
            }
        }
        return list;
    }

}
