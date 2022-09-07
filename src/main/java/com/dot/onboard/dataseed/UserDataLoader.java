/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.dataseed;

import com.dot.onboard.presist.jobs.tasks.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
public class UserDataLoader implements CommandLineRunner{
    
    @Autowired
    private UserTask userTask;
    
    @Override
    public void run(String... args) throws Exception {
        userTask.seedUserAdmin();
    }
    
}
