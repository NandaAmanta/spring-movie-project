/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.user;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class UserCreateDto {
    private String name;
    private String email;
    private String password;
    private String avatar;
}
