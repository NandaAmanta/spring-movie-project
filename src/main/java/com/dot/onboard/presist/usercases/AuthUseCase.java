/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.usercases;

import com.dot.onboard.applications.requests.v1.user.UserCreateDto;
import com.dot.onboard.applications.requests.v1.user.UserLoginDto;
import com.dot.onboard.applications.response.v1.user.UserDetailToken;
import com.dot.onboard.global.PasswordEncoder;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.repos.user.UserRepo;
import com.dot.onboard.utility.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class AuthUseCase {

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final UserDetailToken userDetailToken = new UserDetailToken();

    public UserDetailToken login(UserLoginDto dto) {
        UserDetails userDetails = userUseCase.loadUserByUsername(dto.getEmail());
        authenticate(dto.getEmail(), dto.getPassword());
        System.out.println("--------------------");
        userDetailToken.setToken(jwtTokenUtil.generateToken(userDetails));
        userDetailToken.setEmail(userDetails.getUsername());
        return userDetailToken;
    }

    public User signup(UserCreateDto dto) {
        var user = new User();
        var encodedPassword = passwordEncoder.getEncoder().encode(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setAvatar(dto.getAvatar());
        user.setName(dto.getName());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
