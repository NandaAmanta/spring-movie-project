/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.applications.requests.v1.movieSchedule.MovieScheduleSearchParams;
import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.usecases.MovieScheduleUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.MOVIE + Routes.MOVIE_SCHEDULE)
public class MovieScheduleController {

    private final ResponseSuccess response = new ResponseSuccess();

    @Autowired
    private MovieScheduleUseCase movieScheduleUseCase;

    @GetMapping
    public ResponseEntity<Response> getAll(MovieScheduleSearchParams params) {
        var data = movieScheduleUseCase.getAll(params);
        response.setData(data);
        response.setMessage("Success get all movie schedules");
        return ResponseEntity.ok(response);
    }

    @GetMapping(Routes.ID)
    public ResponseEntity<Response> detail(@PathVariable Long id) {
        var data = movieScheduleUseCase.detail(id);
        response.setData(data);
        response.setMessage("Success get detail movie schedule");
        return ResponseEntity.ok(response);
    }
}
