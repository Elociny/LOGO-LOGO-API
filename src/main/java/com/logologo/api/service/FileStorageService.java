package com.logologo.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadPath = Paths.get("uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar diretório de upload.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = this.uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}