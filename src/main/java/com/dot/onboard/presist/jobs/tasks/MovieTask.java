/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.jobs.tasks;

import com.dot.onboard.applications.response.v1.movie.MovieListTmdb;
import com.dot.onboard.exceptions.custom.ThirdPartyException;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.movie.Movie;
import com.dot.onboard.presist.repos.movie.MovieRepo;
import com.dot.onboard.utility.WebClient;
import io.sentry.Sentry;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
@Slf4j
public class MovieTask {

    @Autowired
    private WebClient webClient;

    @Autowired
    private MovieRepo movieRepo;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    @Async
    @CacheEvict(value = {Config.MOVIE_ALL_CACHE, Config.MOVIE_DETAIL_CACHE}, allEntries = true)
    public void patchOngoingMovies() throws ThirdPartyException {
        String url = apiUrl + "/3/movie/now_playing?api_key=" + apiKey;
        ResponseEntity<MovieListTmdb> response = webClient.get(url, MovieListTmdb.class);
        if (response.getStatusCode().isError()) {
            String errorMsg = "Failed to patch ongoing movies from TMDB, response Http status codefrom TMDB:" + response.getStatusCode();
            var exeption = new ThirdPartyException(errorMsg);
            Sentry.captureException(exeption);
            log.error(errorMsg);
            return;
        }

        if (response.getBody() == null
                || response.getBody().toListEntity() == null
                || response.getBody().toListEntity().isEmpty()) {
            String errorMsg = "Failed to patch ongoing movies from TMDB, list data movie empty from TMDB repsonse";
            var exeption = new ThirdPartyException(errorMsg);
            Sentry.captureException(exeption);
            log.error(errorMsg);
            return;
        }

        List<Movie> movies = response.getBody().toListEntity();
        movieRepo.saveAll(movies);
        log.info("pacthed ongoing movies from TMDB");
    }

}
