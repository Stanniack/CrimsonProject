package com.mycompany.crimsonproject.utils;

import org.javatuples.Sextet;

/**
 *
 * @author Devmachine
 */
public class PIXELRANGE {
    
    public final Sextet<Integer, Integer, Integer, Integer, Integer, Integer> tupleACT_RGB = 
            new Sextet<>(ACT_MINRED, ACT_MINGREEN, ACT_MINBLUE, ACT_MAXRED, ACT_MAXGREEN, ACT_MAXBLUE);
    public static final int ACT_MINRED = 80;
    public static final int ACT_MAXRED = 90;
    public static final int ACT_MINGREEN = 86;
    public static final int ACT_MAXGREEN = 113;
    public static final int ACT_MINBLUE = 67;
    public static final int ACT_MAXBLUE = 70;
    
    public static final int CANCEL_MINRED = 137;
    public static final int CANCEL_MAXRED = 148;
    public static final int CANCEL_MINGREEN = 35;
    public static final int CANCEL_MAXGREEN = 69;
    public static final int CANCEL_MINBLUE = 16;
    public static final int CANCEL_MAXBLUE = 56;
    
    public static final int ALPHA_RED = 104;
    public static final int ALPHA_GREEN = 102;
    public static final int ALPHA_BLUE = 56;
   
    
}
