package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;

/**
 *
 * @author Devmachine
 */
public class KeyboardEvents extends RobotEvent {

    public void clickKey(int keyEvent1) throws AWTException {
        this.sleep(SLEEP_MS);
        this.bot.keyPress(keyEvent1);
        this.bot.keyRelease(keyEvent1);
    }

    public void pressKey(int keyEvent1, int keyEvent2) throws AWTException {
        this.sleep(SLEEP_MS);
        this.bot.keyPress(keyEvent1);
        this.bot.keyPress(keyEvent2);

        this.sleep(SLEEP_MS);
        this.bot.keyRelease(keyEvent1);
        this.bot.keyRelease(keyEvent2);

    }

    public void pressKey(int keyEvent1, int keyEvent2, int keyEvent3) throws AWTException {
        this.sleep(SLEEP_MS);
        this.bot.keyPress(keyEvent1);
        this.bot.keyPress(keyEvent2);
        this.bot.keyPress(keyEvent3);

        this.sleep(SLEEP_MS);
        this.bot.keyRelease(keyEvent1);
        this.bot.keyRelease(keyEvent2);
        this.bot.keyRelease(keyEvent3);

    }
}
