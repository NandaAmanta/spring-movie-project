/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.movie;

import com.dot.onboard.presist.models.movie.Movie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class MovieListTmdb {

    private MovieDateTmdb dates;
    private List<MovieDetailTmdb> results;

    public List<Movie> toListEntity() {
        List<Movie> movies = new ArrayList<>();
        results.forEach((MovieDetailTmdb element) -> {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date playUntil = null;
            try {
                playUntil = formatter.parse(dates.getMaximum());
            } catch (ParseException ex) {
                Logger.getLogger(MovieDetailTmdb.class.getName()).log(Level.SEVERE, null, ex);
            }
            var entity = element.toEntity(playUntil);
            entity.setPlayUntil(new Date());
            movies.add(entity);
        });
        return movies;
    }
}
