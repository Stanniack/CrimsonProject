/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
