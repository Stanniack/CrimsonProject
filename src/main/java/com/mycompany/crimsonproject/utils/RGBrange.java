package com.mycompany.crimsonproject.utils;

import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class RGBrange {

    private final Triplet<Integer, Integer, Integer> minBeingAttacked = new Triplet<>(BEINGATTACKED_MINRED, BEINGATTACKED_MINGREEN, BEINGATTACKED_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxBeingAttacked = new Triplet<>(BEINGATTACKED_MAXRED, BEINGATTACKED_MAXGREEN, BEINGATTACKED_MAXBLUE);
    private static final int BEINGATTACKED_MINRED = 125;
    private static final int BEINGATTACKED_MINGREEN = 14;
    private static final int BEINGATTACKED_MINBLUE = 14;
    private static final int BEINGATTACKED_MAXRED = 150;
    private static final int BEINGATTACKED_MAXGREEN = 18;
    private static final int BEINGATTACKED_MAXBLUE = 18;

    private final Triplet<Integer, Integer, Integer> minWhiteLabel = new Triplet<>(DESTINATION_MINRED, DESTINATION_MINGREEN, DESTINATION_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxWhiteLabel = new Triplet<>(DESTINATION_MAXRED, DESTINATION_MAXGREEN, DESTINATION_MAXBLUE);
    private static final int DESTINATION_MINRED = 178;
    private static final int DESTINATION_MINGREEN = 179;
    private static final int DESTINATION_MINBLUE = 179;
    private static final int DESTINATION_MAXRED = 200;
    private static final int DESTINATION_MAXGREEN = 200;
    private static final int DESTINATION_MAXBLUE = 200;

    private final Triplet<Integer, Integer, Integer> minInfo = new Triplet<>(INFO_MINRED, INFO_MINGREEN, INFO_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxInfo = new Triplet<>(INFO_MAXRED, INFO_MAXGREEN, INFO_MAXBLUE);
    private static final int INFO_MINRED = 200;
    private static final int INFO_MINGREEN = 200;
    private static final int INFO_MINBLUE = 200;
    private static final int INFO_MAXRED = 230;
    private static final int INFO_MAXGREEN = 230;
    private static final int INFO_MAXBLUE = 230;

    private final Triplet<Integer, Integer, Integer> minGreenVenture = new Triplet<>(ACT_MINRED, ACT_MINGREEN, ACT_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxGreenVenture = new Triplet<>(ACT_MAXRED, ACT_MAXGREEN, ACT_MAXBLUE);
    private static final int ACT_MINRED = 80;
    private static final int ACT_MAXRED = 90;
    private static final int ACT_MINGREEN = 86;
    private static final int ACT_MAXGREEN = 113;
    private static final int ACT_MINBLUE = 67;
    private static final int ACT_MAXBLUE = 70;

    //76, 85, 62 -> 79, 114, 67
    private final Triplet<Integer, Integer, Integer> minGreenStrip = new Triplet<>(ACTSTRIPMINER_MINRED, ACTSTRIPMINER_MINGREEN, ACTSTRIPMINER_MINBLUE);
    private final Triplet<Integer, Integer, Integer> maxGreenStrip = new Triplet<>(ACTSTRIPMINER_MAXRED, ACTSTRIPMINER_MAXGREEN, ACTSTRIPMINER_MAXBLUE);
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

    private final Triplet<Integer, Integer, Integer> minFreeTarget = new Triplet<>(MINFREETARGET_RED, MINFREETARGET_GREEN, MINFREETARGET_BLUE);
    private final Triplet<Integer, Integer, Integer> maxFreeTarget = new Triplet<>(MAXFREETARGET_RED, MAXFREETARGET_GREEN, MAXFREETARGET_BLUE);
    private static final int MINFREETARGET_RED = 210;
    private static final int MINFREETARGET_GREEN = 210;
    private static final int MINFREETARGET_BLUE = 210;
    private static final int MAXFREETARGET_RED = 255;
    private static final int MAXFREETARGET_GREEN = 255;
    private static final int MAXFREETARGET_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> minLockTarget = new Triplet<>(MINLOCKGET_RED, MINLOCKTARGET_GREEN, MINLOCKTARGET_BLUE);
    private final Triplet<Integer, Integer, Integer> maxLockTarget = new Triplet<>(MAXLOCKGET_RED, MAXLOCKTARGET_GREEN, MAXLOCKTARGET_BLUE);
    private static final int MINLOCKGET_RED = 200;
    private static final int MINLOCKTARGET_GREEN = 0;
    private static final int MINLOCKTARGET_BLUE = 0;
    private static final int MAXLOCKGET_RED = 255;
    private static final int MAXLOCKTARGET_GREEN = 0;
    private static final int MAXLOCKTARGET_BLUE = 0;

    private final Triplet<Integer, Integer, Integer> fullWhite = new Triplet<>(FULLWHITE_RED, FULLWHITE_GREEN, FULLWHITE_BLUE);
    private static final int FULLWHITE_RED = 255;
    private static final int FULLWHITE_GREEN = 255;
    private static final int FULLWHITE_BLUE = 255;

    private final Triplet<Integer, Integer, Integer> minNpcPlayer = new Triplet<>(MINPLAYERNPCCOLOR_RED, MINPLAYERNPCCOLOR_GREEN, MINPLAYERNPCCOLOR_BLUE);
    private final Triplet<Integer, Integer, Integer> maxNpcPlayer = new Triplet<>(MAXPLAYERNPCCOLOR_RED, MAXPLAYERNPCCOLOR_GREEN, MAXPLAYERNPCCOLOR_BLUE);
    private static final int MINPLAYERNPCCOLOR_RED = 101;
    private static final int MINPLAYERNPCCOLOR_GREEN = 101;
    private static final int MINPLAYERNPCCOLOR_BLUE = 101;
    private static final int MAXPLAYERNPCCOLOR_RED = 105;
    private static final int MAXPLAYERNPCCOLOR_GREEN = 107;
    private static final int MAXPLAYERNPCCOLOR_BLUE = 107;

    public Triplet<Integer, Integer, Integer> getMinWhiteLabel() {
        return minWhiteLabel;
    }

    public Triplet<Integer, Integer, Integer> getMaxWhiteLabel() {
        return maxWhiteLabel;
    }

    public Triplet<Integer, Integer, Integer> getMaxInfo() {
        return maxInfo;
    }

    public Triplet<Integer, Integer, Integer> getMinGreenVenture() {
        return minGreenVenture;
    }

    public Triplet<Integer, Integer, Integer> getMaxGreenVenture() {
        return maxGreenVenture;
    }

    public Triplet<Integer, Integer, Integer> getAlphaRGB() {
        return alphaRGB;
    }

    public Triplet<Integer, Integer, Integer> getMinFreeTarget() {
        return minFreeTarget;
    }

    public Triplet<Integer, Integer, Integer> getMaxFreeTarget() {
        return maxFreeTarget;
    }

    public Triplet<Integer, Integer, Integer> getMinLockTarget() {
        return minLockTarget;
    }

    public Triplet<Integer, Integer, Integer> getMaxLockTarget() {
        return maxLockTarget;
    }

    public Triplet<Integer, Integer, Integer> getFullWhite() {
        return fullWhite;
    }

    public Triplet<Integer, Integer, Integer> getMaxNpcPlayer() {
        return maxNpcPlayer;
    }

    public Triplet<Integer, Integer, Integer> getMinNpcPlayer() {
        return minNpcPlayer;
    }

    public Triplet<Integer, Integer, Integer> getMinInfo() {
        return minInfo;
    }

    public Triplet<Integer, Integer, Integer> getMinGreenStrip() {
        return minGreenStrip;
    }

    public Triplet<Integer, Integer, Integer> getMaxGreenStrip() {
        return maxGreenStrip;
    }

    public Triplet<Integer, Integer, Integer> getMinBeingAttacked() {
        return minBeingAttacked;
    }

    public Triplet<Integer, Integer, Integer> getMaxBeingAttacked() {
        return maxBeingAttacked;
    }

}
