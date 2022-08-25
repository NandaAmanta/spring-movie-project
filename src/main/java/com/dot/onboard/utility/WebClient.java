/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.utility;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Component
public class WebClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public <T extends Object> ResponseEntity<T> get(String url, Class<T> responseType) {
        var responseData = restTemplate.getForEntity(url, responseType);
        return responseData;
    }
}
