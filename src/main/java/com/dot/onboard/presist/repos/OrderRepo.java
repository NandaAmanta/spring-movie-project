/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.repos;

import com.dot.onboard.presist.models.order.Order;
import com.dot.onboard.presist.models.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASUS
 */
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    Optional<Order> findByIdAndUser(Long id, User user);
}
