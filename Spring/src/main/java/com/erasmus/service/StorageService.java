package com.erasmus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {
    @Value("${location.image-folder}")
    String imageFolderLocation;

    public void storePicture(MultipartFile picture, String filename) throws IOException {
        createFolderIfNeeded(Paths.get("resources"));

        Path imageFolder = Paths.get(imageFolderLocation);
        createFolderIfNeeded(imageFolder);

        Files.copy(picture.getInputStream(), imageFolder.resolve(filename));
    }

    private void createFolderIfNeeded(Path folder) throws IOException {
        if (!Files.exists(folder)) {
            Files.createDirectory(folder);
        }
    }
}
