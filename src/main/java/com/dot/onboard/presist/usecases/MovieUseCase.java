/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.movie.MovieSearchParams;
import com.dot.onboard.applications.response.v1.Pagination;
import com.dot.onboard.applications.response.v1.movie.MovieDetail;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.repos.MovieRepo;
import com.dot.onboard.presist.repos.movie.MovieSpecification;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    
    @Autowired
    private MovieSpecification movieSpecification;

    @Cacheable(Config.MOVIE_ALL_CACHE)
    public Pagination<MovieDetail> getAll(MovieSearchParams params) {
        Page<Movie> movies = movieRepo.findAll(movieSpecification.filter(params),PageRequest.of(params.getPage(), 10));
        var movieDetails = new ArrayList<MovieDetail>();
        movies.forEach((e) -> movieDetails.add(MovieDetail.fromEntity(e)));
        var data = new Pagination(movies);
        data.setItems(movieDetails.toArray());
        return data;
    }

    @Cacheable(Config.MOVIE_DETAIL_CACHE)
    public MovieDetail detailById(Long id) {
        Movie movie = movieRepo.findById(id).orElseThrow();
        return MovieDetail.fromEntity(movie);
    }

}
