/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.usecases;

import com.dot.onboard.applications.response.v1.rajaongkir.City;
import com.dot.onboard.applications.response.v1.rajaongkir.Province;
import com.dot.onboard.applications.response.v1.rajaongkir.RajaOngkirResponse;
import com.dot.onboard.exceptions.custom.ThirdPartyException;
import com.dot.onboard.global.Config;
import com.dot.onboard.utility.WebClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS ROG
 */
@Service
@Slf4j
public class CityUseCase {

    @Autowired
    private WebClient webClient;

    @Value("${rajaongkir.api.key}")
    private String key;

    @Value("${rajaongkir.api.url}")
    private String url;

    @Cacheable(Config.CITY_ALL_CACHE)
    public List<City> getAll() {
        try {
            String urlReq = url + "/city?key=" + key;
            var response = webClient.get(urlReq, RajaOngkirResponse.class);
            var data = response.getBody().getRajaongkir().getResults();
            return (List<City>) data;
        } catch (Exception ex) {
            log.error("error message : ", ex.getMessage());
            throw new ThirdPartyException(ex.getMessage());
        }
    }

}
