package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;

/**
 *
 * @author DevMachine
 */
public class ClickScreenEvents extends RobotEvent {

    public void leftClickCenterButton(Rectangle rect) throws AWTException, InterruptedException {

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);

        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(this.leftClick);
        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);

    }

    public void rightClickCenterButton(Rectangle rect) throws AWTException, InterruptedException {

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(rightClick);
        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(rightClick);

    }

    public void doubleClick(Rectangle rect) throws AWTException, InterruptedException {

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);

        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(leftClick);
        this.bot.mouseRelease(leftClick);

        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(leftClick);
        this.bot.mouseRelease(leftClick);

    }

    public void holdCtrlAndLeftClick(Rectangle rect) throws AWTException, InterruptedException {

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);

        Thread.sleep(SLEEP_MS);
        this.bot.keyPress(this.ctrl);

        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(this.leftClick);

        this.bot.keyRelease(this.ctrl);
        this.bot.mouseRelease(this.leftClick);

    }

    /* Actioned when the I.A doesn't found the case and needs to be returned. */
    public void returnCaseLeftClick() throws InterruptedException, AWTException {

        this.bot.mouseMove(1230, 720);
        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(this.leftClick);
        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);
    }

    public void leftClick(int x, int y) throws InterruptedException, AWTException {

        this.bot.mouseMove(x, y);
        Thread.sleep(SLEEP_MS);
        this.bot.mousePress(this.leftClick);
        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);

    }
}
