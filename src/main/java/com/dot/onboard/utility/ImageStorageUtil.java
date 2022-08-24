/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.dot.onboard.utility;

import com.dot.onboard.global.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Component
@Slf4j
public class ImageStorageUtil {

    public String store(MultipartFile file, String imageFolder, String prefix) throws IOException {
        String folderLocation = Config.getProjectDir() + "/" + imageFolder;

        log.info(file.getOriginalFilename());
        String ext = "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = prefix + "-" + new Date().getTime() + ext;
        var path = Paths.get(folderLocation, fileName);
        Files.write(path, file.getBytes());

        return path.toString();
    }

}
