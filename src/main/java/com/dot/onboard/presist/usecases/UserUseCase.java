/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.requests.v1.UploadImageDto;
import com.dot.onboard.applications.requests.v1.user.UserUpdateDto;
import com.dot.onboard.applications.response.v1.user.UserDetail;
import com.dot.onboard.exceptions.custom.UserNotFound;
import com.dot.onboard.global.Config;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.models.user.UserRole;
import com.dot.onboard.presist.repos.UserRepo;
import com.dot.onboard.utility.ImageStorageUtil;
import com.dot.onboard.utility.JwtTokenUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class UserUseCase implements UserDetailsService {

    @Value("${image.folder.avatar}")
    private String imageFolder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    ImageStorageUtil imageStorageUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow();
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .roles(this.getRole(user).toString())
                .password(user.getPassword()).build();
        return userDetails;
    }

    public UserDetail detailByHttpServletRequest(HttpServletRequest req) {
        String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));
        return UserDetail.fromEntity(user);
    }

    public UserDetail update(UserUpdateDto dto, HttpServletRequest req) throws IOException {
        String token = jwtTokenUtil.resolveToken(req);
        String email = jwtTokenUtil.getUserNameFromToken(token);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found!"));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            user.setName(dto.getName());
        }

        if (dto.getFile() != null) {
            String uri = imageStorageUtil.store(dto.getFile(), imageFolder, "avatar");
            user.setAvatar(uri);
        }

        return UserDetail.fromEntity(userRepo.save(user));
    }

    public List<UserDetail> getAll() {
        var users = userRepo.findAll();
        List<UserDetail> userDetails = new ArrayList<>();
        users.forEach((element) -> userDetails.add(UserDetail.fromEntity(element)));
        return userDetails;

    }

    private UserRole getRole(User user) {
        return user.getIsAdmin() ? UserRole.ADMIN : UserRole.USER;
    }

}
