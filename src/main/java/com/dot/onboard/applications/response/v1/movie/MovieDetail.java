/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.movie;

import com.dot.onboard.presist.models.movie.Movie;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class MovieDetail {

    private Long id;
    private String title;
    private String overview;
    private String poster;
    private Date playUntil;

    public static MovieDetail fromEntity(Movie entity) {
        var movieDetail = new MovieDetail();
        movieDetail.setId(entity.getId());
        movieDetail.setOverview(entity.getOverview());
        movieDetail.setTitle(entity.getTitle());
        movieDetail.setPoster(entity.getPoster());
        movieDetail.setPlayUntil(entity.getPlayUntil());
        return movieDetail;
    }
}
