/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.user;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class UserLoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
