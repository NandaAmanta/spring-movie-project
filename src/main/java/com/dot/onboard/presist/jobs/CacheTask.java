/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.jobs;

import com.dot.onboard.global.Config;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.identity.Chache71IdentityColumnSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS ROG
 */
@Component
@Slf4j
@EnableCaching
public class CacheTask {

    @Autowired
    private CacheManager cacheManager;

    @Async
    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Async
    @CacheEvict(value = Config.PROVINCE_ALL_CACHE, allEntries = true)
    public void evictProvinces() {
        log.info("evict provinces cache");
    }

    @Async
    @CacheEvict(value = Config.CITY_ALL_CACHE, allEntries = true)
    public void evictCities() {
        log.info("evict provinces cache");
    }
}
