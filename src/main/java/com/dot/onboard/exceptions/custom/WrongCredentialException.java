/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions.custom;

/**
 *
 * @author ASUS
 */
public class WrongCredentialException extends RuntimeException {

    public WrongCredentialException() {
    }

    public WrongCredentialException(String message) {
        super(message);
    }

    public WrongCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCredentialException(Throwable cause) {
        super(cause);
    }

}
