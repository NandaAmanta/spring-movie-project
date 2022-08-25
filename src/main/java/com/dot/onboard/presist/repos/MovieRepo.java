/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.repos;

import com.dot.onboard.presist.models.movie.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASUS
 */
public interface MovieRepo extends JpaRepository<Movie, Long>{
    List<Movie> findAll();
}
