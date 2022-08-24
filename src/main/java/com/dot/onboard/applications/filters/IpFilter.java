/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.filters;

import com.dot.onboard.utility.IpAddressGetter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
@Slf4j
public class IpFilter {

    public void doFilter(HttpServletRequest request, HttpServletResponse response) {
        log.info(IpAddressGetter.get(request));
    }
}
