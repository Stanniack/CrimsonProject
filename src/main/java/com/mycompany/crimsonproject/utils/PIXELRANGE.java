package com.mycompany.crimsonproject.utils;

import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class PIXELRANGE {

    public final Triplet<Integer, Integer, Integer> tupleMinInfoRGB = new Triplet<>(INFO_MINRED, INFO_MINGREEN, INFO_MINBLUE);
    public final Triplet<Integer, Integer, Integer> tupleMaxInfoRGB = new Triplet<>(INFO_MAXRED, INFO_MAXGREEN, INFO_MAXBLUE);
    public static final int INFO_MINRED = 200;
    public static final int INFO_MINGREEN = 200;
    public static final int INFO_MINBLUE = 200;
    public static final int INFO_MAXRED = 230;
    public static final int INFO_MAXGREEN = 230;
    public static final int INFO_MAXBLUE = 230;
    
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

    public final Triplet<Integer, Integer, Integer> minTupleFreeTargetRGB = new Triplet<>(MINFREETARGET_RED, MINFREETARGET_GREEN, MINFREETARGET_BLUE);
    public static final int MINFREETARGET_RED = 210;
    public static final int MINFREETARGET_GREEN = 210;
    public static final int MINFREETARGET_BLUE = 210;

    public final Triplet<Integer, Integer, Integer> maxTupleFreeTargetRGB = new Triplet<>(MAXFREETARGET_RED, MAXFREETARGET_GREEN, MAXFREETARGET_BLUE);
    public static final int MAXFREETARGET_RED = 255;
    public static final int MAXFREETARGET_GREEN = 255;
    public static final int MAXFREETARGET_BLUE = 255;

    public final Triplet<Integer, Integer, Integer> minTupleLockTargetRGB = new Triplet<>(MINLOCKGET_RED, MINLOCKTARGET_GREEN, MINLOCKTARGET_BLUE);
    public static final int MINLOCKGET_RED = 200;
    public static final int MINLOCKTARGET_GREEN = 0;
    public static final int MINLOCKTARGET_BLUE = 0;

    public final Triplet<Integer, Integer, Integer> maxTupleLockTargetRGB = new Triplet<>(MAXLOCKGET_RED, MAXLOCKTARGET_GREEN, MAXLOCKTARGET_BLUE);
    public static final int MAXLOCKGET_RED = 255;
    public static final int MAXLOCKTARGET_GREEN = 0;
    public static final int MAXLOCKTARGET_BLUE = 0;

    public final Triplet<Integer, Integer, Integer> tupleFullWhiteRGB = new Triplet<>(FULLWHITE_RED, FULLWHITE_GREEN, FULLWHITE_BLUE);
    public static final int FULLWHITE_RED = 255;
    public static final int FULLWHITE_GREEN = 255;
    public static final int FULLWHITE_BLUE = 255;
    
    public final Triplet<Integer, Integer, Integer> maxTupleNpcPlayerRGB = new Triplet<>(MINPLAYERNPCCOLOR_RED, MINPLAYERNPCCOLOR_GREEN, MINPLAYERNPCCOLOR_BLUE);
    public static final int MINPLAYERNPCCOLOR_RED = 101;
    public static final int MINPLAYERNPCCOLOR_GREEN = 101;
    public static final int MINPLAYERNPCCOLOR_BLUE = 101;
    
    public final Triplet<Integer, Integer, Integer> minTupleNpcPlayerRGB = new Triplet<>(MAXPLAYERNPCCOLOR_RED, MAXPLAYERNPCCOLOR_GREEN, MAXPLAYERNPCCOLOR_BLUE);
    public static final int MAXPLAYERNPCCOLOR_RED = 105;
    public static final int MAXPLAYERNPCCOLOR_GREEN = 107;
    public static final int MAXPLAYERNPCCOLOR_BLUE = 107;

}
