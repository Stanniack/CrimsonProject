package com.mycompany.crimsonproject.utils;

import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class RGBrange {
    //125, 14, 14 -> 150, 18, 18

    private final Triplet<Integer, Integer, Integer> minBeingAttackedRGB = new Triplet<>(BEINGATTACKED_MINRED, BEINGATTACKED_MINGREEN, BEINGATTACKED_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxBeingAttackedRGB = new Triplet<>(BEINGATTACKED_MAXRED, BEINGATTACKED_MAXGREEN, BEINGATTACKED_MAXBLUE);
    private static final int BEINGATTACKED_MINRED = 125;
    private static final int BEINGATTACKED_MINGREEN = 14;
    private static final int BEINGATTACKED_MINBLUE = 14;
    private static final int BEINGATTACKED_MAXRED = 150;
    private static final int BEINGATTACKED_MAXGREEN = 18;
    private static final int BEINGATTACKED_MAXBLUE = 18;

    private final Triplet<Integer, Integer, Integer> minDestinationRGB = new Triplet<>(DESTINATION_MINRED, DESTINATION_MINGREEN, DESTINATION_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxDestinationRGB = new Triplet<>(DESTINATION_MAXRED, DESTINATION_MAXGREEN, DESTINATION_MAXBLUE);
    private static final int DESTINATION_MINRED = 178;
    private static final int DESTINATION_MINGREEN = 179;
    private static final int DESTINATION_MINBLUE = 179;
    private static final int DESTINATION_MAXRED = 200;
    private static final int DESTINATION_MAXGREEN = 200;
    private static final int DESTINATION_MAXBLUE = 200;

    private final Triplet<Integer, Integer, Integer> minInfoRGB = new Triplet<>(INFO_MINRED, INFO_MINGREEN, INFO_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxInfoRGB = new Triplet<>(INFO_MAXRED, INFO_MAXGREEN, INFO_MAXBLUE);
    private static final int INFO_MINRED = 200;
    private static final int INFO_MINGREEN = 200;
    private static final int INFO_MINBLUE = 200;
    private static final int INFO_MAXRED = 230;
    private static final int INFO_MAXGREEN = 230;
    private static final int INFO_MAXBLUE = 230;

    private final Triplet<Integer, Integer, Integer> minActivedMinerCannonRGB = new Triplet<>(ACT_MINRED, ACT_MINGREEN, ACT_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxActivedMinerCannonRGB = new Triplet<>(ACT_MAXRED, ACT_MAXGREEN, ACT_MAXBLUE);
    private static final int ACT_MINRED = 80;
    private static final int ACT_MAXRED = 90;
    private static final int ACT_MINGREEN = 86;
    private static final int ACT_MAXGREEN = 113;
    private static final int ACT_MINBLUE = 67;
    private static final int ACT_MAXBLUE = 70;

    //76, 85, 62 -> 79, 114, 67
    private final Triplet<Integer, Integer, Integer> minStripActiveMinerRGB = new Triplet<>(ACTSTRIPMINER_MINRED, ACTSTRIPMINER_MINGREEN, ACTSTRIPMINER_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxStripActiveMinerRGB = new Triplet<>(ACTSTRIPMINER_MAXRED, ACTSTRIPMINER_MAXGREEN, ACTSTRIPMINER_MAXBLUE);
    private static final int ACTSTRIPMINER_MINRED = 76;
    private static final int ACTSTRIPMINER_MAXRED = 79;
    private static final int ACTSTRIPMINER_MINGREEN = 85;
    private static final int ACTSTRIPMINER_MAXGREEN = 114;
    private static final int ACTSTRIPMINER_MINBLUE = 62;
    private static final int ACTSTRIPMINER_MAXBLUE = 67;

    private final Triplet<Integer, Integer, Integer> alphaRGB = new Triplet<>(ALPHA_RED, ALPHA_GREEN, ALPHA_BLUE);
    private static final int ALPHA_RED = 117;
    private static final int ALPHA_GREEN = 115;
    private static final int ALPHA_BLUE = 115;

    private final Triplet<Integer, Integer, Integer> minFreeTargetRGB = new Triplet<>(MINFREETARGET_RED, MINFREETARGET_GREEN, MINFREETARGET_BLUE);
    private final Triplet<Integer, Integer, Integer> maxFreeTargetRGB = new Triplet<>(MAXFREETARGET_RED, MAXFREETARGET_GREEN, MAXFREETARGET_BLUE);
    private static final int MINFREETARGET_RED = 210;
    private static final int MINFREETARGET_GREEN = 210;
    private static final int MINFREETARGET_BLUE = 210;
    private static final int MAXFREETARGET_RED = 255;
    private static final int MAXFREETARGET_GREEN = 255;
    private static final int MAXFREETARGET_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> minLockTargetRGB = new Triplet<>(MINLOCKGET_RED, MINLOCKTARGET_GREEN, MINLOCKTARGET_BLUE);
    private final Triplet<Integer, Integer, Integer> maxLockTargetRGB = new Triplet<>(MAXLOCKGET_RED, MAXLOCKTARGET_GREEN, MAXLOCKTARGET_BLUE);
    private static final int MINLOCKGET_RED = 200;
    private static final int MINLOCKTARGET_GREEN = 0;
    private static final int MINLOCKTARGET_BLUE = 0;
    private static final int MAXLOCKGET_RED = 255;
    private static final int MAXLOCKTARGET_GREEN = 0;
    private static final int MAXLOCKTARGET_BLUE = 0;

    private final Triplet<Integer, Integer, Integer> fullWhiteRGB = new Triplet<>(FULLWHITE_RED, FULLWHITE_GREEN, FULLWHITE_BLUE);
    private static final int FULLWHITE_RED = 255;
    private static final int FULLWHITE_GREEN = 255;
    private static final int FULLWHITE_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> minNpcPlayerRGB = new Triplet<>(MINPLAYERNPCCOLOR_RED, MINPLAYERNPCCOLOR_GREEN, MINPLAYERNPCCOLOR_BLUE);
    private final Triplet<Integer, Integer, Integer> maxNpcPlayerRGB = new Triplet<>(MAXPLAYERNPCCOLOR_RED, MAXPLAYERNPCCOLOR_GREEN, MAXPLAYERNPCCOLOR_BLUE);
    private static final int MINPLAYERNPCCOLOR_RED = 101;
    private static final int MINPLAYERNPCCOLOR_GREEN = 101;
    private static final int MINPLAYERNPCCOLOR_BLUE = 101;
    private static final int MAXPLAYERNPCCOLOR_RED = 105;
    private static final int MAXPLAYERNPCCOLOR_GREEN = 107;
    private static final int MAXPLAYERNPCCOLOR_BLUE = 107;

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

    public Triplet<Integer, Integer, Integer> getMinStripActiveMinerRGB() {
        return minStripActiveMinerRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxStripActiveMinerRGB() {
        return maxStripActiveMinerRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinBeingAttackedRGB() {
        return minBeingAttackedRGB;
    }

    public Triplet<Integer, Integer, Integer> getMaxBeingAttackedRGB() {
        return maxBeingAttackedRGB;
    }

}
