package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author Stanniack
 */
public class DragClickEventInInventoryStation {

    public void eventClick() throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        bot.mouseMove(286, 700);
        Thread.sleep(150);

        bot.mousePress(leftClick);
        bot.mouseMove(566, 380);

        Thread.sleep(150);
        bot.mouseRelease(leftClick);

        bot.mouseMove(295, 380);
        Thread.sleep(150);
        bot.mousePress(leftClick);

        bot.mouseMove(130, 425);
        Thread.sleep(150);
        bot.mouseRelease(leftClick);
    }

    public void eventClick(int x1, int x2, int y1, int y2, Rectangle rect) throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        bot.mouseMove(x2, y2); // ok
        Thread.sleep(150); // ok

        bot.mousePress(leftClick); // ok
        bot.mouseMove(x1, y1); // ok

        Thread.sleep(150); //ok
        bot.mouseRelease(leftClick); //ok
        
        bot.mousePress(leftClick); // n
        
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2); // ok
        Thread.sleep(150); //ok
        bot.mouseRelease(leftClick); // ok

    }
}
