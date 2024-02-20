package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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
        Thread.sleep(300);
        bot.mousePress(leftClick);
        Thread.sleep(300);
        bot.mouseRelease(leftClick);

    }

    public void rightClickCenterButton(Rectangle rect) throws AWTException, InterruptedException {

        int rightClick = InputEvent.BUTTON3_DOWN_MASK;

        Robot bot = new Robot();

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Thread.sleep(300);
        bot.mousePress(rightClick);
        Thread.sleep(300);
        bot.mouseRelease(rightClick);

    }

    public void doubleClick(Rectangle rect) throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);

        Thread.sleep(100);
        bot.mousePress(leftClick);
        bot.mouseRelease(leftClick);

        Thread.sleep(100);
        bot.mousePress(leftClick);
        bot.mouseRelease(leftClick);

    }

    public void holdCtrlAndLeftClick(Rectangle rect) throws AWTException, InterruptedException {

        int ctrl = KeyEvent.VK_CONTROL;
        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);

        Thread.sleep(100);
        bot.keyPress(ctrl);

        Thread.sleep(100);
        bot.mousePress(leftClick);

        bot.keyRelease(ctrl);
        bot.mouseRelease(leftClick);

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
