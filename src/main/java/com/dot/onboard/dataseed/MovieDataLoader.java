/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.dataseed;

import com.dot.onboard.presist.jobs.MovieTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
public class MovieDataLoader implements CommandLineRunner{

    @Autowired
    private MovieTask movieTask;
    
    @Override
    public void run(String... args) throws Exception {
        movieTask.patchOngoingMovies();
    }
    
}
