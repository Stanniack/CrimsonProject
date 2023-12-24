/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author Mateus
 */
public class ClickScreen {

    public void eventClick(Rectangle rect) throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;
        int rightClick = InputEvent.BUTTON3_DOWN_MASK;

        //Thread.sleep(4000);
        Robot bot = new Robot();
        Thread.sleep(1000);

        bot.mouseMove(rect.x, rect.y);
        Thread.sleep(150);
        bot.mousePress(leftClick);
        Thread.sleep(150);
        bot.mouseRelease(leftClick);

    }
}
