package com.mycompany.crimsonproject.IOlogs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
public class TextLogs {

    public void minPerRoute(String path, String message) {
        boolean exists = new File(path).exists();

        try {
            if (exists) {
                Files.write(Paths.get(path), message.getBytes(), StandardOpenOption.APPEND);
            } else {
                Files.write(Paths.get(path), message.getBytes(), StandardOpenOption.CREATE);
            }
        } catch (IOException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
