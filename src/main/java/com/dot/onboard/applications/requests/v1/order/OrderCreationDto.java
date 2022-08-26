/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.order;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class OrderCreationDto {

    private Long movieScheduleId;
    private String paymentMethod;
    private Integer qty;
}
