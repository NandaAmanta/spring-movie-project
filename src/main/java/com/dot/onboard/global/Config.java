/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.global;

/**
 *
 * @author ASUS
 */
public class Config {
    
    public static final String MOVIE_ALL_CACHE = "movieAllCache";
    public static final String MOVIE_DETAIL_CACHE = "movieDetailCache";
    
    
    public static String getProjectDir(){
        return System.getProperty("user.dir");
    }
}
