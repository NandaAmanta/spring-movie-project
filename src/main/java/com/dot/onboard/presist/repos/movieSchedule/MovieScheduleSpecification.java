/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.repos.movieSchedule;

import com.dot.onboard.applications.requests.v1.movie.MovieSearchParams;
import com.dot.onboard.applications.requests.v1.movieSchedule.MovieScheduleSearchParams;
import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.models.order.Order;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Slf4j
@Component
public class MovieScheduleSpecification {

    private final String COLUMN_DATE = "date";
    private final String COLUMN_MOVIE_TITLE = "title";

    public Specification<MovieSchedule> filter(MovieScheduleSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return ((root, query, cb) -> {

            if (params.getKeyword() != null) {
                Join<Movie, MovieSchedule> movie = root.join("movie");
                predicates.add(cb.like(cb.upper(movie.get(COLUMN_MOVIE_TITLE)), params.getKeyword().toUpperCase()));
            }

            if (params.getDate() != null) {
                log.info(params.getDate());
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateReq = null;
                try {
                    dateReq = formatter.parse(params.getDate());
                } catch (ParseException ex) {
                    log.error(ex.getMessage());
                }
                predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(COLUMN_DATE), dateReq));
            }
            var predicateInArr = predicates.toArray(new Predicate[0]);
            return query.where(predicateInArr).getRestriction();
        });
    }
}
