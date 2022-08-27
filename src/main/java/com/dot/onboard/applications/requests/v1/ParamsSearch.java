/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.requests.v1;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
abstract public class ParamsSearch extends Params{

    private String keyword;

    public String getKeyword() {
        return keyword != null ? "%" + keyword + "%" : "%%";
    }
}
