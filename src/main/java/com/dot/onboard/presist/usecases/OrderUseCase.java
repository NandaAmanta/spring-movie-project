/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.order.ListOrderCreationDto;
import com.dot.onboard.applications.requests.v1.order.OrderCreationDto;
import com.dot.onboard.applications.response.v1.order.OrderDetail;
import com.dot.onboard.exceptions.custom.PaymentMethodNotFound;
import com.dot.onboard.exceptions.custom.UserNotFound;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.order.Order;
import com.dot.onboard.presist.models.order.PaymentMethod;
import com.dot.onboard.presist.models.orderItem.OrderItem;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.repos.MovieScheduleRepo;
import com.dot.onboard.presist.repos.OrderItemRepo;
import com.dot.onboard.presist.repos.OrderRepo;
import com.dot.onboard.presist.repos.UserRepo;
import com.dot.onboard.utility.JwtTokenUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    private OrderItemRepo orderItemRepo;

    @Autowired
    private MovieScheduleRepo movieShcRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Cacheable(Config.ORDER_ALL_CACHE)
    public List<OrderDetail> getAll() {
        var data = orderRepo.findAll();
        var orderDetails = new ArrayList<OrderDetail>();
        data.forEach((e) -> orderDetails.add(OrderDetail.fromEntity(e)));
        return orderDetails;
    }

    @Cacheable(Config.ORDER_ALL_MINE_CACHE)
    public List<OrderDetail> getAllMine(HttpServletRequest req) {String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        var data = orderRepo.findAllByUser(user);

        var orderDetails = new ArrayList<OrderDetail>();
        data.forEach((e) -> orderDetails.add(OrderDetail.fromEntity(e)));

        return orderDetails;
    }

    public OrderDetail detailMine(Long id, HttpServletRequest req) {
       String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        var data = orderRepo.findByIdAndUser(id, user).orElseThrow();
        return OrderDetail.fromEntity(data);
    }

    public OrderDetail detail(Long id) {
        var data = orderRepo.findById(id).orElseThrow();
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
            var movieSch = movieShcRepo.findById(e.getMovieScheduleId()).orElseThrow();
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
