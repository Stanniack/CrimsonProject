package com.mycompany.crimsonproject.utils;

import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class RGBrange {

    private final Triplet<Integer, Integer, Integer> minDestinationRGB = new Triplet<>(DESTINATION_MINRED, DESTINATION_MINGREEN, DESTINATION_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxDestinationRGB = new Triplet<>(DESTINATION_MAXRED, DESTINATION_MAXGREEN, DESTINATION_MAXBLUE);
    public static final int DESTINATION_MINRED = 178;
    public static final int DESTINATION_MINGREEN = 179;
    public static final int DESTINATION_MINBLUE = 179;
    public static final int DESTINATION_MAXRED = 200;
    public static final int DESTINATION_MAXGREEN = 200;
    public static final int DESTINATION_MAXBLUE = 200;

    private final Triplet<Integer, Integer, Integer> minInfoRGB = new Triplet<>(INFO_MINRED, INFO_MINGREEN, INFO_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxInfoRGB = new Triplet<>(INFO_MAXRED, INFO_MAXGREEN, INFO_MAXBLUE);
    public static final int INFO_MINRED = 200;
    public static final int INFO_MINGREEN = 200;
    public static final int INFO_MINBLUE = 200;
    public static final int INFO_MAXRED = 230;
    public static final int INFO_MAXGREEN = 230;
    public static final int INFO_MAXBLUE = 230;

    private final Triplet<Integer, Integer, Integer> minActivedMinerCannonRGB = new Triplet<>(ACT_MINRED, ACT_MINGREEN, ACT_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxActivedMinerCannonRGB = new Triplet<>(ACT_MAXRED, ACT_MAXGREEN, ACT_MAXBLUE);
    public static final int ACT_MINRED = 80;
    public static final int ACT_MAXRED = 90;
    public static final int ACT_MINGREEN = 86;
    public static final int ACT_MAXGREEN = 113;
    public static final int ACT_MINBLUE = 67;
    public static final int ACT_MAXBLUE = 70;

    private final Triplet<Integer, Integer, Integer> minCancelMinerCannonRGB = new Triplet<>(CANCEL_MINRED, CANCEL_MINGREEN, CANCEL_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxCancelMinerCannonRGB = new Triplet<>(CANCEL_MAXRED, CANCEL_MAXGREEN, CANCEL_MAXBLUE);
    public static final int CANCEL_MINRED = 137;
    public static final int CANCEL_MAXRED = 148;
    public static final int CANCEL_MINGREEN = 35;
    public static final int CANCEL_MAXGREEN = 69;
    public static final int CANCEL_MINBLUE = 16;
    public static final int CANCEL_MAXBLUE = 56;

    private final Triplet<Integer, Integer, Integer> alphaRGB = new Triplet<>(ALPHA_RED, ALPHA_GREEN, ALPHA_BLUE);
    public static final int ALPHA_RED = 117;
    public static final int ALPHA_GREEN = 115;
    public static final int ALPHA_BLUE = 115;

    private final Triplet<Integer, Integer, Integer> minFreeTargetRGB = new Triplet<>(MINFREETARGET_RED, MINFREETARGET_GREEN, MINFREETARGET_BLUE);
    private final Triplet<Integer, Integer, Integer> maxFreeTargetRGB = new Triplet<>(MAXFREETARGET_RED, MAXFREETARGET_GREEN, MAXFREETARGET_BLUE);
    public static final int MINFREETARGET_RED = 210;
    public static final int MINFREETARGET_GREEN = 210;
    public static final int MINFREETARGET_BLUE = 210;
    public static final int MAXFREETARGET_RED = 255;
    public static final int MAXFREETARGET_GREEN = 255;
    public static final int MAXFREETARGET_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> minLockTargetRGB = new Triplet<>(MINLOCKGET_RED, MINLOCKTARGET_GREEN, MINLOCKTARGET_BLUE);
    private final Triplet<Integer, Integer, Integer> maxLockTargetRGB = new Triplet<>(MAXLOCKGET_RED, MAXLOCKTARGET_GREEN, MAXLOCKTARGET_BLUE);
    public static final int MINLOCKGET_RED = 200;
    public static final int MINLOCKTARGET_GREEN = 0;
    public static final int MINLOCKTARGET_BLUE = 0;
    public static final int MAXLOCKGET_RED = 255;
    public static final int MAXLOCKTARGET_GREEN = 0;
    public static final int MAXLOCKTARGET_BLUE = 0;

    private final Triplet<Integer, Integer, Integer> fullWhiteRGB = new Triplet<>(FULLWHITE_RED, FULLWHITE_GREEN, FULLWHITE_BLUE);
    public static final int FULLWHITE_RED = 255;
    public static final int FULLWHITE_GREEN = 255;
    public static final int FULLWHITE_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> minNpcPlayerRGB = new Triplet<>(MAXPLAYERNPCCOLOR_RED, MAXPLAYERNPCCOLOR_GREEN, MAXPLAYERNPCCOLOR_BLUE);
    private final Triplet<Integer, Integer, Integer> maxNpcPlayerRGB = new Triplet<>(MINPLAYERNPCCOLOR_RED, MINPLAYERNPCCOLOR_GREEN, MINPLAYERNPCCOLOR_BLUE);
    public static final int MAXPLAYERNPCCOLOR_RED = 105;
    public static final int MAXPLAYERNPCCOLOR_GREEN = 107;
    public static final int MAXPLAYERNPCCOLOR_BLUE = 107;
    public static final int MINPLAYERNPCCOLOR_RED = 101;
    public static final int MINPLAYERNPCCOLOR_GREEN = 101;
    public static final int MINPLAYERNPCCOLOR_BLUE = 101;

    public Triplet<Integer, Integer, Integer> getMinDestinationRGB() {
        return minDestinationRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxDestinationRGB() {
        return maxDestinationRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxInfoRGB() {
        return maxInfoRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinActivedMinerCannonRGB() {
        return minActivedMinerCannonRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxActivedMinerCannonRGB() {
        return maxActivedMinerCannonRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinCancelMinerCannonRGB() {
        return minCancelMinerCannonRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxCancelMinerCannonRGB() {
        return maxCancelMinerCannonRGB;
    }

    public Triplet<Integer, Integer, Integer> getAlphaRGB() {
        return alphaRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinFreeTargetRGB() {
        return minFreeTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxFreeTargetRGB() {
        return maxFreeTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinLockTargetRGB() {
        return minLockTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxLockTargetRGB() {
        return maxLockTargetRGB;
    }

    public Triplet<Integer, Integer, Integer> getFullWhiteRGB() {
        return fullWhiteRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxNpcPlayerRGB() {
        return maxNpcPlayerRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinNpcPlayerRGB() {
        return minNpcPlayerRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinInfoRGB() {
        return minInfoRGB;
    }

}
