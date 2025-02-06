package com.mycompany.crimsonproject.soundlogs;

import com.mycompany.crimsonproject.utils.MathTools;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Devmachine
 */
public class SoundAlert {

    /**
     * Starts a new thread to play an audio file from the specified path. The
     * audio plays to completion, and the thread is then closed.
     *
     * @param pathSound the file path of the audio to be played.
     */
    public void start(String pathSound) {

        Thread t1 = new Thread(() -> {
            try {
                File file = new File(pathSound);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());

                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(sound);
                clip.start();
                Thread.sleep((long) new MathTools().getSoundFileTimeInSec(sound) * 1000);
                clip.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                Logger.getLogger(SoundAlert.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        t1.start();
    }

    /**
     * Starts a new thread to play an audio file from the specified path,
     * looping it the specified number of times. The audio plays to completion,
     * and the thread is then closed.
     *
     * @param pathSound the file path of the audio to be played.
     * @param loopCount the number of times the audio should loop.
     */
    public void start(String pathSound, int loopCount) {

        Thread t1 = new Thread(() -> {
            try {
                File file = new File(pathSound);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());

                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(sound);
                clip.loop(loopCount);
                clip.start();
                Thread.sleep((long) new MathTools().getSoundFileTimeInSec(sound) * 1000 * loopCount);
                clip.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                Logger.getLogger(SoundAlert.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        t1.start();
    }
}
