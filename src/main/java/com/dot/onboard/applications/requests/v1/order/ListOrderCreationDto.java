/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.order;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class ListOrderCreationDto {

    @NotNull(message = "payment method can't be empty")
    private String paymentMethod;

    @NotNull(message = "items method can't be empty")
    private List<OrderCreationDto> items;
}
