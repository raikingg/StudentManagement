package com.students.StudentManagement.exception;

import co.elastic.clients.elasticsearch._types.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.NoSuchElementException;

@ControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler(value = Exception.class) // mechanism to treat exception.
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {StudentNotFoundException.class,NoSuchElementException.class})
    public ResponseEntity<String> handleException(StudentNotFoundException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }



}