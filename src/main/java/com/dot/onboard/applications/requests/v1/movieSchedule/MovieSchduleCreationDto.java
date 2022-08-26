/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.movieSchedule;

import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class MovieSchduleCreationDto {

    private Long movieId;
    private Long studioId;
    private String startTime;
    private String endTime;
    private Double price;
    private Date date;


}
