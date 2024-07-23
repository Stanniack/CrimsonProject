package com.mycompany.crimsonproject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Devmachine
 */
public class Main2 {

    public static void main(String[] args) throws IOException {
        boolean exists = new File("C:\\Users\\Flavio\\Desktop\\test.txt").exists();

        if (exists) {
            Path path = Paths.get("C:\\Users\\Flavio\\Desktop\\test.txt");
            Files.write(path, "the text\n".getBytes(), StandardOpenOption.APPEND);
        } else {
            Path path = Paths.get("C:\\Users\\Flavio\\Desktop\\test.txt");
            Files.write(path, "the text\n".getBytes(), StandardOpenOption.CREATE);
        }

    }
}
