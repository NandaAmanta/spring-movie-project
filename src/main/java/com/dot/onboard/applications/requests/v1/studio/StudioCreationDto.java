/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.studio;

import com.dot.onboard.presist.models.studio.Studio;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class StudioCreationDto {

    @NotNull(message = "seatCapacity can't be empty")
    private Integer seatCapacity;

    @NotNull(message = "studioNumber can't be empty")
    private Integer studioNumber;

    public Studio toEntity() {
        var studio = new Studio();
        studio.setSeatCapacity(seatCapacity);
        studio.setStudioNumber(studioNumber);
        return studio;
    }
}
