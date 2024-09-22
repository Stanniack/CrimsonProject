package com.mycompany.crimsonproject.IOlogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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

    public void timePerRoute(String file, String message) {
        boolean exists = new File(file).exists();

        try {
            if (exists) {
                Files.write(Paths.get(file), message.getBytes(), StandardOpenOption.APPEND);
            } else {
                Files.write(Paths.get(file), message.getBytes(), StandardOpenOption.CREATE);
            }
        } catch (IOException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void readByLine(String path) {
        try {
            FileReader file = new FileReader(new File(path));
            BufferedReader bf = new BufferedReader(file);

            while (bf.readLine() != null) {
                System.out.println(bf.readLine());
            }
            file.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int readLine(String path) {
        int content = 0;

        try {
            FileReader file = new FileReader(new File(path));
            BufferedReader bf = new BufferedReader(file);

            content = Integer.parseInt(bf.readLine());
            file.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
}
