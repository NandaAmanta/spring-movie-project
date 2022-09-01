/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.applications.requests.v1.movieSchedule.MovieSchduleCreationDto;
import com.dot.onboard.applications.requests.v1.movieTag.MovieTagCreationDto;
import com.dot.onboard.applications.requests.v1.order.OrderParams;
import com.dot.onboard.applications.requests.v1.studio.StudioCreationDto;
import com.dot.onboard.applications.requests.v1.tag.TagCreationDto;
import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.MovieScheduleUseCase;
import com.dot.onboard.presist.usecases.MovieTagUseCase;
import com.dot.onboard.presist.usecases.OrderUseCase;
import com.dot.onboard.presist.usecases.StudioUseCase;
import com.dot.onboard.presist.usecases.TagUseCase;
import com.dot.onboard.presist.usecases.UserUseCase;
import com.dot.onboard.applications.response.v1.Response;
import com.dot.onboard.applications.response.v1.ResponseSuccess;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.BACKOFFICE)
public class BackOfficeController {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private OrderUseCase orderUseCase;

    @Autowired
    private StudioUseCase studioUseCase;

    @Autowired
    private MovieScheduleUseCase movieScheduleUseCase;

    @Autowired
    private TagUseCase tagUseCase;

    @Autowired
    private MovieTagUseCase movieTagUseCase;

    @GetMapping(Routes.USER)
    public ResponseEntity<Response> getAllUser() {
        ResponseSuccess response = new ResponseSuccess();
        var data = userUseCase.getAll();
        response.setData(data);
        response.setMessage("Success get all users");
        return ResponseEntity.ok(response);
    }

    @GetMapping(Routes.ORDER)
    public ResponseEntity<Response> getAllOrder(OrderParams params) {
        ResponseSuccess response = new ResponseSuccess();
        var data = orderUseCase.getAll(params);
        response.setData(data);
        response.setMessage("Success get all orders");
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.STUDIO)
    public ResponseEntity<Response> createStudio(@RequestBody StudioCreationDto dto) {
        ResponseSuccess response = new ResponseSuccess();
        var data = studioUseCase.create(dto);
        response.setMessage("Success create new studio");
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.MOVIE_SCHEDULE)
    public ResponseEntity<Response> createMovieSchedule(@RequestBody MovieSchduleCreationDto dto) {
        ResponseSuccess response = new ResponseSuccess();
        var data = movieScheduleUseCase.create(dto);
        response.setMessage("Success create a movie schedule");
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.TAG)
    public ResponseEntity<Response> createTag(@RequestBody TagCreationDto dto) {
        ResponseSuccess response = new ResponseSuccess();
        var data = tagUseCase.create(dto);
        response.setMessage("Success create a tag");
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.MOVIE_TAG)
    public ResponseEntity<Response> createMovieTag(MovieTagCreationDto dto) {
        ResponseSuccess response = new ResponseSuccess();
        var data = movieTagUseCase.create(dto);
        response.setMessage("Success add a tag on a movie");
        return ResponseEntity.ok(response);
    }

}
