package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;

/**
 *
 * @author Devmachine
 */
public class DragClickEventInInventoryStation extends RobotEvent {

    public void eventClick() throws AWTException, InterruptedException {

        this.bot.mouseMove(286, 700);
        Thread.sleep(this.SLEEP_MS);

        this.bot.mousePress(this.leftClick);
        bot.mouseMove(566, 380);

        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);

        this.bot.mouseMove(295, 380);
        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(this.leftClick);

        this.bot.mouseMove(130, 425);
        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);
    }

    public void eventClick(int x1, int x2, int y1, int y2, Rectangle rect) throws AWTException, InterruptedException {

        this.bot.mouseMove(x2, y2); // ok
        Thread.sleep(SLEEP_MS); // ok

        this.bot.mousePress(this.leftClick); // ok
        this.bot.mouseMove(x1, y1); // ok

        Thread.sleep(SLEEP_MS); //ok
        this.bot.mouseRelease(this.leftClick); //ok

        this.bot.mousePress(this.leftClick); // n

        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2); // ok
        Thread.sleep(SLEEP_MS); //ok
        this.bot.mouseRelease(this.leftClick); // ok

    }
}
