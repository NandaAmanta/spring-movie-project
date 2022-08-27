/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.movieSchedule.MovieSchduleCreationDto;
import com.dot.onboard.applications.requests.v1.movieSchedule.MovieScheduleSearchParams;
import com.dot.onboard.applications.response.v1.Pagination;
import com.dot.onboard.applications.response.v1.movieSchedule.MovieScheduleDetail;
import com.dot.onboard.exceptions.custom.CustomDataNotFoundException;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import com.dot.onboard.presist.repos.movie.MovieRepo;
import com.dot.onboard.presist.repos.movieSchedule.MovieScheduleRepo;
import com.dot.onboard.presist.repos.StudioRepo;
import com.dot.onboard.presist.repos.movieSchedule.MovieScheduleSpecification;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
@Slf4j
public class MovieScheduleUseCase {

    @Autowired
    private MovieScheduleRepo movieScheduleRepo;

    @Autowired
    private StudioRepo studioRepo;

    @Autowired
    private MovieScheduleSpecification movieScheduleSpec;

    @Autowired
    private MovieRepo movieRepo;

    public Pagination<MovieScheduleDetail> getAll(MovieScheduleSearchParams params) {
        var movieSchedules = movieScheduleRepo.findAll(movieScheduleSpec.filter(params), PageRequest.of(params.getPage(), Config.ITEMS_PER_PAGE));
        var movieSchedulesDetails = new ArrayList<MovieScheduleDetail>();
        movieSchedules.forEach((e) -> movieSchedulesDetails.add(MovieScheduleDetail.fromEntity(e)));

        var data = new Pagination(movieSchedules);
        data.setItems(movieSchedulesDetails.toArray());
        return data;
    }

    public MovieScheduleDetail detail(Long id) {
        var movieSch = movieScheduleRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Movie Schedule not found"));
        return MovieScheduleDetail.fromEntity(movieSch);
    }

    public MovieSchedule create(MovieSchduleCreationDto dto) {
        var movieSch = new MovieSchedule();
        List<String> errMsgArray = new ArrayList<>();

        var studio = studioRepo.findById(dto.getStudioId()).orElseGet(() -> {
            errMsgArray.add("studio not found");
            return null;
        });

        var movie = movieRepo.findById(dto.getMovieId()).orElseGet(() -> {
            errMsgArray.add("movie not found");
            return null;
        });

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;

        try {
            date = formatter.parse(dto.getDate());
        } catch (ParseException ex) {
            errMsgArray.add("Invalid Date Pattern,correct example : 2022-12-01");
            log.error("ParseException happend when parse date create movie schdule");
        }

        if (!errMsgArray.isEmpty()) {
            throw new CustomDataNotFoundException("Failed to create new Schedule", errMsgArray);
        }

        movieSch.setDate(date);
        movieSch.setEndTime(dto.getEndTime());
        movieSch.setStartTime(dto.getStartTime());
        movieSch.setPrice(dto.getPrice());
        movieSch.setMovie(movie);
        movieSch.setStudio(studio);

        return movieScheduleRepo.save(movieSch);

    }

}
