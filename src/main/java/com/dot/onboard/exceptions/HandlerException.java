/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions;

import com.dot.onboard.exceptions.custom.CustomDataNotFoundException;
import com.dot.onboard.exceptions.custom.UserNotFound;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseFail;
import io.jsonwebtoken.SignatureException;
import io.sentry.Sentry;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author ASUS
 */
@RestControllerAdvice
@Slf4j
public class HandlerException extends ResponseEntityExceptionHandler {

    private final ResponseFail response = new ResponseFail();

    @ExceptionHandler({AuthenticationException.class, SignatureException.class})
    public ResponseEntity<Response> handleAuthenticationException() {
        response.setMessage("You are not authenticated, need a valid token");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handleAccessDeniedException() {
        response.setMessage("Access Denied");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Response> handleUserNotFound(UserNotFound exception) {
        response.setMessage(exception.getMessage());
        response.setErrors(List.of("User not found"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Response> handleNoResultException(NoResultException exception) {
        response.setMessage(exception.getMessage());
        response.setErrors(List.of(""));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response> handleNoSuchElementException(NoSuchElementException exception) {
        response.setMessage(exception.getMessage() == null ? "Data not found" : exception.getMessage());
        response.setErrors(List.of(""));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomDataNotFoundException.class)
    public ResponseEntity<Response> handleCustomDataNotFoundException(CustomDataNotFoundException exception) {
        response.setMessage(exception.getMessage());
        response.setErrors(List.of(exception.getErrors()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException exception) {
        response.setMessage("Bad Request");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleUnknownError(Exception exception) {
        log.error(exception.getMessage());
        Sentry.captureException(exception);
        Sentry.captureMessage(exception.getMessage());
        
        response.setMessage("Internal Server Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        response.setMessage("Method not allowed");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        response.setMessage("Resource not found");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        response.setMessage("Bad Request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        response.setMessage("Bad Request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
