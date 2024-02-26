package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;

/**
 *
 * @author DevMachine
 */
public class UndockEvent extends RobotEvent {

    public void eventClick() throws AWTException, InterruptedException {

        this.bot.mouseMove(1780, 290);
        Thread.sleep(150);

        this.bot.mousePress(this.leftClick);
        this.bot.mouseRelease(this.leftClick);
    }

}
