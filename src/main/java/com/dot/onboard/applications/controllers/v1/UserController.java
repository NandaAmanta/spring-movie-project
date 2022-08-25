/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.applications.requests.v1.user.UserUpdateDto;
import com.dot.onboard.applications.response.v1.user.UserDetail;
import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usercases.UserUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
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
        var user = userUseCase.detailByHttpServletRequest(req);
        UserDetail userDetail = UserDetail.fromEntity(user);

        response.setMessage("Success get detail user");
        response.setData(userDetail);
        return ResponseEntity.ok(response);
    }

    @PutMapping(Routes.ME)
    public ResponseEntity<Response> update(@ModelAttribute UserUpdateDto dto, HttpServletRequest req) throws IOException {
        var user = userUseCase.update(dto, req);
        UserDetail userDetail = UserDetail.fromEntity(user);

        response.setData(userDetail);
        response.setMessage("Success update user");
        return ResponseEntity.ok(response);
    }

}
