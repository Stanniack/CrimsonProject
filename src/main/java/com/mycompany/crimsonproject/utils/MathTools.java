package com.mycompany.crimsonproject.utils;

import javax.sound.sampled.AudioInputStream;

/**
 *
 * @author Devmachine
 */
public class MathTools {

    /**
     * Calculates the duration of an audio file in seconds.
     *
     * @param sound the {@link AudioInputStream} representing the audio file.
     * @return the duration of the audio file in seconds.
     */
    public float getSoundFileTimeInSec(AudioInputStream sound) {
        return sound.getFrameLength() / sound.getFormat().getFrameRate();
    }
}
