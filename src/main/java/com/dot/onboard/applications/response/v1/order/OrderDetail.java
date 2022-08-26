/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.order;

import com.dot.onboard.applications.response.v1.orderItem.OrderItemDetail;
import com.dot.onboard.presist.models.order.PaymentMethod;
import com.dot.onboard.presist.models.orderItem.OrderItem;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class OrderDetail {
    private PaymentMethod paymentMethod;
    private Set<OrderItem> orderItems;
    private Double totalItemPrice;
}
