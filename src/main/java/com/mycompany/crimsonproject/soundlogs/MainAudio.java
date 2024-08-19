package com.mycompany.crimsonproject.soundlogs;

/**
 *
 * @author Devmachine
 */
public class MainAudio {

    public static void main(String[] args) {
        new SoundAlert().start(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\soundlogs\\soundfiles\\endofmining.wav", 1);
    }

}
