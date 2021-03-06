package com.library.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        System.out.println("Could not find user");
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(BookBadRequest.class)
    public void BookBadHandler(HttpServletResponse response) throws IOException {
        System.out.println("Could not find user");
        response.sendError(HttpStatus.OK.value());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public void springHandleNotFound1(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}