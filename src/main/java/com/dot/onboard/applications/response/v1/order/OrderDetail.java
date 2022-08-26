/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.order;

import com.dot.onboard.applications.response.v1.orderItem.OrderItemDetail;
import com.dot.onboard.presist.models.order.Order;
import com.dot.onboard.presist.models.order.PaymentMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author ASUS
 */
@Data

@Slf4j
public class OrderDetail {
    private Long id;
    private PaymentMethod paymentMethod;
    private List<OrderItemDetail> orderItems;
    private Integer totalItemPrice;
    
    public static OrderDetail fromEntity(Order entity){
        var orderDetail = new OrderDetail();
        orderDetail.setId(entity.getId());
        orderDetail.setPaymentMethod(entity.getPaymentMethod());
        orderDetail.setTotalItemPrice(entity.getTotalItemPrice().intValue());
        List<OrderItemDetail> orderItems = new ArrayList<>();
        entity.getOrderItems().forEach((e)-> orderItems.add(OrderItemDetail.fromEntity(e)));
        orderDetail.setOrderItems(orderItems);
        return orderDetail;
    }
}
