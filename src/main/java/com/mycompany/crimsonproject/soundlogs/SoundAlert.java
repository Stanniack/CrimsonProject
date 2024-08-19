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

    public void start(String pathSound) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
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
            }

        });
        t1.start();
    }

    public void start(String pathSound, int loopCount) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
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
            }

        });

        t1.start();
    }
}
