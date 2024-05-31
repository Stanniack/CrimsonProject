package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import org.javatuples.Quartet;

/**
 *
 * @author Devmachine
 */
public class ClickScreenEvents extends RobotEvent {
    
    public Rectangle returnButtonCenter (Rectangle rect) {
        return new Rectangle(rect.x + rect.width / 2, rect.y + rect.height / 2);
    }

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

    public void dragScreen() throws AWTException, InterruptedException {

        Thread.sleep(1000);

        this.bot.mouseMove(1200, 870);
        Thread.sleep(SLEEP_MS);

        this.bot.mousePress(this.leftClick);
        this.bot.mouseMove(1230, 870);

        Thread.sleep(SLEEP_MS);
        this.bot.mouseRelease(this.leftClick);

    }

    public void dragItemsToInventory(int x1, int x2, int y1, int y2, Rectangle rect) throws AWTException, InterruptedException {

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

    public void dragItemsToInventory(Quartet<Integer, Integer, Integer, Integer> dragItensDeadZone, Rectangle rect) throws AWTException, InterruptedException {

        this.bot.mouseMove(dragItensDeadZone.getValue1(), dragItensDeadZone.getValue3()); // ok
        Thread.sleep(SLEEP_MS); // ok

        this.bot.mousePress(this.leftClick); // ok
        this.bot.mouseMove(dragItensDeadZone.getValue0(), dragItensDeadZone.getValue2()); // ok

        Thread.sleep(SLEEP_MS); //ok
        this.bot.mouseRelease(this.leftClick); //ok

        this.bot.mousePress(this.leftClick); // n

        this.bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2); // ok
        Thread.sleep(SLEEP_MS); //ok
        this.bot.mouseRelease(this.leftClick); // ok

    }

}
