/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions;

import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseFail;
import io.jsonwebtoken.SignatureException;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author ASUS
 */
@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private final ResponseFail response = new ResponseFail();

    @ExceptionHandler({AccessDeniedException.class, AuthenticationException.class, SignatureException.class})
    public ResponseEntity<Response> handle() {
        response.setMessage("Access Denied");
        response.setErrors(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
