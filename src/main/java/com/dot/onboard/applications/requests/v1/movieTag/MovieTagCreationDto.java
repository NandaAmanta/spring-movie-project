/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.movieTag;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class MovieTagCreationDto {

    @NotNull(message = "movieId can't be empty")
    private Long movieId;

    @NotNull(message = "tagId can't be empty")
    private Long tagId;

}
