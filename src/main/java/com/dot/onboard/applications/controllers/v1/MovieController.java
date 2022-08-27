/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.applications.requests.v1.movie.MovieSearchParams;
import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.usecases.MovieUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping(Routes.API_V1 + Routes.MOVIE)
public class MovieController {

    @Autowired
    private MovieUseCase movieUseCase;
    
    private final ResponseSuccess response = new ResponseSuccess();

    @GetMapping
    public ResponseEntity<Response> getAll(@Nullable MovieSearchParams params) {
        var data = movieUseCase.getAll(params);
        response.setData(data);
        response.setMessage("Success get all movies");
        return ResponseEntity.ok(response);
    }

}
