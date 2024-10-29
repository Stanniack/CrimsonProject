package com.mycompany.crimsonproject.interfaces;

import java.awt.AWTException;
import java.awt.Rectangle;

/**
 *
 * @author Devmachine
 */
public interface RectangleVerifier {
    boolean RectangleVerifier(Rectangle rectangle, String itemName, int chosenClick) throws AWTException, InterruptedException;
}
