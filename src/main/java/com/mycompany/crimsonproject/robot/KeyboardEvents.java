package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;

/**
 *
 * @author Devmachine
 */
public class KeyboardEvents extends RobotEvent {

    public void clickKey(int KeyEvent1) throws AWTException, InterruptedException {
        Thread.sleep(SLEEP_MS);
        this.bot.keyPress(KeyEvent1);
        this.bot.keyRelease(KeyEvent1);
    }

    public void pressKey(int KeyEvent1, int KeyEvent2) throws AWTException, InterruptedException {
        Thread.sleep(SLEEP_MS);
        this.bot.keyPress(KeyEvent1);
        this.bot.keyPress(KeyEvent2);
        
        Thread.sleep(SLEEP_MS);
        this.bot.keyRelease(KeyEvent1);
        this.bot.keyRelease(KeyEvent2);
        
    }

}