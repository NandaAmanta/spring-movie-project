/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.applications.requests.v1.order.ListOrderCreationDto;
import com.dot.onboard.applications.requests.v1.order.OrderCreationDto;
import com.dot.onboard.applications.requests.v1.order.OrderSearchParams;
import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.OrderUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.ORDER)
public class OrderController {

    private final ResponseSuccess response = new ResponseSuccess();

    @Autowired
    private OrderUseCase orderUseCase;

    @GetMapping
    public ResponseEntity<Response> getAllMine(HttpServletRequest req, OrderSearchParams params) {
        var data = orderUseCase.getAllMine(req, params);
        response.setMessage("Success get all my orders");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping(Routes.ID)
    public ResponseEntity<Response> detailMine(@PathVariable Long id, HttpServletRequest req) {
        var data = orderUseCase.detailMine(id, req);
        response.setMessage("Success get detail my order");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody ListOrderCreationDto dto, HttpServletRequest req) {
        var data = orderUseCase.create(dto, req);
        response.setMessage("Success create new order");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

}
