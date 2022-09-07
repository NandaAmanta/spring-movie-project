/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.jobs;

import com.dot.onboard.exceptions.custom.ThirdPartyException;
import com.dot.onboard.presist.jobs.tasks.MovieTask;
import com.dot.onboard.presist.jobs.tasks.CacheTask;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
@Slf4j
public class TaskScheduller {

    @Autowired
    private MovieTask movieTask;

    @Autowired
    private CacheTask cacheTask;

    @Scheduled(cron = "${app.data.movie.patch.cron}")
    public void loadOngoingMovieSchedulle() {
         movieTask.patchOngoingMovies();
    }

    @Scheduled(cron = "${app.cache.evict.cron}")
    public void clearCaches() {
        log.info("evict all caches...");
        cacheTask.evictAllCaches();
    }

}
