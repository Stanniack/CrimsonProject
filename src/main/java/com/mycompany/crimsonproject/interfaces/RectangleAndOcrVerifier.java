package com.mycompany.crimsonproject.interfaces;

import java.awt.AWTException;
import java.awt.Rectangle;
import org.javatuples.Pair;

/**
 *
 * @author Devmachine
 */
public interface RectangleAndOcrVerifier {

    boolean rectangleAndOcrVerifier(Pair<Rectangle, String> pair, String ocrMatch, String itemName, int chosenClick) throws AWTException, InterruptedException;
}
