package com.mycompany.crimsonproject.interfaces;

import java.awt.AWTException;
import java.awt.Rectangle;

/**
 *
 * @author Devmachine
 */
public interface VerifyRectangle {

    boolean VerifyRectangle(Rectangle rectangle, String itemName, int chosenClick) throws AWTException, InterruptedException;

}
