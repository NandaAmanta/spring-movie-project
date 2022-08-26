/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.studio;

import com.dot.onboard.presist.models.studio.Studio;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class StudioCreationDto {
    private Integer seatCapacity;
    private Integer studioNumber;
    
    public Studio toEntity(){
        var studio = new Studio();
        studio.setSeatCapacity(seatCapacity);
        studio.setStudioNumber(studioNumber);
        return studio;
    }
}
