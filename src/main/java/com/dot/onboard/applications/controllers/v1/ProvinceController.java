/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.ProvinceUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
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
@RequestMapping(Routes.API_V1+Routes.PROVINCE)
public class ProvinceController {

    @Autowired
    private ProvinceUseCase provinceUseCase;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        var data = provinceUseCase.getAll();
        var response = new ResponseSuccess();
        response.setMessage("Success get all provinces");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

}
