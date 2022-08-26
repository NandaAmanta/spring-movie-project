/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.movieSchedule.MovieSchduleCreationDto;
import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import com.dot.onboard.presist.repos.MovieRepo;
import com.dot.onboard.presist.repos.MovieScheduleRepo;
import com.dot.onboard.presist.repos.StudioRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class MovieScheduleUseCase {

    @Autowired
    private MovieScheduleRepo movieScheduleRepo;

    @Autowired
    private StudioRepo studioRepo;

    @Autowired
    private MovieRepo movieRepo;

    public List<MovieSchedule> getAll() {
        var movieSchedules = movieScheduleRepo.findAll();
        return movieSchedules;
    }

    public MovieSchedule detail(Long id) {
        var movieSch = movieScheduleRepo.findById(id).orElseThrow();
        return movieSch;
    }

    public MovieSchedule create(MovieSchduleCreationDto dto) {
        var movieSch = new MovieSchedule();
        var studio = studioRepo.findById(dto.getStudioId()).orElseThrow();
        var movie = movieRepo.findById(dto.getMovieId()).orElseThrow();

        movieSch.setDate(dto.getDate());
        movieSch.setEndTime(dto.getEndTime());
        movieSch.setStartTime(dto.getStartTime());
        movieSch.setPrice(dto.getPrice());
        movieSch.setMovie(movie);
        movieSch.setStudio(studio);

        return movieSch;
    }

}
