/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.requests.v1;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
abstract public class Params {

    private String date;
    private Integer page = 0;


    public Integer getPage() {
        return page == 0 ? 0 : page - 1;
    }

    
}
