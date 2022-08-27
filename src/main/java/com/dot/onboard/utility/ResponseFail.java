/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.utility;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
public class ResponseFail implements Response {

    private boolean success = false;
    private String message;
    private String errorCode;
    private Object errors = new ArrayList<>();
}
