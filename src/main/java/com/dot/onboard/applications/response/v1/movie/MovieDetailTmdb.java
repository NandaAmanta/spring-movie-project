/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.movie;

import com.dot.onboard.presist.models.movie.Movie;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author ASUS
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieDetailTmdb {

    @Value("$tmdb.image.url")
    private String imageUrl;

    private Long id;
    private String title;
    private String posterPath;
    private String overview;

    public Movie toEntity(Date playUntil) {
        var movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setPoster(imageUrl + posterPath);
        movie.setOverview(overview);
        movie.setPlayUntil(playUntil);
        return movie;
    }
}
