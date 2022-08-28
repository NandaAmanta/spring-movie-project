/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.applications.requests.v1.user.UserCreateDto;
import com.dot.onboard.applications.requests.v1.user.UserLoginDto;
import com.dot.onboard.applications.response.v1.user.UserDetail;
import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.AuthUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.AUTH)
public class AuthenticationController {

    @Autowired
    private AuthUseCase authUseCase;

    private final ResponseSuccess response = new ResponseSuccess();

    @PostMapping(Routes.SIGNUP)
    public ResponseEntity<Response> signup(@Valid @RequestBody UserCreateDto dto) {
        var data = authUseCase.signup(dto);
        response.setMessage("Success Signup");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.LOGIN)
    public ResponseEntity<Response> login(@Valid @RequestBody UserLoginDto dto) {
        var data = authUseCase.login(dto);
        response.setMessage("Success Login");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

}
