/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1;

import com.dot.onboard.global.Config;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.domain.Page;

/**
 *
 * @author ASUS
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pagination<T> {

    private int totalPage;
    private int page;
    private int perPage = Config.ITEMS_PER_PAGE;
    private long totalItems;
    private Object[] items;

    public Pagination(Page<T> data) {
        page = data.getNumber() + 1;
        totalItems = data.getTotalElements();
        totalPage = data.getTotalPages();
    }

}
