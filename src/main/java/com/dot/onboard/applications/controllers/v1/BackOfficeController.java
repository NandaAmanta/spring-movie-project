/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usercases.UserUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.BACKOFFICE)
public class BackOfficeController {
    
    private final ResponseSuccess response= new ResponseSuccess();
    
    @Autowired
    private UserUseCase userUseCase;
    
    @GetMapping(Routes.USER)
    public ResponseEntity<Response> getAllUser(){
        var data = userUseCase.getAll();
        response.setData(data);
        response.setMessage("Success get all users");
        return ResponseEntity.ok(response);
    }
    
}
