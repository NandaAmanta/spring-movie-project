/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.order.OrderCreationDto;
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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
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
    public List<Order> getAll(){
        var data = orderRepo.findAll();
        return data;
    }
    
    @Cacheable(Config.ORDER_ALL_MINE_CACHE)
    public List<Order> getAllMine(HttpServletRequest req){
        String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        var data = orderRepo.findAllByUser(user);
        return data;
    }
    
    public Order detailMine(Long id,HttpServletRequest req){
        String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        var data = orderRepo.findByIdAndUser(id,user).orElseThrow();
        return data;
    }
    
    public Order detail(Long id){
        var data = orderRepo.findById(id).orElseThrow();
        return data;
    }
    
    
    @CacheEvict(value = {Config.ORDER_ALL_CACHE, Config.ORDER_ALL_MINE_CACHE}, allEntries = true)
    public Order create(OrderCreationDto dto){
        var movieSch = movieShcRepo.findById(dto.getMovieScheduleId()).orElseThrow();
        var order = new Order();
        switch(dto.getPaymentMethod().toUpperCase()){
            case "CASH":
                order.setPaymentMethod(PaymentMethod.CASH);
                break;
            case "BANK": 
                order.setPaymentMethod(PaymentMethod.BANK);
                break;
            default:
                throw new PaymentMethodNotFound();
        }
        var newOrder = orderRepo.save(order);
        var orderItem = new OrderItem();
        orderItem.setMovieSchedule(movieSch);
        orderItem.setPrice(movieSch.getPrice());
        orderItem.setOrder(order);
        orderItem.setQty(dto.getQty());
        orderItemRepo.save(orderItem);
        
        return newOrder;
    }
    
}
