/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.movieSchedule;

import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieScheduleDetail {

    private Long id;
    private Long studioId;

    private Long movieId;

    private String startTime;

    private String endTime;

    private Double price;

    private Date date;

    public static MovieScheduleDetail fromEntity(MovieSchedule movieSchedule) {
        var movieScheduleDetail = new MovieScheduleDetail();
        movieScheduleDetail.setId(movieSchedule.getId());
        movieScheduleDetail.setDate(movieSchedule.getDate());
        movieScheduleDetail.setPrice(movieSchedule.getPrice());
        movieScheduleDetail.setStudioId(movieSchedule.getStudio().getId());
        movieScheduleDetail.setMovieId(movieSchedule.getMovie().getId());
        movieScheduleDetail.setStartTime(movieSchedule.getStartTime());
        movieScheduleDetail.setEndTime(movieSchedule.getEndTime());
        return movieScheduleDetail;
    }
}
