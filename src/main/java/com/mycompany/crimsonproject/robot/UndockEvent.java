/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author Mateus
 */
public class UndockEvent {
    
    public void eventClick () throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;
        int rightClick = InputEvent.BUTTON3_DOWN_MASK;
        
        Robot bot = new Robot();
        
        bot.mouseMove(1780, 290);
        Thread.sleep(150);
        
        bot.mousePress(leftClick);
        bot.mouseRelease(leftClick);
    }
    
}
