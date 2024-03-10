package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;

/**
 *
 * @author Devmachine
 */
public class KeyboardEvents extends RobotEvent {

    public void pressKey(int KeyEvent1) throws AWTException, InterruptedException {

        int key = KeyEvent1;
        
        Thread.sleep(SLEEP_MS);
        this.bot.keyPress(key);
        this.bot.keyRelease(key);

    }
    
}
