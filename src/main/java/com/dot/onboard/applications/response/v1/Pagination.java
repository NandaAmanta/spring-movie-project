/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.response.v1;

import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;

/**
 *
 * @author ASUS
 */
@Data
public class Pagination<T> {

    private int totalPage;
    private int page;
    private long total;
    private Object[] items;

    public Pagination(Page<T> data) {
        page = data.getNumber() + 1;
        total = data.getTotalElements();
        totalPage = data.getTotalPages();
    }

}
