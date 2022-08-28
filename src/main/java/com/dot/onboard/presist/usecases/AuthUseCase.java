/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.user.UserCreateDto;
import com.dot.onboard.applications.requests.v1.user.UserLoginDto;
import com.dot.onboard.applications.response.v1.user.UserDetail;
import com.dot.onboard.applications.response.v1.user.UserDetailToken;
import com.dot.onboard.exceptions.custom.EmailAlreadyInUsedException;
import com.dot.onboard.kernel.configs.PasswordEncoder;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.repos.UserRepo;
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
        authenticate(dto.getEmail(), dto.getPassword());
        UserDetails userDetails = userUseCase.loadUserByUsername(dto.getEmail());
        userDetailToken.setToken(jwtTokenUtil.generateToken(userDetails));
        userDetailToken.setEmail(userDetails.getUsername());
        return userDetailToken;
    }

    public UserDetail signup(UserCreateDto dto) {
        boolean isEmailExist = userRepo.findByEmail(dto.getEmail()).isPresent();
        if (isEmailExist) {
            throw new EmailAlreadyInUsedException("this email is already in used");
        }
        var user = new User();
        var encodedPassword = passwordEncoder.getEncoder().encode(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(encodedPassword);
        return UserDetail.fromEntity(userRepo.save(user));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
