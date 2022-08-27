/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.orderItem;

import com.dot.onboard.applications.response.v1.movie.MovieDetail;
import com.dot.onboard.applications.response.v1.studio.StudioDetail;
import com.dot.onboard.presist.models.orderItem.OrderItem;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderItemDetail {
    private Long id;
    private MovieDetail movie;
    private StudioDetail studio;
    private Integer qty;
    private Double price;
    private Double subTotalPrice;
    
    public static OrderItemDetail fromEntity(OrderItem entity){
        var orderItemDetail = new OrderItemDetail();
        orderItemDetail.setId(entity.getId());
        orderItemDetail.setMovie(MovieDetail.fromEntity(entity.getMovieSchedule().getMovie()));
        orderItemDetail.setStudio(StudioDetail.fromEntity(entity.getMovieSchedule().getStudio()));
        orderItemDetail.setQty(entity.getQty());
        orderItemDetail.setPrice(entity.getPrice());
        orderItemDetail.setSubTotalPrice(entity.getSubTotalPrice());
        return orderItemDetail;
    }
    
}
