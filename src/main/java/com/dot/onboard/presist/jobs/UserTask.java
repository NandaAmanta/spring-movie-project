/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.presist.jobs;

import com.dot.onboard.global.PasswordEncoder;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
@Slf4j
public class UserTask {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Async
    public void seedUserAdmin() {
        var userAdmin = new User();
        userAdmin.setEmail("admin@gmail.com");
        userAdmin.setName("admin");
        userAdmin.setPassword(passwordEncoder.getEncoder().encode("password"));
        userAdmin.setIsAdmin(Boolean.TRUE);
        userRepo.save(userAdmin);
        log.info("Admin created");
    }

}
