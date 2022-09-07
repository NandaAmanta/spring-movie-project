/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.repos;

import com.dot.onboard.global.Config;
import com.dot.onboard.kernel.configs.PasswordEncoder;
import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.models.order.Order;
import com.dot.onboard.presist.models.order.PaymentMethod;
import com.dot.onboard.presist.models.orderItem.OrderItem;
import com.dot.onboard.presist.models.studio.Studio;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.repos.StudioRepo;
import com.dot.onboard.presist.repos.UserRepo;
import com.dot.onboard.presist.repos.movie.MovieRepo;
import com.dot.onboard.presist.repos.movieSchedule.MovieScheduleRepo;
import com.dot.onboard.presist.repos.order.OrderRepo;
import java.util.Date;
import java.util.Set;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author ASUS ROG
 */
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderRepoTest {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudioRepo studioRepo;

    @Autowired
    private MovieScheduleRepo movieScheduleRepo;

    @Autowired
    private MovieRepo movieRepo;

    private User existUser;

    @BeforeAll
    public void setUp() {
        User user = new User();
        user.setName("I Putu Nanda Amanta");
        user.setEmail("nanda@gmail.com");
        user.setIsAdmin(Boolean.FALSE);
        user.setPassword(passwordEncoder.getEncoder().encode("password"));
        this.existUser = userRepo.save(user);

        long id = 2;
        var movie = new Movie();
        movie.setOverview("this is good movie");
        movie.setPoster("path");
        movie.setTitle("Azab Indosiar");
        movie.setId(id);
        var savedMovie = movieRepo.save(movie);

        var studio = new Studio();
        studio.setSeatCapacity(20);
        studio.setStudioNumber(1);
        var savedStudio = studioRepo.save(studio);

        var movieSchedule = new MovieSchedule();
        movieSchedule.setDate(new Date());
        movieSchedule.setPrice(1000.0);
        movieSchedule.setEndTime("15:00");
        movieSchedule.setStartTime("13:00");
        movieSchedule.setMovie(savedMovie);
        movieSchedule.setStudio(savedStudio);
        var savedMovieSchedule = movieScheduleRepo.save(movieSchedule);

        int qty = 2;
        double subTotalPrice = 2000.0;
        var order = new Order();
        var orderItems = new OrderItem();
        orderItems.setMovieSchedule(savedMovieSchedule);
        orderItems.setPrice(movieSchedule.getPrice());
        orderItems.setQty(qty);
        orderItems.setSubTotalPrice(subTotalPrice);
        orderItems.setOrder(order);

        double totalItemPrice = orderItems.getPrice() * orderItems.getQty();
        order.setOrderItems(Set.of(orderItems));
        order.setTotalItemPrice(totalItemPrice);
        order.setUser(user);
        order.setPaymentMethod(PaymentMethod.CASH);
        orderRepo.save(order);
    }

    @AfterAll
    public void clear() {
        orderRepo.deleteAll();
        movieScheduleRepo.deleteAll();
        studioRepo.deleteAll();
        movieRepo.deleteAll();
        userRepo.delete(existUser);
    }

    @Test
    public void findAllByUser_orderExist_pageOrder() {
        // given 
        User user = existUser;

        // then
        Page<Order> orders = orderRepo.findAllByUser(user, PageRequest.of(0, Config.ITEMS_PER_PAGE));

        // result 
        Assertions.assertFalse(orders.isEmpty());
    }

}
