package com.logrex.online_learning_platform.controller;

import com.logrex.online_learning_platform.service.AzureBlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@RequestMapping("/api/")
public class RestFileUpload {

    @Autowired
    AzureBlobStorageService azureBlobStorageService;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        String filePath = "bal/" + file.getOriginalFilename();
        return azureBlobStorageService.uploadFile(filePath, file);
    }
}
