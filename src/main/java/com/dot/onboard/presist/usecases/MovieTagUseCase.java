/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.movieTag.MovieTagCreationDto;
import com.dot.onboard.presist.models.movieTags.MovieTags;
import com.dot.onboard.presist.repos.movie.MovieRepo;
import com.dot.onboard.presist.repos.MovieTagRepo;
import com.dot.onboard.presist.repos.TagRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class MovieTagUseCase {

    @Autowired
    private MovieTagRepo movieTagRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TagRepo tagRepo;

    public List<MovieTags> getAll() {
        var movieTags = movieTagRepo.findAll();
        return movieTags;
    }

    public MovieTags create(MovieTagCreationDto dto) {
        var tag = tagRepo.findById(dto.getTagId()).orElseThrow();
        var movie = movieRepo.findById(dto.getMovieId()).orElseThrow();
        var movieTag = new MovieTags();
        movieTag.setMovie(movie);
        movieTag.setTag(tag);
        return movieTagRepo.save(movieTag);
    }

}
