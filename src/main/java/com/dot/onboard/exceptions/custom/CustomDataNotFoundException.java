/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions.custom;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class CustomDataNotFoundException extends RuntimeException {
    
    private List<String> errors;

    public CustomDataNotFoundException() {
    }

    public CustomDataNotFoundException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public CustomDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomDataNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * @return the errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}