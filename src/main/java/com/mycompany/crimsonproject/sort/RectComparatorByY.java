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
public class RectComparatorByY implements Comparator<Rectangle> {


    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        return Integer.compare(o1.y, o2.y);
    }
    
    
    
}

