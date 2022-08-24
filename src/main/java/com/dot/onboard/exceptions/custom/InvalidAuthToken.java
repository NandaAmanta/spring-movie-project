/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions.custom;

/**
 *
 * @author ASUS
 */
public class InvalidAuthToken extends RuntimeException {

    public InvalidAuthToken() {
    }

    public InvalidAuthToken(String message) {
        super(message);
    }

    public InvalidAuthToken(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAuthToken(Throwable cause) {
        super(cause);
    }

}
