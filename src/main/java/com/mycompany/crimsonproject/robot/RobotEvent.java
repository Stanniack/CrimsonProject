package com.mycompany.crimsonproject.robot;

import com.mycompany.crimsonproject.interfaces.Sleeper;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
abstract class RobotEvent implements Sleeper {

    protected static final int SLEEP_MS = 100;
    protected static final int SLEEP_MS2 = 150;
    protected static final int SLEEP_MS3 = 350;

    protected final int leftClick = InputEvent.BUTTON1_DOWN_MASK;
    protected final int rightClick = InputEvent.BUTTON3_DOWN_MASK;
    protected final int ctrl = KeyEvent.VK_CONTROL;

    protected Robot bot = null;

    RobotEvent() {
        try {
            this.bot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(RobotEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(RobotEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
