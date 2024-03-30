package com.mycompany.crimsonproject.sort;

import java.awt.Rectangle;
import java.util.Comparator;

/**
 *
 * @author Devmachine
 */
public class RectComparatorByX implements Comparator<Rectangle> {

    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        return Integer.compare(o1.x, o2.x);
    }
}
