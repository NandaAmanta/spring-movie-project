/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.response.v1.user;

import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.models.user.UserRole;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class UserDetail {

    private String name;
    private String email;
    private Enum role;
    private String avatar;

    public static UserDetail fromEntity(User user) {
        var dto = new UserDetail();
        dto.setAvatar(user.getAvatar());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRole(user.getIsAdmin() ? UserRole.ADMIN : UserRole.USER);
        return dto;
    }
}
