package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;

/**
 *
 * @author Devmachine
 */
public class KeyboardEvents extends RobotEvent {

    public void pressFn(int KeyEvent1) throws AWTException, InterruptedException {

        int fn = KeyEvent1;
        
        Thread.sleep(SLEEP_MS);
        this.bot.keyPress(fn);
        this.bot.keyRelease(fn);

    }
}
