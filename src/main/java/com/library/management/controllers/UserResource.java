package com.library.management.controllers;

import com.library.management.entities.User;
import com.library.management.dao.UserRepository;
import com.library.management.util.UserValidator;
import com.library.management.exception.BadUserRequest;
import com.library.management.exception.UserNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    private UserRepository repository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<User> findAll() {
        return repository.findAll();
    }


    //test this
    @ApiOperation("This Api will create a new user")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) {
        UserValidator validator = new UserValidator();
        if (validator.isValid(newUser))
            return repository.save(newUser);
        else {
            LOGGER.severe("New user is not valid");
            throw new BadUserRequest();
        }
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    User findOne(@PathVariable int id) {
        LOGGER.info("/users/{id} called with id " + id);

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
