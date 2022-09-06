/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.repos;

import com.dot.onboard.applications.requests.v1.movie.MovieSearchParams;
import com.dot.onboard.applications.requests.v1.movieSchedule.MovieScheduleSearchParams;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.models.studio.Studio;
import com.dot.onboard.presist.repos.StudioRepo;
import com.dot.onboard.presist.repos.movie.MovieRepo;
import com.dot.onboard.presist.repos.movieSchedule.MovieScheduleRepo;
import com.dot.onboard.presist.repos.movieSchedule.MovieScheduleSpecification;
import java.util.Date;
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
public class MovieScheduleRepoTest {

    @Autowired
    private MovieScheduleRepo movieScheduleRepo;

    @Autowired
    private StudioRepo studioRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private MovieScheduleSpecification movieSchduleSpec;

    @BeforeAll
    public void setUp() {
        long id = 1;
        var movie = new Movie();
        movie.setOverview("this is good movie");
        movie.setPoster("path");
        movie.setTitle("Azab Indosiar");
        movie.setId(id);
        var savedMovie = movieRepo.save(movie);

        var studio = new Studio();
        studio.setSeatCapacity(20);
        studio.setStudioNumber(1);
        var savedStudio = studioRepo.save(studio);

        var movieSchedule = new MovieSchedule();
        movieSchedule.setDate(new Date());
        movieSchedule.setPrice(1000.0);
        movieSchedule.setEndTime("15:00");
        movieSchedule.setStartTime("13:00");
        movieSchedule.setMovie(savedMovie);
        movieSchedule.setStudio(savedStudio);

        movieScheduleRepo.save(movieSchedule);

    }

    @AfterAll
    public void clear() {
        movieScheduleRepo.deleteAll();
        movieRepo.deleteAll();
        studioRepo.deleteAll();
    }

    @Test
    public void findAll_movieScheduleSpecExist_listMovieSchedule() {
        // given
        var movieSchParams = new MovieScheduleSearchParams();
        movieSchParams.setKeyword("azab");
        var movieSchSpecification = movieSchduleSpec.filter(movieSchParams);

        // then
        var moviesSch = movieScheduleRepo.findAll(movieSchSpecification, PageRequest.of(0, Config.ITEMS_PER_PAGE));

        // result
        Assertions.assertFalse(moviesSch.isEmpty());
    }

}
