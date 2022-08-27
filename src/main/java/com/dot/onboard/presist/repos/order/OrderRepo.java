/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.repos.order;

import com.dot.onboard.presist.models.order.Order;
import com.dot.onboard.presist.models.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASUS
 */
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);
    
    Page<Order> findAll(Pageable page);
    
    Page<Order> findAll(Specification<Order> spec, Pageable page);
    
    Page<Order> findAllByUser(User user,Pageable page);

    Optional<Order> findByIdAndUser(Long id, User user);
}
