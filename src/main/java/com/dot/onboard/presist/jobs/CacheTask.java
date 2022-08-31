/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.jobs;

import com.dot.onboard.global.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS ROG
 */
@Component
@Slf4j
public class CacheTask {

    @Async
    @CacheEvict(value = Config.PROVINCE_ALL_CACHE, allEntries = true)
    public void evictProvinces() {
        log.info("evict provinces cache");
    }
}
