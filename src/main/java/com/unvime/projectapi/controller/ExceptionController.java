package com.unvime.projectapi.controller;

import com.unvime.projectapi.exceptions.ApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Log4j2
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiException> handler(ApiException e) {
        return new ResponseEntity(e, HttpStatus.resolve(e.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiException hadler(MethodArgumentNotValidException e) {
        StringBuilder mensaje = new StringBuilder();

        e.getFieldErrors()
                .forEach(error -> mensaje.append(error.getDefaultMessage() + "\n"));

        return new ApiException("argument_not_valid", mensaje.toString(), HttpStatus.BAD_REQUEST.value());
    }
    /**
     * captura las excepciones cuando no se envia el objecto esperado
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiException hadler(HttpMessageNotReadableException e) {
        return new ApiException("miss_body", "body is expected", HttpStatus.BAD_REQUEST.value());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiException hadler(Exception e) {
        e.printStackTrace();
        return new ApiException("internal_server_error", "Unexpected exception", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
