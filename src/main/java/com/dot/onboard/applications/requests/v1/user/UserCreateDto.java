/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.requests.v1.user;

import com.dot.onboard.applications.validations.rules.Email;
import com.dot.onboard.applications.validations.rules.Password;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class UserCreateDto {

    @NotNull(message = "name can't be empty")
    private String name;

    @Email
    private String email;

    @Password
    private String password;

}
