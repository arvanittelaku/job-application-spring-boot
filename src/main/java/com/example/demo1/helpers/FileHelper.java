package com.example.demo1.helpers;

import java.io.IOException;

public interface FileHelper {
    String uploadFile(String folder, String fileName, byte[] fileContent);
    void saveFile(String uploadDir, String fileName, byte[] fileData) throws IOException;
    byte[] readFile(String filePath) throws IOException;
    void deleteFile(String filePath) throws IOException;
    boolean fileExists(String filePath);
}
