/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.controllers.v1;

import com.dot.onboard.global.Routes;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.usercases.MovieUseCase;
import com.dot.onboard.utility.Response;
import com.dot.onboard.utility.ResponseSuccess;
import java.util.List;
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
@RequestMapping(Routes.API_V1 + Routes.MOVIE)
public class MovieController {
    
    @Autowired
    private MovieUseCase movieUseCase;
    private final ResponseSuccess response = new ResponseSuccess();
    
    @GetMapping
    public ResponseEntity<Response> getAll(){
        List<Movie> movies = movieUseCase.getAll();
        response.setData(movies);
        response.setMessage("Success get all movies");
        return ResponseEntity.ok(response);
    }
    
    
    
}
