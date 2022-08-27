/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.movieSchedule;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class MovieSchduleCreationDto {

    @NotNull
    private Long movieId;

    @NotNull
    private Long studioId;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @NotNull
    private Double price;

    @NotNull
    private String date;

}
