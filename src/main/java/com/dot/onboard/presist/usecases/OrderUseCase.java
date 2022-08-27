/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.order.ListOrderCreationDto;
import com.dot.onboard.applications.requests.v1.order.OrderCreationDto;
import com.dot.onboard.applications.requests.v1.order.OrderParams;
import com.dot.onboard.applications.response.v1.Pagination;
import com.dot.onboard.applications.response.v1.order.OrderDetail;
import com.dot.onboard.exceptions.custom.PaymentMethodNotFound;
import com.dot.onboard.exceptions.custom.UserNotFound;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.order.Order;
import com.dot.onboard.presist.models.order.PaymentMethod;
import com.dot.onboard.presist.models.orderItem.OrderItem;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.repos.movieSchedule.MovieScheduleRepo;
import com.dot.onboard.presist.repos.order.OrderRepo;
import com.dot.onboard.presist.repos.order.OrderSpecification;
import com.dot.onboard.presist.repos.UserRepo;
import com.dot.onboard.utility.JwtTokenUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
@Slf4j
public class OrderUseCase {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private MovieScheduleRepo movieShcRepo;
    
    @Autowired
    private OrderSpecification orderSpec;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Cacheable(Config.ORDER_ALL_CACHE)
    public Pagination<OrderDetail> getAll(OrderParams params) {
        var orders = orderRepo.findAll(orderSpec.filter(params),PageRequest.of(params.getPage(), Config.ITEMS_PER_PAGE));
        var orderDetails = new ArrayList<OrderDetail>();
        orders.forEach((e) -> orderDetails.add(OrderDetail.fromEntity(e)));
        
        var data = new Pagination(orders);
        data.setItems(orderDetails.toArray());
        
        return data;
    }

    @Cacheable(Config.ORDER_ALL_MINE_CACHE)
    public Pagination<OrderDetail> getAllMine(HttpServletRequest req, OrderParams params) {String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        
        var orders = orderRepo.findAllByUser(user,PageRequest.of(params.getPage(), Config.ITEMS_PER_PAGE));
        var orderDetails = new ArrayList<OrderDetail>();
        orders.forEach((e) -> orderDetails.add(OrderDetail.fromEntity(e)));
        
        var data = new Pagination(orders);
        data.setItems(orderDetails.toArray());
        return data;
    }

    public OrderDetail detailMine(Long id, HttpServletRequest req) {
       String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        var data = orderRepo.findByIdAndUser(id, user).orElseThrow();
        return OrderDetail.fromEntity(data);
    }

    public OrderDetail detail(Long id) {
        var data = orderRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Order not found"));
        return OrderDetail.fromEntity(data);
    }

    @CacheEvict(value = {Config.ORDER_ALL_CACHE, Config.ORDER_ALL_MINE_CACHE}, allEntries = true)
    public OrderDetail create(ListOrderCreationDto dto, HttpServletRequest req) {
        String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        var order = new Order();
        order.setUser(user);
        
        var orderItems = new HashSet<OrderItem>();
        Double totalPrice = 0.0;
        for (OrderCreationDto e : dto.getItems()) {
            var movieSch = movieShcRepo.findById(e.getMovieScheduleId()).orElseThrow(()-> new NoSuchElementException("Movie not found"));
            var orderItem = new OrderItem();
            orderItem.setMovieSchedule(movieSch);
            orderItem.setPrice(movieSch.getPrice());
            orderItem.setOrder(order);
            orderItem.setQty(e.getQty());
            orderItem.setSubTotalPrice(movieSch.getPrice());
            totalPrice += movieSch.getPrice() *e.getQty();
            orderItems.add(orderItem);
        }
        switch(dto.getPaymentMethod().toUpperCase()){
            case "CASH":
                order.setPaymentMethod(PaymentMethod.CASH);
                break;
            case "BANK": 
                order.setPaymentMethod(PaymentMethod.BANK);
                break;
            default:
                log.error("Payment Not Found");
                throw new PaymentMethodNotFound();
        }
        order.setTotalItemPrice(totalPrice);
        order.setOrderItems(orderItems);
        var newOrder = orderRepo.save(order);
        return OrderDetail.fromEntity(newOrder);
    }


    

}
