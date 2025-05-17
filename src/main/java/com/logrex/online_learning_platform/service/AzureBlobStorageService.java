package com.logrex.online_learning_platform.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;

@Service
public class AzureBlobStorageService {
    private final BlobContainerClient containerClient;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public AzureBlobStorageService(BlobContainerClient containerClient) {
        this.containerClient = containerClient;
    }

    public String uploadFile(String path, MultipartFile multipartFile) {
        try {
            BlobClient blob = containerClient.getBlobClient(path);
            blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);
            return blob.getBlobUrl();
        } catch (IOException e) {
            log.error("Failed to upload to Azure at path {}", path, e);
            throw new UncheckedIOException("Azure upload failed", e);
        }
    }
}

