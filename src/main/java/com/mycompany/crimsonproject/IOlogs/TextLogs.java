package com.mycompany.crimsonproject.IOlogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    /**
     * Appends a message to an existing file or creates a new file with the
     * message.
     *
     * @param path the full file path where the message will be saved
     * @param message the content to append or write to the file
     */
    public void timePerRoute(String path, String message) {
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

    /**
     * Reads and returns an integer value from the first line of a file. This
     * method is used to retrieve the current asteroid belt to travel.
     *
     * @param path the full file path to read from
     * @return the integer value read from the file
     */
    public int readLine(String path) {
        int content = 0;

        try {
            FileReader file = new FileReader(new File(path));
            BufferedReader bf = new BufferedReader(file);

            content = Integer.parseInt(bf.readLine());
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    /**
     * Writes an integer value to a file, overwriting its content if it exists.
     * This method is used to set the current asteroid belt to travel.
     *
     * @param path the full file path where the number will be written
     * @param num the integer value to write to the file
     */
    public void writeLine(String path, int num) {
        String message = String.valueOf(num);

        try {
            Files.write(Paths.get(path), message.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a log message that can be used for logging errors or
     * informational messages.
     *
     * @param path the full file path where the log message will be written
     * @param message the log message to save
     */
    public void createLogMessage(String path, String message) {
        try {
            Files.write(Paths.get(path), message.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
