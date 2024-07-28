package com.mycompany.crimsonproject.utils;

import javax.sound.sampled.AudioInputStream;

/**
 *
 * @author Devmachine
 */
public class MathTools {

    public float getSoundFileTimeInSec(AudioInputStream sound) {
        return sound.getFrameLength() / sound.getFormat().getFrameRate();
    }
}
