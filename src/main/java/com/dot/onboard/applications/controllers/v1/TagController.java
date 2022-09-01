/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.TagUseCase;
import com.dot.onboard.applications.response.v1.Response;
import com.dot.onboard.applications.response.v1.ResponseSuccess;
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
@RequestMapping(Routes.API_V1 + Routes.TAG)
public class TagController {

    @Autowired
    private TagUseCase tagUseCase;

    private final ResponseSuccess response = new ResponseSuccess();

    @GetMapping
    public ResponseEntity<Response> getAll() {
        var data = tagUseCase.getAll();
        response.setData(data);
        response.setMessage("Success get all tags");
        return ResponseEntity.ok(response);
    }
}
