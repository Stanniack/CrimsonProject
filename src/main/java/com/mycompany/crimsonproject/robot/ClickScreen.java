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

    public void leftClickEvent(Rectangle rect) throws AWTException, InterruptedException {

        int leftClick = InputEvent.BUTTON1_DOWN_MASK;

        Robot bot = new Robot();
        Thread.sleep(1000);

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Thread.sleep(150);
        bot.mousePress(leftClick);
        Thread.sleep(150);
        bot.mouseRelease(leftClick);

    }

    public void rightClickEvent(Rectangle rect) throws AWTException, InterruptedException {

        int rightClick = InputEvent.BUTTON3_DOWN_MASK;

        Robot bot = new Robot();
        Thread.sleep(1000);

        /* (Xa = Xo + w/2) | (Ya = Yo + h/2) - Search the center of the rectangle */
        bot.mouseMove(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Thread.sleep(150);
        bot.mousePress(rightClick);
        Thread.sleep(150);
        bot.mouseRelease(rightClick);

    }
}
