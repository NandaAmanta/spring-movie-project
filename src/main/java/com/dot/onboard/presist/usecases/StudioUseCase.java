/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.studio.StudioCreationDto;
import com.dot.onboard.presist.models.studio.Studio;
import com.dot.onboard.presist.repos.StudioRepo;
import java.util.List;
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

    public Studio detail(Long id) {
        var studio = studioRepo.findById(id).orElseThrow();
        return studio;
    }

    public List<Studio> getAll() {
        var studios = studioRepo.findAll();
        return studios;
    }

}
