/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.utility;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ASUS
 */
public class IpAddressGetter {

    public static String get(HttpServletRequest request) {
        var ip = "";

        if (request != null) {
            ip = request.getHeader("X-FORWARDED-FOR");
            if (ip == null || ip.isBlank()) {
                ip = request.getRemoteAddr();
            }
        }

        return ip;
    }
}
