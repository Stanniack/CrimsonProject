package com.mycompany.crimsonproject.interfaces;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public interface VerifyRectangleColor {
    boolean verifyRectangleColor(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> minRGB, Triplet<Integer, Integer, Integer> maxRGB) throws AWTException, InterruptedException, IOException;
}
