/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.repos;

import com.dot.onboard.kernel.configs.PasswordEncoder;
import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.repos.UserRepo;
import javax.transaction.Transactional;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS ROG
 */
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String EMAIL_USER_EXIST = "Nansamgr@gmail.com";
    private final User user = new User();

    @BeforeAll
    public void setUp() {
        user.setName("I Putu Nanda Amanta");
        user.setEmail(EMAIL_USER_EXIST);
        user.setIsAdmin(Boolean.FALSE);
        user.setPassword(passwordEncoder.getEncoder().encode("password"));
        userRepo.save(user);
    }

    @AfterAll
    public void clear() {
        userRepo.deleteAll();
    }

    @Test
    public void findByEmail_emailUserExist_OptionalUser() {
        // given
        String email = EMAIL_USER_EXIST;

        // then
        var userFound = userRepo.findByEmail(email);

        // result
        Assertions.assertTrue(userFound.isPresent());
        Assertions.assertNotNull(userFound.orElse(null));

    }
    
    @Test
    public void findByEmail_emailUserNotExist_optionalUser() {
        // given
        String notExistEmailUser = "askdalskdjaskldjas";

        // then
        var userFound = userRepo.findByEmail(notExistEmailUser);

        // result
        Assertions.assertTrue(userFound.isEmpty());
        Assertions.assertNull(userFound.orElse(null));

    }
    

}
