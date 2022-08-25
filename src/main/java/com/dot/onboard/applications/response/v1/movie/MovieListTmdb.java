/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.movie;

import com.dot.onboard.presist.models.movie.Movie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            var entity = element.toEntity();
            entity.setPlayUntil(new Date());
            movies.add(entity);
        });
        return movies;
    }
}
