/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Data
public class UserUpdateDto {

    private String name;
    private MultipartFile file;

}
