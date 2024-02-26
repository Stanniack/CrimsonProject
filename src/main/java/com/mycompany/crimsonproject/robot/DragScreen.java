package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author Stanniack
 */

/* Remove the magical numbers here */
public class DragScreen {
    private static final int SLEEP_MS = 150; 

    public void eventClick() throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        Thread.sleep(1000);
        
        bot.mouseMove(1200, 870);
        Thread.sleep(SLEEP_MS);

        bot.mousePress(leftClick);
        bot.mouseMove(1230, 870);

        Thread.sleep(SLEEP_MS);
        bot.mouseRelease(leftClick);

    }
}
