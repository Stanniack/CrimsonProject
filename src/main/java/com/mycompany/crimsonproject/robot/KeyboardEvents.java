/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Robot;

/**
 *
 * @author Devmachine
 */
public class KeyboardEvents {

    public void pressFn(int KeyEvent1) throws AWTException, InterruptedException {

        int fn = KeyEvent1;

        Robot bot = new Robot();

        Thread.sleep(100);
        bot.keyPress(fn);
        bot.keyRelease(fn);

    }
}
