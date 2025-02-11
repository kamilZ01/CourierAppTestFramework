package org.automation.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void createDirectoryIfNotExist(String filePath) {
        Path path = Paths.get(filePath).getParent();

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Creating directories failed", e);
            }
        }
    }

    public static <T> void saveDataToFile(T object, String filePath) {
        createDirectoryIfNotExist(filePath);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), object);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error saving data to file [%s].", filePath), e);
        }
    }
}
