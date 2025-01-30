package com.example.demo1.helpers.impls;

import com.example.demo1.helpers.FileHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileHelperImpl implements FileHelper {


    @Override
    public String uploadFile(String folder, String fileName, byte[] fileContent) {
        try {
            var folderPath = Paths.get(folder);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            String newName = UUID.randomUUID() + "_" + fileName;
            var path = Paths.get(folder, newName);
            Files.write(path, fileContent);
            return newName;
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void saveFile(String uploadDir, String fileName, byte[] fileData) throws IOException {

    }

    @Override
    public byte[] readFile(String filePath) throws IOException {
        return new byte[0];
    }

    @Override
    public void deleteFile(String filePath) throws IOException {

    }

    @Override
    public boolean fileExists(String filePath) {
        return false;
    }
}
