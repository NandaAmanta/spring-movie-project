/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.applications.response.v1.user;

import com.dot.onboard.presist.models.user.User;
import com.dot.onboard.presist.models.user.UserRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDetail {

    private Long id;
    private String name;
    private String email;
    private Enum role;
    private String avatar;

    public static UserDetail fromEntity(User user) {
        var dto = new UserDetail();
        dto.setId(user.getId());
        dto.setAvatar(user.getAvatar());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRole(user.getIsAdmin() ? UserRole.ADMIN : UserRole.USER);
        return dto;
    }
}
