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

    public static final String STUDIO_ALL_CACHE = "studioAllCache";
    public static final String ORDER_ALL_CACHE = "orderAllCache";
    public static final String ORDER_ALL_MINE_CACHE = "orderAllMineCache";
    public static final String TAG_ALL_CACHE = "tagAllCache";
    public static final String PROVINCE_ALL_CACHE = "provinceAllCache";
    
    public static final int ITEMS_PER_PAGE = 10;

    public static String getProjectDir() {
        return System.getProperty("user.dir");
    }
}
