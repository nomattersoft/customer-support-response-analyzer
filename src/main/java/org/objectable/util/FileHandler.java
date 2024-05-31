package org.objectable.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {


    public static FileInputStream getFileAsStream(String fileName) throws IOException {
        return new FileInputStream(Paths.get(System.getProperty("user.dir"), fileName).toFile());
    }


    public static List<String> readLines(Path filePath) throws IOException {
        return Files.readAllLines(filePath);
    }
}
