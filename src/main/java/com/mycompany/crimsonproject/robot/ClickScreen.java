package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author Stanniack
 */
public class ClickScreen {

    public void leftClickCenterButton(Rectangle rect) throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Thread.sleep(150);
        bot.mousePress(leftClick);
        Thread.sleep(150);
        bot.mouseRelease(leftClick);

    }

    public void rightClickCenterButton(Rectangle rect) throws AWTException, InterruptedException {

        int rightClick = InputEvent.BUTTON3_DOWN_MASK;

        Robot bot = new Robot();

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Thread.sleep(150);
        bot.mousePress(rightClick);
        Thread.sleep(150);
        bot.mouseRelease(rightClick);

    }

    /* Actioned when the I.A doesn't found the case and needs to be returned. */
    public void returnCaseLeftClick() throws InterruptedException, AWTException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        bot.mouseMove(1230, 720);
        Thread.sleep(150);
        bot.mousePress(leftClick);
        Thread.sleep(150);
        bot.mouseRelease(leftClick);
    }

    public void leftClick(int x, int y) throws InterruptedException, AWTException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        bot.mouseMove(x, y);
        Thread.sleep(150);
        bot.mousePress(leftClick);
        Thread.sleep(150);
        bot.mouseRelease(leftClick);

    }
}
