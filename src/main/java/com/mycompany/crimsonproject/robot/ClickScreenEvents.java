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

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);

        this.sleep(SLEEP_MS);
        this.bot.mousePress(this.leftClick);
        this.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);

    }

    public void rightClickCenterButton(Rectangle rect) throws AWTException {

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        this.sleep(SLEEP_MS);
        this.bot.mousePress(rightClick);
        this.sleep(SLEEP_MS);
        this.bot.mouseRelease(rightClick);

    }

    public void doubleClick(Rectangle rect) throws AWTException {

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);

        this.sleep(SLEEP_MS);
        this.bot.mousePress(leftClick);
        this.bot.mouseRelease(leftClick);

        this.sleep(SLEEP_MS);
        this.bot.mousePress(leftClick);
        this.bot.mouseRelease(leftClick);

    }

    public void dragScreen() throws AWTException {

        this.sleep(1000);

        this.bot.mouseMove(1200, 870);
        this.sleep(SLEEP_MS);

        this.bot.mousePress(this.leftClick);
        this.bot.mouseMove(1230, 870);

        this.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);

    }

    public void dragItemsToInventory(Quartet<Integer, Integer, Integer, Integer> dragItensDeadZone, Rectangle rect) throws AWTException {

        this.bot.mouseMove(dragItensDeadZone.getValue1(), dragItensDeadZone.getValue3()); // ok
        this.sleep(SLEEP_MS3); // ok

        this.bot.mousePress(this.leftClick); // ok
        this.bot.mouseMove(dragItensDeadZone.getValue0(), dragItensDeadZone.getValue2()); // ok

        this.sleep(SLEEP_MS3); //ok
        this.bot.mouseRelease(this.leftClick); //ok

        this.bot.mousePress(this.leftClick); // n

        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2); // ok
        this.sleep(SLEEP_MS3); //ok
        this.bot.mouseRelease(this.leftClick); // ok

    }
}
