package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import org.javatuples.Quartet;

/**
 *
 * @author Devmachine
 */
public class ClickScreenEvents extends RobotEvent {

    public Rectangle returnButtonCenter(Rectangle rect) {
        return new Rectangle(rect.x + rect.width / 2, rect.y + rect.height / 2);
    }

    public void leftClickCenterButton(Rectangle rect) throws AWTException {
        // Move the mouse to the center of the rectangle and perform a left click
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2); //(Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle 
        sleep(SLEEP_MS);
        bot.mousePress(leftClick);
        sleep(SLEEP_MS);
        bot.mouseRelease(leftClick);
    }

    public void rightClickCenterButton(Rectangle rect) throws AWTException {
        // Move the mouse to the center of the rectangle and perform a right click
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        sleep(SLEEP_MS);
        bot.mousePress(rightClick);
        sleep(SLEEP_MS);
        bot.mouseRelease(rightClick);
    }

    public void doubleClick(Rectangle rect) throws AWTException {
        // Move the mouse to the center of the rectangle and perform a double click
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        sleep(SLEEP_MS);

        bot.mousePress(leftClick);
        bot.mouseRelease(leftClick);

        sleep(SLEEP_MS);

        bot.mousePress(leftClick);
        bot.mouseRelease(leftClick);
    }

    public void dragScreen() throws AWTException {
        sleep(1000);

        // Move the mouse to a specified position and drag the screen
        bot.mouseMove(1200, 870);
        sleep(SLEEP_MS);

        bot.mousePress(leftClick);
        bot.mouseMove(1230, 870);

        sleep(SLEEP_MS);
        bot.mouseRelease(leftClick);
    }

    public void dragItemsToInventory(Quartet<Integer, Integer, Integer, Integer> dragItemsDeadZone, Rectangle rect) throws AWTException {
        // Move the mouse to the dead zone and drag items to the inventory
        bot.mouseMove(dragItemsDeadZone.getValue1(), dragItemsDeadZone.getValue3());
        sleep(SLEEP_MS3);

        bot.mousePress(leftClick);
        bot.mouseMove(dragItemsDeadZone.getValue0(), dragItemsDeadZone.getValue2());
        sleep(SLEEP_MS3);
        bot.mouseRelease(leftClick);

        // Press the left click to drag the item to the inventory
        bot.mousePress(leftClick);
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        sleep(SLEEP_MS3);
        bot.mouseRelease(leftClick);
    }
}
