/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions.custom;

/**
 *
 * @author ASUS
 */
public class EmailAlreadyInUsedException extends RuntimeException {

    public EmailAlreadyInUsedException() {
    }

    public EmailAlreadyInUsedException(String message) {
        super(message);
    }

    public EmailAlreadyInUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyInUsedException(Throwable cause) {
        super(cause);
    }

}