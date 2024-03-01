package com.mycompany.crimsonproject.sort;

import java.awt.Rectangle;
import java.util.Comparator;

/**
 *
 * @author Devmachine
 */
public class RectComparatorByX_Reverse implements Comparator<Rectangle> {


    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        if (o1.x > o2.x)
            return -1;
       
        return 0;
    }
    
    
    
}
