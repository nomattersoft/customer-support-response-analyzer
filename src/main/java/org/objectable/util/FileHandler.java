package org.objectable.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class FileHandler {

    public static FileInputStream getFileInputStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(Objects.requireNonNull(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResource(fileName))
                .getPath());
    }
}
