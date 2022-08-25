/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.filters;

import com.dot.onboard.presist.usecases.UserUseCase;
import com.dot.onboard.utility.JwtTokenUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
@Slf4j
public class AuthenticationFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserUseCase userUseCase;

    protected void doFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = jwtTokenUtil.resolveToken(request);
        String username = null;
        if (token != null) {
            try {
                username = jwtTokenUtil.getUserNameFromToken(token);
            } catch (Exception ex) {
                log.error("Exception from filter : {}", ex.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userUseCase.loadUserByUsername(username);
            if (jwtTokenUtil.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("====>>>" ,usernamePasswordAuthenticationToken.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

    }

}
