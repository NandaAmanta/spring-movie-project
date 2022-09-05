/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.applications.requests.v1.user.UserUpdateDto;
import com.dot.onboard.applications.response.v1.user.UserDetail;
import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.UserUseCase;
import com.dot.onboard.applications.response.v1.Response;
import com.dot.onboard.applications.response.v1.ResponseSuccess;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.USER)
public class UserController {

    @Autowired
    private UserUseCase userUseCase;

    private final ResponseSuccess response = new ResponseSuccess();

    @GetMapping(Routes.ME)
    public ResponseEntity<Response> selfDetail(HttpServletRequest req) {
        var data = userUseCase.detailByHttpServletRequest(req);
        response.setMessage("Success get detail user");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping(Routes.ME)
    public ResponseEntity<Response> update(@ModelAttribute @Valid UserUpdateDto dto, HttpServletRequest req) throws IOException {
        var data = userUseCase.update(dto, req);
        response.setData(data);
        response.setMessage("Success update user");
        return ResponseEntity.ok(response);
    }

}
