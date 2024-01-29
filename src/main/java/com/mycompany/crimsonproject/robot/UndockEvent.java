package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author Stanniack
 */
public class UndockEvent {
    
    public void eventClick () throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;
        
        Robot bot = new Robot();
        
        bot.mouseMove(1780, 290);
        Thread.sleep(150);
        
        bot.mousePress(leftClick);
        bot.mouseRelease(leftClick);
    }
    
}
