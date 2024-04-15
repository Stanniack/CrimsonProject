package com.mycompany.crimsonproject.utils;

import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class PIXELRANGE {

    public final Triplet<Integer, Integer, Integer> tupleMinACTRGB = new Triplet<>(ACT_MINRED, ACT_MINGREEN, ACT_MINBLUE);
    public final Triplet<Integer, Integer, Integer> tupleMaxACTRGB = new Triplet<>(ACT_MAXRED, ACT_MAXGREEN, ACT_MAXBLUE);
    public static final int ACT_MINRED = 80;
    public static final int ACT_MAXRED = 90;
    public static final int ACT_MINGREEN = 86;
    public static final int ACT_MAXGREEN = 113;
    public static final int ACT_MINBLUE = 67;
    public static final int ACT_MAXBLUE = 70;

    public final Triplet<Integer, Integer, Integer> tupleMinCANCELRGB = new Triplet<>(CANCEL_MINRED, CANCEL_MINGREEN, CANCEL_MINBLUE);
    public final Triplet<Integer, Integer, Integer> tupleMaxCANCELRGB = new Triplet<>(CANCEL_MAXRED, CANCEL_MAXGREEN, CANCEL_MAXBLUE);
    public static final int CANCEL_MINRED = 137;
    public static final int CANCEL_MAXRED = 148;
    public static final int CANCEL_MINGREEN = 35;
    public static final int CANCEL_MAXGREEN = 69;
    public static final int CANCEL_MINBLUE = 16;
    public static final int CANCEL_MAXBLUE = 56;

    public final Triplet<Integer, Integer, Integer> tupleAlphaRGB = new Triplet<>(ALPHA_RED, ALPHA_GREEN, ALPHA_BLUE);
    public static final int ALPHA_RED = 117;
    public static final int ALPHA_GREEN = 115;
    public static final int ALPHA_BLUE = 115;

    public final Triplet<Integer, Integer, Integer> tupleFreeTargetRGB = new Triplet<>(FREETARGET_RED, FREETARGET_GREEN, FREETARGET_BLUE);
    public static final int FREETARGET_RED = 213;
    public static final int FREETARGET_GREEN = 214;
    public static final int FREETARGET_BLUE = 214;

    public final Triplet<Integer, Integer, Integer> tupleLockTargetRGB = new Triplet<>(LOCKGET_RED, LOCKTARGET_GREEN, LOCKTARGET_BLUE);
    public static final int LOCKGET_RED = 255;
    public static final int LOCKTARGET_GREEN = 0;
    public static final int LOCKTARGET_BLUE = 0;

    public final Triplet<Integer, Integer, Integer> tupleFullWhiteRGB = new Triplet<>(FULLWHITE_RED, FULLWHITE_GREEN, FULLWHITE_BLUE);
    public static final int FULLWHITE_RED = 255;
    public static final int FULLWHITE_GREEN = 255;
    public static final int FULLWHITE_BLUE = 255;

}
