/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.sort;

import java.awt.Rectangle;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Devmachine
 */
public class HashMapRectComparatorByY implements Comparator<Entry<Integer, Rectangle>> {

    @Override
    public int compare(Entry<Integer, Rectangle> o1, Entry<Integer, Rectangle> o2) {
        return Integer.compare(o1.getValue().y, o2.getValue().y);
    }



}
