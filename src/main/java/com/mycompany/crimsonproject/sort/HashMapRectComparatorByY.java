package com.mycompany.crimsonproject.sort;

import java.awt.Rectangle;
import java.util.Comparator;
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
