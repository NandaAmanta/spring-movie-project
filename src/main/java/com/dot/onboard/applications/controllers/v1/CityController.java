/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.CityUseCase;
import com.dot.onboard.applications.response.v1.Response;
import com.dot.onboard.applications.response.v1.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS ROG
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.CITY)
public class CityController {

    @Autowired
    private CityUseCase cityUseCase;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        var data = cityUseCase.getAll();
        var response = new ResponseSuccess();
        response.setMessage("Success get all cities");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

}
