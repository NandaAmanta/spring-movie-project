/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions.custom;

/**
 *
 * @author ASUS
 */
public class UserNotFound extends RuntimeException {

    public UserNotFound() {
    }

    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFound(Throwable cause) {
        super(cause);
    }

}
