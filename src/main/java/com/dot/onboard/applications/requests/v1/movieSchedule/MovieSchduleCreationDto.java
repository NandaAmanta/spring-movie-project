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

    @NotNull(message = "movieId can't be empty")
    private Long movieId;

    @NotNull(message = "studioId can't be empty")
    private Long studioId;

    @NotNull(message = "startTime can't be empty")
    private String startTime;

    @NotNull(message = "endTime can't be empty")
    private String endTime;

    @NotNull(message = "price can't be empty")
    private Double price;

    @NotNull(message = "date can't be empty")
    private String date;

}
