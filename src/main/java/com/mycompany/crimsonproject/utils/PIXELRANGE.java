package com.mycompany.crimsonproject.utils;

import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class PIXELRANGE {

    private final Triplet<Integer, Integer, Integer> tupleMinDestinationRGB = new Triplet<>(DESTINATION_MINRED, DESTINATION_MINGREEN, DESTINATION_MINBLUE);
    private final Triplet<Integer, Integer, Integer> tupleMaxDestinationRGB = new Triplet<>(DESTINATION_MAXRED, DESTINATION_MAXGREEN, DESTINATION_MAXBLUE);
    public static final int DESTINATION_MINRED = 178;
    public static final int DESTINATION_MINGREEN = 179;
    public static final int DESTINATION_MINBLUE = 179;
    public static final int DESTINATION_MAXRED = 200;
    public static final int DESTINATION_MAXGREEN = 200;
    public static final int DESTINATION_MAXBLUE = 200;

    private final Triplet<Integer, Integer, Integer> tupleMinInfoRGB = new Triplet<>(INFO_MINRED, INFO_MINGREEN, INFO_MINBLUE);
    private final Triplet<Integer, Integer, Integer> tupleMaxInfoRGB = new Triplet<>(INFO_MAXRED, INFO_MAXGREEN, INFO_MAXBLUE);
    public static final int INFO_MINRED = 200;
    public static final int INFO_MINGREEN = 200;
    public static final int INFO_MINBLUE = 200;
    public static final int INFO_MAXRED = 230;
    public static final int INFO_MAXGREEN = 230;
    public static final int INFO_MAXBLUE = 230;

    private final Triplet<Integer, Integer, Integer> tupleMinACTRGB = new Triplet<>(ACT_MINRED, ACT_MINGREEN, ACT_MINBLUE);
    private final Triplet<Integer, Integer, Integer> tupleMaxACTRGB = new Triplet<>(ACT_MAXRED, ACT_MAXGREEN, ACT_MAXBLUE);
    public static final int ACT_MINRED = 80;
    public static final int ACT_MAXRED = 90;
    public static final int ACT_MINGREEN = 86;
    public static final int ACT_MAXGREEN = 113;
    public static final int ACT_MINBLUE = 67;
    public static final int ACT_MAXBLUE = 70;

    private final Triplet<Integer, Integer, Integer> tupleMinCANCELRGB = new Triplet<>(CANCEL_MINRED, CANCEL_MINGREEN, CANCEL_MINBLUE);
    private final Triplet<Integer, Integer, Integer> tupleMaxCANCELRGB = new Triplet<>(CANCEL_MAXRED, CANCEL_MAXGREEN, CANCEL_MAXBLUE);
    public static final int CANCEL_MINRED = 137;
    public static final int CANCEL_MAXRED = 148;
    public static final int CANCEL_MINGREEN = 35;
    public static final int CANCEL_MAXGREEN = 69;
    public static final int CANCEL_MINBLUE = 16;
    public static final int CANCEL_MAXBLUE = 56;

    private final Triplet<Integer, Integer, Integer> tupleAlphaRGB = new Triplet<>(ALPHA_RED, ALPHA_GREEN, ALPHA_BLUE);
    public static final int ALPHA_RED = 117;
    public static final int ALPHA_GREEN = 115;
    public static final int ALPHA_BLUE = 115;

    private final Triplet<Integer, Integer, Integer> minTupleFreeTargetRGB = new Triplet<>(MINFREETARGET_RED, MINFREETARGET_GREEN, MINFREETARGET_BLUE);
    public static final int MINFREETARGET_RED = 210;
    public static final int MINFREETARGET_GREEN = 210;
    public static final int MINFREETARGET_BLUE = 210;

    private final Triplet<Integer, Integer, Integer> maxTupleFreeTargetRGB = new Triplet<>(MAXFREETARGET_RED, MAXFREETARGET_GREEN, MAXFREETARGET_BLUE);
    public static final int MAXFREETARGET_RED = 255;
    public static final int MAXFREETARGET_GREEN = 255;
    public static final int MAXFREETARGET_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> minTupleLockTargetRGB = new Triplet<>(MINLOCKGET_RED, MINLOCKTARGET_GREEN, MINLOCKTARGET_BLUE);
    public static final int MINLOCKGET_RED = 200;
    public static final int MINLOCKTARGET_GREEN = 0;
    public static final int MINLOCKTARGET_BLUE = 0;

    private final Triplet<Integer, Integer, Integer> maxTupleLockTargetRGB = new Triplet<>(MAXLOCKGET_RED, MAXLOCKTARGET_GREEN, MAXLOCKTARGET_BLUE);
    public static final int MAXLOCKGET_RED = 255;
    public static final int MAXLOCKTARGET_GREEN = 0;
    public static final int MAXLOCKTARGET_BLUE = 0;

    private final Triplet<Integer, Integer, Integer> tupleFullWhiteRGB = new Triplet<>(FULLWHITE_RED, FULLWHITE_GREEN, FULLWHITE_BLUE);
    public static final int FULLWHITE_RED = 255;
    public static final int FULLWHITE_GREEN = 255;
    public static final int FULLWHITE_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> maxTupleNpcPlayerRGB = new Triplet<>(MINPLAYERNPCCOLOR_RED, MINPLAYERNPCCOLOR_GREEN, MINPLAYERNPCCOLOR_BLUE);
    public static final int MINPLAYERNPCCOLOR_RED = 101;
    public static final int MINPLAYERNPCCOLOR_GREEN = 101;
    public static final int MINPLAYERNPCCOLOR_BLUE = 101;

    private final Triplet<Integer, Integer, Integer> minTupleNpcPlayerRGB = new Triplet<>(MAXPLAYERNPCCOLOR_RED, MAXPLAYERNPCCOLOR_GREEN, MAXPLAYERNPCCOLOR_BLUE);
    public static final int MAXPLAYERNPCCOLOR_RED = 105;
    public static final int MAXPLAYERNPCCOLOR_GREEN = 107;
    public static final int MAXPLAYERNPCCOLOR_BLUE = 107;

    public Triplet<Integer, Integer, Integer> getTupleMinDestinationRGB() {
        return tupleMinDestinationRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleMaxDestinationRGB() {
        return tupleMaxDestinationRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleMaxInfoRGB() {
        return tupleMaxInfoRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleMinACTRGB() {
        return tupleMinACTRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleMaxACTRGB() {
        return tupleMaxACTRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleMinCANCELRGB() {
        return tupleMinCANCELRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleMaxCANCELRGB() {
        return tupleMaxCANCELRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleAlphaRGB() {
        return tupleAlphaRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinTupleFreeTargetRGB() {
        return minTupleFreeTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxTupleFreeTargetRGB() {
        return maxTupleFreeTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinTupleLockTargetRGB() {
        return minTupleLockTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxTupleLockTargetRGB() {
        return maxTupleLockTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleFullWhiteRGB() {
        return tupleFullWhiteRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxTupleNpcPlayerRGB() {
        return maxTupleNpcPlayerRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinTupleNpcPlayerRGB() {
        return minTupleNpcPlayerRGB;
    }

    public Triplet<Integer, Integer, Integer> getTupleMinInfoRGB() {
        return tupleMinInfoRGB;
    }
    
    

}
