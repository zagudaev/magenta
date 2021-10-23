package ru.example.magenta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handlerException(RuntimeException ex){
        return  new ResponseEntity<Object>(new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
}
