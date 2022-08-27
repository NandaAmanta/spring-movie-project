/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1.studio;

import com.dot.onboard.presist.models.studio.Studio;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StudioDetail {

    private Long id;
    private Integer studioNumber;
    private Integer seatCapacity;

    public static StudioDetail fromEntity(Studio entity) {
        var studioDetail = new StudioDetail();
        studioDetail.setId(entity.getId());
        studioDetail.setSeatCapacity(entity.getSeatCapacity());
        studioDetail.setStudioNumber(entity.getStudioNumber());
        return studioDetail;
    }
}
