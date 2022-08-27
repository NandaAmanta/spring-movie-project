/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.user;

import com.dot.onboard.applications.requests.v1.movie.MovieSearchParams;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
abstract public class Params {

    private String keyword;
    private String date;
    private Integer page = 0;

    public String getKeyword() {
        return keyword != null ? "%" + keyword + "%" : "%%";
    }

    public Integer getPage() {
        return page == 0 ? 0 : page - 1;
    }

    
}
