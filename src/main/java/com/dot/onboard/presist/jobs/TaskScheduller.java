/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.jobs;

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

    @Scheduled(cron = "0 0 * * * *")
    public void syncOngoingMovieSchedulle() {
        log.info("patching ongoing movie data...");
        movieTask.patchOngoingMovies();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void clearCaches() {
        cacheTask.evictProvinces();
    }

}
