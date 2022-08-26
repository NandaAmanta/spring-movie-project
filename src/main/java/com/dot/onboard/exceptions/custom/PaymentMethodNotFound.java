/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.exceptions.custom;

/**
 *
 * @author ASUS
 */
public class PaymentMethodNotFound extends RuntimeException {

    public PaymentMethodNotFound() {
    }

    public PaymentMethodNotFound(String message) {
        super(message);
    }

    public PaymentMethodNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentMethodNotFound(Throwable cause) {
        super(cause);
    }

}
