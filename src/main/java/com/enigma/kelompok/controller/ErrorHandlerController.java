package com.enigma.kelompok.controller;

import com.enigma.kelompok.utils.response.Res;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleConstraintViolation(MethodArgumentNotValidException ex) {
        return Res.renderJson(null, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
