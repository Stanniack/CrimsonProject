package com.mycompany.crimsonproject.resolutions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.javatuples.Pair;
import org.javatuples.Quartet;

/**
 * @author Devmachine
 *
 * This class uses EVE font size SMALL and scale resolution 100% 1920x1080
 *
 * BLOCKSCREEN calculator: x1 = x; x2 = x + w; y1 = y; y2 = y + h.
 *
 * BLOSCKSCREEN calculator with DEADZONE: x1 = x; x2 = x + w + DEADZONE; y1 = y;
 * y2 = y + h + DEADZONE;
 *
 * BLOCKSCREEN: Rect limitation where to find the recognized rects
 *
 * DEADZONE INFO: Pixels amount to add in the BLOCKSCREEN for caution or not
 */
public class R1920x1080 {

    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> invalidTargetList = Arrays.asList(
            new Pair<>(INVALIDTARGET_W1, INVALIDTARGET_H1),
            new Pair<>(INVALIDTARGET_W2, INVALIDTARGET_H2),
            new Pair<>(INVALIDTARGET_W3, INVALIDTARGET_H3));
    private static final int INVALIDTARGET_W1 = 106;
    private static final int INVALIDTARGET_W2 = 200;
    private static final int INVALIDTARGET_W3 = 101;
    private static final int INVALIDTARGET_H1 = 17;
    private static final int INVALIDTARGET_H2 = 50;
    private static final int INVALIDTARGET_H3 = 16;

    private final Quartet<Integer, Integer, Integer, Integer> invalidTargetDeadZoneList
            = new Quartet<>(INVALIDTARGET_DEADZONE_X1, INVALIDTARGET_DEADZONE_X2_W, INVALIDTARGET_DEADZONE_Y1, INVALIDTARGET_DEADZONE_Y2_H);
    private static final int INVALIDTARGET_DEADZONE_X1 = 751;
    private static final int INVALIDTARGET_DEADZONE_X2_W = 951;
    private static final int INVALIDTARGET_DEADZONE_Y1 = 420;
    private static final int INVALIDTARGET_DEADZONE_Y2_H = 470;
    /*
     * *****************************************************************************
     */
    private static final int BEINGATTACKED_X1 = 1006;
    private static final int BEINGATTACKED_Y1 = 960;
    private static final int BEINGATTACKED_W1 = 24;
    private static final int BEINGATTACKED_H1 = 19;
    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> miningBotList = Arrays.asList(
            new Pair<>(MININGBOT1_W1, MININGBOT1_H1),
            new Pair<>(MININGBOT1_W2, MININGBOT1_H1),
            new Pair<>(MININGBOT1_W3, MININGBOT1_H2));
    private static final int MININGBOT1_W1 = 60;
    private static final int MININGBOT1_W2 = 59;
    private static final int MININGBOT1_W3 = 76;
    private static final int MININGBOT1_H2 = 12;
    private static final int MININGBOT1_H1 = 9;

    private final List<Pair<Integer, Integer>> homeStationList = Arrays.asList(
            new Pair<>(HOMESTATION_W1, HOMESTATION_H1),
            new Pair<>(HOMESTATION_W2, HOMESTATION_H1));
    private static final int HOMESTATION_W1 = 67;
    private static final int HOMESTATION_W2 = 68;
    private static final int HOMESTATION_H1 = 9;

    private final List<Pair<Integer, Integer>> AstBeltIList = Arrays.asList(
            new Pair<>(ASTBELTI_W1, ASTBELTI_H1),
            new Pair<>(ASTBELTI_W2, ASTBELTI_H1));

    private static final int ASTBELTI_W1 = 73;
    private static final int ASTBELTI_W2 = 74;
    private static final int ASTBELTI_H1 = 9;

    private final List<Pair<Integer, Integer>> AstBeltIIList = Arrays.asList(
            new Pair<>(ASTBELTII_W1, ASTBELTII_H1),
            new Pair<>(ASTBELTII_W2, ASTBELTII_H1));

    private static final int ASTBELTII_W1 = 76;
    private static final int ASTBELTII_W2 = 77;
    private static final int ASTBELTII_H1 = 9;

    private final List<Pair<Integer, Integer>> AstBeltIIIList = Arrays.asList(
            new Pair<>(ASTBELTIII_W1, ASTBELTIII_H1),
            new Pair<>(ASTBELTIII_W2, ASTBELTIII_H1));

    private static final int ASTBELTIII_W1 = 79;
    private static final int ASTBELTIII_W2 = 80;
    private static final int ASTBELTIII_H1 = 9;

    private final List<Pair<Integer, Integer>> AstBeltIIIIList = Arrays.asList(
            new Pair<>(ASTBELTIIII_W1, ASTBELTIIII_H1),
            new Pair<>(ASTBELTIIII_W2, ASTBELTIIII_H1));

    private static final int ASTBELTIIII_W1 = 82;
    private static final int ASTBELTIIII_W2 = 83;
    private static final int ASTBELTIIII_H1 = 9;

    private final List<Pair<Integer, Integer>> AstBeltIIIIIList = Arrays.asList(
            new Pair<>(ASTBELTIIIII_W1, ASTBELTIIIII_H1),
            new Pair<>(ASTBELTIIIII_W2, ASTBELTIIIII_H1));

    private static final int ASTBELTIIIII_W1 = 85;
    private static final int ASTBELTIIIII_W2 = 86;
    private static final int ASTBELTIIIII_H1 = 9;

    private final Map<Integer, List<Pair<Integer, Integer>>> astBeltsMap = Map.of(
            1, AstBeltIList,
            2, AstBeltIIList,
            3, AstBeltIIIList,
            4, AstBeltIIIIList,
            5, AstBeltIIIIIList
    );

    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> warpList = Arrays.asList(
            new Pair<>(WITHIN_W1, WITHIN_H1),
            new Pair<>(ALLRECT_W1, ALLRECT_H1),
            new Pair<>(WARPARROW_W1, WARPARROW_H1),
            new Pair<>(WARP_W1, WARP_H1),
            new Pair<>(TO_W1, TO_H1),
            new Pair<>(DISTANCE1_W1, DISTANCE1_H1),
            new Pair<>(DISTANCE2_W1, DISTANCE2_H1),
            new Pair<>(WARPTOWITHIN_W1, WARPTOWITHIN_H1));
    private static final int WITHIN_W1 = 31;
    private static final int WITHIN_H1 = 9;
    private static final int ALLRECT_W1 = 139;
    private static final int ALLRECT_H1 = 13;
    private static final int WARPARROW_W1 = 12;
    private static final int WARPARROW_H1 = 6;
    private static final int WARP_W1 = 25;
    private static final int WARP_H1 = 12;
    private static final int TO_W1 = 9;
    private static final int TO_H1 = 9;
    private static final int DISTANCE1_W1 = 9;
    private static final int DISTANCE1_H1 = 12;
    private static final int DISTANCE2_W1 = 11;
    private static final int DISTANCE2_H1 = 12;
    private static final int WARPTOWITHIN_W1 = 80;
    private static final int WARPTOWITHIN_H1 = 12;

    private final List<Pair<Integer, Integer>> dockList = Arrays.asList(
            new Pair<>(DOCK_W0, DOCK_H1),
            new Pair<>(DOCK_W1, DOCK_H1),
            new Pair<>(DOCK_W2, DOCK_H1),
            new Pair<>(DOCK_W3, DOCK_H1),
            new Pair<>(DOCK_W1, DOCK_H2),
            new Pair<>(DOCK_W4, DOCK_H3),
            new Pair<>(BLOCKDOCK_H3, BLOCLDOCK_H3));
    private static final int DOCK_W0 = 22;
    private static final int DOCK_W1 = 23;
    private static final int DOCK_W2 = 24;
    private static final int DOCK_W3 = 25;
    private static final int DOCK_W4 = 46;
    private static final int DOCK_H1 = 9;
    private static final int DOCK_H2 = 10;
    private static final int DOCK_H3 = 12;
    private static final int BLOCKDOCK_H3 = 12;
    private static final int BLOCLDOCK_H3 = 12;
    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> hangarList = Arrays.asList(
            new Pair<>(HANGAR_W1, HANGAR_H1),
            new Pair<>(HANGAR_W1, HANGAR_H2));
    private static final int HANGAR_W1 = 34;
    private static final int HANGAR_H1 = 12;
    private static final int HANGAR_H2 = 11;

    private final List<Pair<Integer, Integer>> closeLocationButtonList = Arrays.asList(
            new Pair<>(CLOSELOCATIONBUTTON_W1, CLOSELOCATIONBUTTON_H1));
    private static final int CLOSELOCATIONBUTTON_W1 = 10;
    private static final int CLOSELOCATIONBUTTON_H1 = 10;

    private final Quartet<Integer, Integer, Integer, Integer> locationTabDeadZoneTuple
            = new Quartet<>(LOCATIONTAB_DEADZONE_X1, LOCATIONTAB_DEADZONE_X2_W, LOCATIONTAB_DEADZONE_Y1, LOCATIONTAB_DEADZONE_Y2_H);
    private static final int LOCATIONTAB_DEADZONE_X1 = 445;
    private static final int LOCATIONTAB_DEADZONE_X2_W = 889;
    private static final int LOCATIONTAB_DEADZONE_Y1 = 0;
    private static final int LOCATIONTAB_DEADZONE_Y2_H = 540;
    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> allAsteroidsList = Arrays.asList(
            new Pair<>(CONDENSED_W1, CONDENSED_H1),
            new Pair<>(CONDENSED_W2, CONDENSED_H1),
            new Pair<>(SCORDITE_W1, SCORDITE_H1),
            new Pair<>(SCORDITE_W1, SCORDITE_H2),
            new Pair<>(DENSE_W1, DENSE_H1),
            new Pair<>(DENSE_W2, DENSE_H1),
            new Pair<>(CONCENTRATED_W1, CONCENTRATED_H1),
            new Pair<>(CONCENTRATED_W2, CONCENTRATED_H1),
            new Pair<>(CONCENTRATED_W2, CONCENTRATED_H2),
            new Pair<>(CONCENTRATED_W3, CONCENTRATED_H2),
            new Pair<>(VELDSPAR_W1, VELDSPAR_H1),
            new Pair<>(VELDSPAR_W2, VELDSPAR_H1),
            new Pair<>(VELDSPAR_W3, VELDSPAR_H2));

    private final List<Pair<Integer, Integer>> condensedScorditeList = Arrays.asList(
            new Pair<>(CONDENSED_W1, CONDENSED_H1),
            new Pair<>(CONDENSED_W2, CONDENSED_H1));
    private static final int CONDENSED_W1 = 62;
    private static final int CONDENSED_W2 = 61;
    private static final int CONDENSED_H1 = 9;

    private final List<Pair<Integer, Integer>> scorditeList = Arrays.asList(
            new Pair<>(SCORDITE_W1, SCORDITE_H1),
            new Pair<>(SCORDITE_W1, SCORDITE_H2),
            new Pair<>(SCORDITE_W2, SCORDITE_H2));
    private static final int SCORDITE_W1 = 42;
    private static final int SCORDITE_W2 = 43;
    private static final int SCORDITE_H1 = 9;
    private static final int SCORDITE_H2 = 10;

    private final List<Pair<Integer, Integer>> denseVeldsparList = Arrays.asList(
            new Pair<>(DENSE_W1, DENSE_H1),
            new Pair<>(DENSE_W2, DENSE_H1));
    private static final int DENSE_W1 = 34;
    private static final int DENSE_W2 = 33;
    private static final int DENSE_H1 = 9;

    private final List<Pair<Integer, Integer>> concentratedVeldsparList = Arrays.asList(
            new Pair<>(CONCENTRATED_W1, CONCENTRATED_H1),
            new Pair<>(CONCENTRATED_W2, CONCENTRATED_H1),
            new Pair<>(CONCENTRATED_W2, CONCENTRATED_H2),
            new Pair<>(CONCENTRATED_W3, CONCENTRATED_H2));
    private static final int CONCENTRATED_W1 = 73;
    private static final int CONCENTRATED_W2 = 72;
    private static final int CONCENTRATED_W3 = 71;
    private static final int CONCENTRATED_H1 = 10;
    private static final int CONCENTRATED_H2 = 9;

    private final List<Pair<Integer, Integer>> veldsparList = Arrays.asList(
            new Pair<>(VELDSPAR_W1, VELDSPAR_H1),
            new Pair<>(VELDSPAR_W2, VELDSPAR_H1),
            new Pair<>(VELDSPAR_W3, VELDSPAR_H2));
    private static final int VELDSPAR_W1 = 50;
    private static final int VELDSPAR_W2 = 49;
    private static final int VELDSPAR_W3 = 48;
    private static final int VELDSPAR_H1 = 13;
    private static final int VELDSPAR_H2 = 12;
    /*
     * *****************************************************************************
     * This find where the floating overview->mining window is in the display
     */
    private static final int OVERVIEWMINING_X1 = 1230;
    private static final int OVERVIEWMINING_X2_W = 1919;
    private static final int OVERVIEWMINING_Y1 = 251;
    private static final int OVERVIEWMINING_Y2_H = 1079;
    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> lockTargetList = Arrays.asList(
            new Pair<>(LOCKTARGET_W1, LOCKTARGET_H1));
    private static final int LOCKTARGET_W1 = 26;
    private static final int LOCKTARGET_H1 = 26;
    /* This find where the button LOCK TARGET OF SELECTED ITEM WINDOW is in the display */
    private final Quartet<Integer, Integer, Integer, Integer> lockTargetDeadZoneTuple
            = new Quartet<>(LOCKTARGET_DEADZONE_X1, LOCKTARGET_DEADZONE_X2_W, LOCKTARGET_DEADZONE_Y1, LOCKTARGET_DEADZONE_Y2_H);
    private static final int LOCKTARGET_DEADZONE_X1 = 1000;
    private static final int LOCKTARGET_DEADZONE_X2_W = 1919;
    private static final int LOCKTARGET_DEADZONE_Y1 = 0;
    private static final int LOCKTARGET_DEADZONE_Y2_H = 200;
    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> maxCargoList = Arrays.asList(
            new Pair<>(MAXCARGO1_W1, MAXCARGO1_H1),
            new Pair<>(MAXCARGO1_W2, MAXCARGO1_H1));
    private static final int MAXCARGO1_W1 = 111;
    private static final int MAXCARGO1_W2 = 110;
    private static final int MAXCARGO1_H1 = 24;

    private final List<Pair<Integer, Integer>> minCargoList = Arrays.asList(
            new Pair<>(MINGCARGO_WITHM3_W1, MINGCARGO_H1),
            new Pair<>(MINGCARGO_WITHOUTM3_W1, MINGCARGO_H1),
            new Pair<>(MINGCARGO_WITHOUTM3_W1, MINGCARGO_H2));
    private static final int MINGCARGO_WITHM3_W1 = 72;
    private static final int MINGCARGO_WITHOUTM3_W1 = 51;
    private static final int MINGCARGO_H1 = 9;
    private static final int MINGCARGO_H2 = 10;
    /*
     * *****************************************************************************
     * This find where the rectangle of MAXCARGO and ITEM (ITEM HANGAR) is in
     * the display in STATION
     */
    private final Quartet<Integer, Integer, Integer, Integer> inventoryDeadzoneTuple
            = new Quartet<>(INVENTORY_DEADZONE_X1, INVENTORY_DEADZONE_X2_W, INVENTORY_DEADZONE_Y1, INVENTORY_DEADZONE_Y2_H);
    private static final int INVENTORY_DEADZONE_X1 = 70;
    private static final int INVENTORY_DEADZONE_X2_W = 570;
    private static final int INVENTORY_DEADZONE_Y1 = 40;
    private static final int INVENTORY_DEADZONE_Y2_H = 1040;
    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> compactMaxCargoList = Arrays.asList(
            new Pair<>(COMPACTMAXCARGO_W1, COMPACTMAXCARGO_H1),
            new Pair<>(COMPACTMAXCARGO_W2, COMPACTMAXCARGO_H1),
            new Pair<>(COMPACTMAXCARGO_W3, COMPACTMAXCARGO_H1),
            new Pair<>(COMPACTMAXCARGO_W4, COMPACTMAXCARGO_H1));
    private static final int COMPACTMAXCARGO_W1 = 331;
    private static final int COMPACTMAXCARGO_W2 = 332;
    private static final int COMPACTMAXCARGO_W3 = 345;
    private static final int COMPACTMAXCARGO_W4 = 346;
    private static final int COMPACTMAXCARGO_H1 = 5;
    /*
     * *****************************************************************************
     */
    private static final int APPROACHING_X1 = 899;
    private static final int APPROACHING_Y1 = 787;
    private static final int APPROACHING_W1 = 121;
    private static final int APPROACHING_H1 = 14;
    /*
     * *****************************************************************************
     * Drag itens deadzone
     */
    private final Quartet<Integer, Integer, Integer, Integer> dragItensDeadZoneList
            = new Quartet<>(DRAGITENS_DEADZONE_X1, DRAGITENS_DEADZONE_X2_W, DRAGITENS_DEADZONE_Y1, DRAGITENS_DEADZONE_Y2_H);
    private static final int DRAGITENS_DEADZONE_X1 = 245;
    private static final int DRAGITENS_DEADZONE_X2_W = 530;
    private static final int DRAGITENS_DEADZONE_Y1 = 185;
    private static final int DRAGITENS_DEADZONE_Y2_H = 985;
    /*
     * *****************************************************************************
     */
    private final List<Pair<Integer, Integer>> undockButtonList = Arrays.asList(
            new Pair<>(UNDOCK_BUTTON_W1, UNDOCK_BUTTON_H1),
            new Pair<>(UNDOCK_BUTTON_W2, UNDOCK_BUTTON_H1));
    private static final int UNDOCK_BUTTON_W1 = 235;
    private static final int UNDOCK_BUTTON_W2 = 234;
    private static final int UNDOCK_BUTTON_H1 = 40;
    private final Quartet<Integer, Integer, Integer, Integer> undockDeadZoneTuple
            = new Quartet<>(UNDOCK_DEADZONE_X1, UNDOCK_DEADZONE_X2_W, UNDOCK_DEADZONE_Y1, UNDOCK_DEADZONE_Y2_H);
    private static final int UNDOCK_DEADZONE_X1 = 571;
    private static final int UNDOCK_DEADZONE_X2_W = 1919;
    private static final int UNDOCK_DEADZONE_Y1 = 0;
    private static final int UNDOCK_DEADZONE_Y2_H = 1079;
    /*
     * *****************************************************************************
     */
    private final Quartet<Integer, Integer, Integer, Integer> compactMaxCargoDeadZoneTuple
            = new Quartet<>(COMPACTCARGO_DEADZONE_X1, COMPACTCARGO_DEADZONE_X2_W, COMPACTCARGO_DEADZONE_Y1, COMPACTCARGO_DEADZONE_Y2_H);
    private static final int COMPACTCARGO_DEADZONE_X1 = 30;
    private static final int COMPACTCARGO_DEADZONE_X2_W = 444;
    private static final int COMPACTCARGO_DEADZONE_Y1 = 0;
    private static final int COMPACTCARGO_DEADZONE_Y2_H = 540;
    /*
     * *****************************************************************************
     */
    private static final int CHECKPATH_X1 = 760;
    private static final int CHECKPATH_Y1 = 765;
    private static final int CHECKPATH_W1 = 400;
    private static final int CHECKPATH_H1 = 100;
    //x1 = x; x2 = x + w; y1 = y; y2 = y + h.
    /*
     * *****************************************************************************
     */
    private static final int CANNON_W1 = 45;
    private static final int CANNON_H1 = 46;

    private static final int F1CANNON1_X = 1042;
    private static final int F2CANNON2_X = F1CANNON1_X + 51;
    private static final int FNCANNON_Y = 911;

    /*
     * *****************************************************************************
     */
    public List<Pair<Integer, Integer>> getInvalidTargetList() {
        return invalidTargetList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getInvalidTargetDeadZoneList() {
        return invalidTargetDeadZoneList;
    }

    public List<Pair<Integer, Integer>> getMiningBotList() {
        return miningBotList;
    }

    public List<Pair<Integer, Integer>> getHomeStationList() {
        return homeStationList;
    }

    public List<Pair<Integer, Integer>> getWarpList() {
        return warpList;
    }

    public List<Pair<Integer, Integer>> getDockList() {
        return dockList;
    }

    public List<Pair<Integer, Integer>> getHangarList() {
        return hangarList;
    }

    public List<Pair<Integer, Integer>> getCloseLocationButtonList() {
        return closeLocationButtonList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getLocationTabDeadZoneTuple() {
        return locationTabDeadZoneTuple;
    }

    public List<Pair<Integer, Integer>> getAllAsteroidsList() {
        return allAsteroidsList;
    }

    public List<Pair<Integer, Integer>> getCondensedScorditeList() {
        return condensedScorditeList;
    }

    public List<Pair<Integer, Integer>> getScorditeList() {
        return scorditeList;
    }

    public List<Pair<Integer, Integer>> getDenseVeldsparList() {
        return denseVeldsparList;
    }

    public List<Pair<Integer, Integer>> getConcentratedVeldsparList() {
        return concentratedVeldsparList;
    }

    public List<Pair<Integer, Integer>> getVeldsparList() {
        return veldsparList;
    }

    public List<Pair<Integer, Integer>> getLockTargetList() {
        return lockTargetList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getLockTargetDeadZoneTuple() {
        return lockTargetDeadZoneTuple;
    }

    public List<Pair<Integer, Integer>> getMaxCargoList() {
        return maxCargoList;
    }

    public List<Pair<Integer, Integer>> getMinCargoList() {
        return minCargoList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getInventoryDeadzoneTuple() {
        return inventoryDeadzoneTuple;
    }

    public List<Pair<Integer, Integer>> getCompactMaxCargoList() {
        return compactMaxCargoList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getDragItensDeadZoneList() {
        return dragItensDeadZoneList;
    }

    public List<Pair<Integer, Integer>> getUndockButtonList() {
        return undockButtonList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getUndockDeadZoneTuple() {
        return undockDeadZoneTuple;
    }

    public Quartet<Integer, Integer, Integer, Integer> getCompactMaxCargoDeadZoneTuple() {
        return compactMaxCargoDeadZoneTuple;
    }

    public static int getOVERVIEWMINING_X1() {
        return OVERVIEWMINING_X1;
    }

    public static int getOVERVIEWMINING_X2_W() {
        return OVERVIEWMINING_X2_W;
    }

    public static int getOVERVIEWMINING_Y1() {
        return OVERVIEWMINING_Y1;
    }

    public static int getOVERVIEWMINING_Y2_H() {
        return OVERVIEWMINING_Y2_H;
    }

    public static int getAPPROACHING_W1() {
        return APPROACHING_W1;
    }

    public static int getAPPROACHING_H1() {
        return APPROACHING_H1;
    }

    public static int getAPPROACHING_X1() {
        return APPROACHING_X1;
    }

    public static int getAPPROACHING_Y1() {
        return APPROACHING_Y1;
    }

    public static int getCANNON_W1() {
        return CANNON_W1;
    }

    public static int getCANNON_H1() {
        return CANNON_H1;
    }

    public static int getBEINGATTACKED_X1() {
        return BEINGATTACKED_X1;
    }

    public static int getBEINGATTACKED_W1() {
        return BEINGATTACKED_W1;
    }

    public static int getBEINGATTACKED_Y1() {
        return BEINGATTACKED_Y1;
    }

    public static int getBEINGATTACKED_H1() {
        return BEINGATTACKED_H1;
    }

    public static int getF1CANNON1_X() {
        return F1CANNON1_X;
    }

    public static int getF2CANNON2_X() {
        return F2CANNON2_X;
    }

    public static int getFNCANNON_Y() {
        return FNCANNON_Y;
    }

    public static int getCHECKPATH_X1() {
        return CHECKPATH_X1;
    }

    public static int getCHECKPATH_W1() {
        return CHECKPATH_W1;
    }

    public static int getCHECKPATH_Y1() {
        return CHECKPATH_Y1;
    }

    public static int getCHECKPATH_H1() {
        return CHECKPATH_H1;
    }

    public List<Pair<Integer, Integer>> getAstBeltIList() {
        return AstBeltIList;
    }

    public List<Pair<Integer, Integer>> getAstBeltIIList() {
        return AstBeltIIList;
    }

    public List<Pair<Integer, Integer>> getAstBeltIIIList() {
        return AstBeltIIIList;
    }

    public List<Pair<Integer, Integer>> getAstBeltIIIIList() {
        return AstBeltIIIIList;
    }

    public List<Pair<Integer, Integer>> getAstBeltIIIIIList() {
        return AstBeltIIIIIList;
    }

    public Map<Integer, List<Pair<Integer, Integer>>> getAstBeltsMap() {
        return astBeltsMap;
    }
}
