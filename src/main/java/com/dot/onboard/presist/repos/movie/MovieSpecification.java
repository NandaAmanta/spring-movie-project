/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.repos.movie;

import com.dot.onboard.applications.requests.v1.movie.MovieSearchParams;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.dot.onboard.presist.models.movie.Movie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author ASUS
 */
@Component
@Slf4j
public class MovieSpecification {

    private final String COLUMN_NAME = "title";
    private final String COLUMN_PLAY_UNTIL = "playUntil";

    public Specification<Movie> filter(MovieSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return ((root, query, cb) -> {
            if (params.getKeyword() != null) {
                predicates.add(cb.like(cb.upper(root.get(COLUMN_NAME)), params.getKeyword().toUpperCase()));
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
                log.info("tess -> "  + dateReq.toString());
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(COLUMN_PLAY_UNTIL), dateReq));
            }
            var predicateInArr = predicates.toArray(new Predicate[0]);
            return query.where(predicateInArr).getRestriction();
        });
    }
}
