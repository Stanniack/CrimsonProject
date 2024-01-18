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

/* Remove the magical numbers here */
public class DragScreen {

    public void eventClick() throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();

        Thread.sleep(2000);
        
        bot.mouseMove(1200, 870);
        Thread.sleep(150);

        bot.mousePress(leftClick);
        bot.mouseMove(1230, 870);

        Thread.sleep(250);
        bot.mouseRelease(leftClick);

    }
}
