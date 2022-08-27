/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.studio.StudioCreationDto;
import com.dot.onboard.applications.response.v1.studio.StudioDetail;
import com.dot.onboard.presist.models.studio.Studio;
import com.dot.onboard.presist.repos.StudioRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class StudioUseCase {

    @Autowired
    private StudioRepo studioRepo;

    public Studio create(StudioCreationDto dto) {
        var studio = dto.toEntity();
        return studioRepo.save(studio);
    }

    public StudioDetail detail(Long id) {
        var studio = studioRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Studio not found"));
        return StudioDetail.fromEntity(studio);
    }

    public List<StudioDetail> getAll() {
        var studios = studioRepo.findAll();
        var studioDetails = new ArrayList<StudioDetail>();
        studios.forEach((e)->studioDetails.add(StudioDetail.fromEntity(e)));
        return studioDetails;
    }

}
