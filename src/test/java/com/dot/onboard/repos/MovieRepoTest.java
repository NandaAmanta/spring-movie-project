/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.repos;

import com.dot.onboard.applications.requests.v1.movie.MovieSearchParams;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.repos.movie.MovieRepo;
import com.dot.onboard.presist.repos.movie.MovieSpecification;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author ASUS ROG
 */
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieRepoTest {

    @Autowired
    private MovieRepo movieRepo;
    
    @Autowired
    private MovieSpecification movieSpec;

    @BeforeAll
    public void setUp() {
        long id = 1;
        var movie = new Movie();
        movie.setOverview("this is good movie");
        movie.setPoster("path");
        movie.setTitle("Azab Indosiar");
        movie.setId(id);
        movieRepo.save(movie);
    }

    @AfterAll
    public void clear() {
        movieRepo.deleteAll();
    }

    @Test
    public void findAll_movieExist_ListMovie() {
        // when
        var movies = movieRepo.findAll();

        // result
        Assertions.assertFalse(movies.isEmpty());
    }
    
    @Test
    public void findAll_movieSpecExist_listMovie(){
        // given
        var movieParams = new MovieSearchParams();
        movieParams.setKeyword("azab");
        var movieSpecification = movieSpec.filter(movieParams);
        
        // then
        var movies = movieRepo.findAll(movieSpecification, PageRequest.of(0, Config.ITEMS_PER_PAGE));
        
        // result
        Assertions.assertFalse(movies.isEmpty());
                
    }

}
