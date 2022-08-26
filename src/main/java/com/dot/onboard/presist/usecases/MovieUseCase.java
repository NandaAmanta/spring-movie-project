/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.repos.MovieRepo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
@Slf4j
public class MovieUseCase {

    @Autowired
    private MovieRepo movieRepo;

    @Cacheable(Config.MOVIE_ALL_CACHE)
    public List<Movie> getAll(String keyword , String date) {
        List<Movie> movies = movieRepo.findAll();
        return movies;
    }

    @Cacheable(Config.MOVIE_DETAIL_CACHE)
    public Movie detailById(Long id) {
        Movie movie = movieRepo.findById(id).orElseThrow();
        return movie;
    }

}
