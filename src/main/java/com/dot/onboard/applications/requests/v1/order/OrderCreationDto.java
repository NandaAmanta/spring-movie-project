/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.order;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class OrderCreationDto {

    @NotNull(message = "movieScheduleId can't be empty")
    private Long movieScheduleId;

    @NotNull(message = "qty can't be empty")
    private Integer qty;
}
