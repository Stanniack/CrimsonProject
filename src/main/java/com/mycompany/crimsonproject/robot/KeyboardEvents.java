package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;

/**
 *
 * @author Devmachine
 */
public class KeyboardEvents extends RobotEvent {

    public void clickKey(int keyEvent1) throws AWTException {
        sleep(SLEEP_MS);
        bot.keyPress(keyEvent1);
        bot.keyRelease(keyEvent1);
    }

    public void pressKey(int keyEvent1, int keyEvent2) throws AWTException {
        sleep(SLEEP_MS);
        bot.keyPress(keyEvent1);
        bot.keyPress(keyEvent2);

        sleep(SLEEP_MS);
        bot.keyRelease(keyEvent1);
        bot.keyRelease(keyEvent2);
    }

    public void pressKey(int keyEvent1, int keyEvent2, int keyEvent3) throws AWTException {
        sleep(SLEEP_MS);
        bot.keyPress(keyEvent1);
        bot.keyPress(keyEvent2);
        bot.keyPress(keyEvent3);

        sleep(SLEEP_MS);
        bot.keyRelease(keyEvent1);
        bot.keyRelease(keyEvent2);
        bot.keyRelease(keyEvent3);
    }
}
