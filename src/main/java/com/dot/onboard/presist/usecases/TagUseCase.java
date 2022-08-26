/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.tag.TagCreationDto;
import com.dot.onboard.presist.models.tag.Tag;
import com.dot.onboard.presist.repos.TagRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class TagUseCase {

    @Autowired
    private TagRepo tagRepo;

    public List<Tag> getAll() {
        var tags = tagRepo.findAll();
        return tags;
    }

    public Tag create(TagCreationDto dto) {
        var tag = new Tag();
        tag.setName(dto.getName());
        return tagRepo.save(tag);
    }

}
