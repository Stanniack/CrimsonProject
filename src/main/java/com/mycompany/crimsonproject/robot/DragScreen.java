package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;

/**
 *
 * @author Devmachine
 */

/* Remove the magical numbers here */
public class DragScreen extends RobotEvent {

    public void eventClick() throws AWTException, InterruptedException {

        Thread.sleep(1000);

        this.bot.mouseMove(1200, 870);
        Thread.sleep(SLEEP_MS);

        this.bot.mousePress(this.leftClick);
        this.bot.mouseMove(1230, 870);

        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);

    }
}
