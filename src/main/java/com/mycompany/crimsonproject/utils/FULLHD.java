package com.mycompany.crimsonproject.utils;

import java.util.Arrays;
import java.util.List;
import org.javatuples.Pair;
import org.javatuples.Quartet;

/**
 * -> This classe uses font size SMALL of Eve Online
 *
 * @author Devmachine BLOCKSCREEN calculator: x1 = x; x2 = x + w; y1 = y; y2 = y
 * + h.
 *
 * BLOSCKSCREEN calculator with DEADZONE: x1 = x; x2 = x + w + DEADZONE; y1 = y;
 * y2 = y + h + DEADZONE.
 *
 * BLOCKSCREEN: Rect limitation where to find the recognized rects DEADZONE:
 * Pixel's amount to add in the BLOCKSCREEN for caution or not
 */
public class Fullhd {

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

    public static int getAPPROACHING_H3() {
        return APPROACHING_H3;
    }

    public static int getAPPROACHING_X() {
        return APPROACHING_X;
    }

    public static int getAPPROACHING_Y() {
        return APPROACHING_Y;
    }

    public static int getVENTURECANNONS_Y() {
        return VENTURECANNONS_Y;
    }

    public static int getRANGEDCANNON_W1() {
        return RANGEDCANNON_W1;
    }

    public static int getRANGEDCANNON_H1() {
        return RANGEDCANNON_H1;
    }

    public static int getRANGEDCANNON1_X() {
        return RANGEDCANNON1_X;
    }

    public static int getRANGEDCANNON2_X() {
        return RANGEDCANNON2_X;
    }

    public static int getRANGEDCANNONS_Y() {
        return RANGEDCANNONS_Y;
    }

    public static int getVENTURECANNON_W1() {
        return VENTURECANNON_W1;
    }

    public static int getVENTURECANNON_H1() {
        return VENTURECANNON_H1;
    }

    public static int getVENTURECANNON1_X() {
        return VENTURECANNON1_X;
    }

    public static int getVENTURECANNON2_X() {
        return VENTURECANNON2_X;
    }

    private final List<Pair<Integer, Integer>> invalidTargetList = Arrays.asList(
            new Pair<>(INVALIDTARGET_W1, INVALIDTARGET_H1));
    private static final int INVALIDTARGET_W1 = 106;
    private static final int INVALIDTARGET_H1 = 17;

    private final Quartet<Integer, Integer, Integer, Integer> invalidTargetDeadZoneList
            = new Quartet<>(INVALIDTARGET_DEADZONE_X1, INVALIDTARGET_DEADZONE_X2_W, INVALIDTARGET_DEADZONE_Y1, INVALIDTARGET_DEADZONE_Y2_H);
    private static final int INVALIDTARGET_DEADZONE_X1 = 751;
    private static final int INVALIDTARGET_DEADZONE_X2_W = 951;
    private static final int INVALIDTARGET_DEADZONE_Y1 = 420;
    private static final int INVALIDTARGET_DEADZONE_Y2_H = 470;

    private final List<Pair<Integer, Integer>> miningBotWxH = Arrays.asList(
            new Pair<>(MININGBOT1_W1, MININGBOT1_H1),
            new Pair<>(MININGBOT1_W2, MININGBOT1_H1));
    private static final int MININGBOT1_W1 = 60;
    private static final int MININGBOT1_W2 = 59;
    private static final int MININGBOT1_H1 = 9;

    private final List<Pair<Integer, Integer>> homeStationWxHlist = Arrays.asList(
            new Pair<>(HOMESTATION1_W1, HOMESTATION1_H1),
            new Pair<>(HOMESTATION1_W2, HOMESTATION1_H1));
    private static final int HOMESTATION1_W1 = 75;
    private static final int HOMESTATION1_W2 = 74;
    private static final int HOMESTATION1_H1 = 9;

    private final List<Pair<Integer, Integer>> warpWxHlist = Arrays.asList(
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

    private final List<Pair<Integer, Integer>> dockWxHlist = Arrays.asList(
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

    private final List<Pair<Integer, Integer>> hangarWxHlist = Arrays.asList(
            new Pair<>(HANGAR_W1, HANGAR_H1),
            new Pair<>(HANGAR_W1, HANGAR_H2));
    private static final int HANGAR_W1 = 40;
    private static final int HANGAR_H1 = 12;
    private static final int HANGAR_H2 = 11;

    private final List<Pair<Integer, Integer>> closeLocationButtonWxHlist = Arrays.asList(
            new Pair<>(CLOSELOCATIONBUTTON_W1, CLOSELOCATIONBUTTON_H1));
    private static final int CLOSELOCATIONBUTTON_W1 = 10;
    private static final int CLOSELOCATIONBUTTON_H1 = 10;

    private final Quartet<Integer, Integer, Integer, Integer> tupleLocationTabDeadZone
            = new Quartet<>(LOCATIONTAB_DEADZONE_X1, LOCATIONTAB_DEADZONE_X2_W, LOCATIONTAB_DEADZONE_Y1, LOCATIONTAB_DEADZONE_Y2_H);
    private static final int LOCATIONTAB_DEADZONE_X1 = 445;
    private static final int LOCATIONTAB_DEADZONE_X2_W = 889;
    private static final int LOCATIONTAB_DEADZONE_Y1 = 0;
    private static final int LOCATIONTAB_DEADZONE_Y2_H = 540;

    /* ------------------------------------------------------------- */
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

    // Priority 1: (62, 9); (61, 9)
    private final List<Pair<Integer, Integer>> condensedScorditeList = Arrays.asList(
            new Pair<>(CONDENSED_W1, CONDENSED_H1),
            new Pair<>(CONDENSED_W2, CONDENSED_H1));
    private static final int CONDENSED_W1 = 62;
    private static final int CONDENSED_W2 = 61;
    private static final int CONDENSED_H1 = 9;

    // Priority 2: (42,9); (42,10)
    private final List<Pair<Integer, Integer>> listScordite = Arrays.asList(
            new Pair<>(SCORDITE_W1, SCORDITE_H1),
            new Pair<>(SCORDITE_W1, SCORDITE_H2));
    private static final int SCORDITE_W1 = 42;
    private static final int SCORDITE_H1 = 9;
    private static final int SCORDITE_H2 = 10;

    // Priotity 3: (34, 9); (33, 9)
    private final List<Pair<Integer, Integer>> denseVeldsparList = Arrays.asList(
            new Pair<>(DENSE_W1, DENSE_H1),
            new Pair<>(DENSE_W2, DENSE_H1));
    private static final int DENSE_W1 = 34;
    private static final int DENSE_W2 = 33;
    private static final int DENSE_H1 = 9;

    // Priority 4: (73, 10); (72, 10); (72, 9); (71, 9)
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

    // Priority 5: (50, 13); (49, 13); (48, 12)
    private final List<Pair<Integer, Integer>> veldsparList = Arrays.asList(
            new Pair<>(VELDSPAR_W1, VELDSPAR_H1),
            new Pair<>(VELDSPAR_W2, VELDSPAR_H1),
            new Pair<>(VELDSPAR_W3, VELDSPAR_H2));
    private static final int VELDSPAR_W1 = 50;
    private static final int VELDSPAR_W2 = 49;
    private static final int VELDSPAR_W3 = 48;
    private static final int VELDSPAR_H1 = 13;
    private static final int VELDSPAR_H2 = 12;
    // This find where the floating overview->mining window is in the display
    private static final int OVERVIEWMINING_X1 = 1230;
    private static final int OVERVIEWMINING_X2_W = 1919;
    private static final int OVERVIEWMINING_Y1 = 251;
    private static final int OVERVIEWMINING_Y2_H = 1079;
    /* ------------------------------------------------------------- */

    private final List<Pair<Integer, Integer>> lockTargetList = Arrays.asList(
            new Pair<>(LOCKTARGET_W1, LOCKTARGET_H1));
    private static final int LOCKTARGET_W1 = 26;
    private static final int LOCKTARGET_H1 = 26;
    /* This find where the button LOCK TARGET OF SELECTED ITEM WINDOW is in the display */
    private final Quartet<Integer, Integer, Integer, Integer> tupleLockTargetDeadZone
            = new Quartet<>(LOCKTARGET_DEADZONE_X1, LOCKTARGET_DEADZONE_X2_W, LOCKTARGET_DEADZONE_Y1, LOCKTARGET_DEADZONE_Y2_H);
    private static final int LOCKTARGET_DEADZONE_X1 = 1000;
    private static final int LOCKTARGET_DEADZONE_X2_W = 1919;
    private static final int LOCKTARGET_DEADZONE_Y1 = 0;
    private static final int LOCKTARGET_DEADZONE_Y2_H = 200;
    /* ------------------------------------------------------------- */

    private final List<Pair<Integer, Integer>> maxCargoWxHList = Arrays.asList(
            new Pair<>(MAXCARGO1_W1, MAXCARGO1_H1),
            new Pair<>(MAXCARGO1_W2, MAXCARGO1_H1));
    private static final int MAXCARGO1_W1 = 111;
    private static final int MAXCARGO1_W2 = 110;
    private static final int MAXCARGO1_H1 = 24;

    private final List<Pair<Integer, Integer>> minCargoWxHList = Arrays.asList(
            new Pair<>(MINGCARGO_WITHM3_W1, MINGCARGO_H1),
            new Pair<>(MINGCARGO_WITHOUTM3_W1, MINGCARGO_H1),
            new Pair<>(MINGCARGO_WITHOUTM3_W1, MINGCARGO_H2));
    private static final int MINGCARGO_WITHM3_W1 = 72;
    private static final int MINGCARGO_WITHOUTM3_W1 = 51;
    private static final int MINGCARGO_H1 = 9;
    private static final int MINGCARGO_H2 = 10;
    /* ------------------------------------------------------------- */
    // This find where the rectangle of MAXCARGO and ITEM (ITEM HANGAR) is in the display in STATION
    private final Quartet<Integer, Integer, Integer, Integer> inventoryDeadzone
            = new Quartet<>(INVENTORY_DEADZONE_X1, INVENTORY_DEADZONE_X2_W, INVENTORY_DEADZONE_Y1, INVENTORY_DEADZONE_Y2_H);
    private static final int INVENTORY_DEADZONE_X1 = 70;
    private static final int INVENTORY_DEADZONE_X2_W = 570;
    private static final int INVENTORY_DEADZONE_Y1 = 40;
    private static final int INVENTORY_DEADZONE_Y2_H = 1040;
    /* ------------------------------------------------------------- */

    private final List<Pair<Integer, Integer>> compactMaxCargoList = Arrays.asList(
            new Pair<>(COMPACTMAXCARGO_W1, COMPACTMAXCARGO_H1));
    private static final int COMPACTMAXCARGO_W1 = 331;
    private static final int COMPACTMAXCARGO_H1 = 5;
    /* ------------------------------------------------------------- */

    // (122, 16); (121, 14); (121, 25)
    private static final int APPROACHING_W1 = 121;
    private static final int APPROACHING_H3 = 14;
    private static final int APPROACHING_X = 899;
    private static final int APPROACHING_Y = 787;
    /* ------------------------------------------------------------- */

    // Drag itens deadzone
    private final Quartet<Integer, Integer, Integer, Integer> dragItensDeadZoneList
            = new Quartet<>(DRAGITENS_DEADZONE_X1, DRAGITENS_DEADZONE_X2_W, DRAGITENS_DEADZONE_Y1, DRAGITENS_DEADZONE_Y2_H);
    private static final int DRAGITENS_DEADZONE_X1 = 245;
    private static final int DRAGITENS_DEADZONE_X2_W = 530;
    private static final int DRAGITENS_DEADZONE_Y1 = 185;
    private static final int DRAGITENS_DEADZONE_Y2_H = 985;
    /* ------------------------------------------------------------- */

    private final List<Pair<Integer, Integer>> undockButtonWxHlist = Arrays.asList(
            new Pair<>(UNDOCK_BUTTON_W1, UNDOCK_BUTTON_H1),
            new Pair<>(UNDOCK_BUTTON_W2, UNDOCK_BUTTON_H1));
    private static final int UNDOCK_BUTTON_W1 = 235;
    private static final int UNDOCK_BUTTON_W2 = 234;
    private static final int UNDOCK_BUTTON_H1 = 40;
    /* This find where the word Approaching is in the display */
    private final Quartet<Integer, Integer, Integer, Integer> undockDeadZone
            = new Quartet<>(UNDOCK_DEADZONE_X1, UNDOCK_DEADZONE_X2_W, UNDOCK_DEADZONE_Y1, UNDOCK_DEADZONE_Y2_H);
    private static final int UNDOCK_DEADZONE_X1 = 571;
    private static final int UNDOCK_DEADZONE_X2_W = 1919;
    private static final int UNDOCK_DEADZONE_Y1 = 0;
    private static final int UNDOCK_DEADZONE_Y2_H = 1079;
    /* ------------------------------------------------------------- */

    private final Quartet<Integer, Integer, Integer, Integer> compactMaxCargoDeadZone
            = new Quartet<>(COMPACTCARGO_DEADZONE_X1, COMPACTCARGO_DEADZONE_X2_W, COMPACTCARGO_DEADZONE_Y1, COMPACTCARGO_DEADZONE_Y2_H);
    private static final int COMPACTCARGO_DEADZONE_X1 = 30;
    private static final int COMPACTCARGO_DEADZONE_X2_W = 444;
    private static final int COMPACTCARGO_DEADZONE_Y1 = 0;
    private static final int COMPACTCARGO_DEADZONE_Y2_H = 540;
    /* ------------------------------------------------------------- */

    private final List<Pair<Integer, Integer>> ventureCannonWxHlist = Arrays.asList(
            new Pair<>(VENTURECANNON_W1, VENTURECANNON_H1));
    private static final int VENTURECANNON_W1 = 42;
    private static final int VENTURECANNON_H1 = 40;
    private final List<Pair<Integer, Integer>> ventureXcannonCoordinates = Arrays.asList(
            new Pair<>(VENTURECANNON1_X, VENTURECANNON2_X));
    private static final int VENTURECANNON1_X = 1044;
    private static final int VENTURECANNON2_X = 1095;
    private static final int VENTURECANNONS_Y = 910;
    /* ------------------------------------------------------------- */
    
    private static final int RANGEDCANNON_W1 = 27;
    private static final int RANGEDCANNON_H1 = 12;
    private static final int RANGEDCANNON1_X = 1049;
    private static final int RANGEDCANNON2_X = 1100;
    private static final int RANGEDCANNONS_Y = 925;

    public List<Pair<Integer, Integer>> getInvalidTargetList() {
        return invalidTargetList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getInvalidTargetDeadZoneList() {
        return invalidTargetDeadZoneList;
    }

    public List<Pair<Integer, Integer>> getMiningBotWxH() {
        return miningBotWxH;
    }

    public List<Pair<Integer, Integer>> getHomeStationWxHlist() {
        return homeStationWxHlist;
    }

    public List<Pair<Integer, Integer>> getWarpWxHlist() {
        return warpWxHlist;
    }

    public List<Pair<Integer, Integer>> getDockWxHlist() {
        return dockWxHlist;
    }

    public List<Pair<Integer, Integer>> getHangarWxHlist() {
        return hangarWxHlist;
    }

    public List<Pair<Integer, Integer>> getCloseLocationButtonWxHlist() {
        return closeLocationButtonWxHlist;
    }

    public Quartet<Integer, Integer, Integer, Integer> getTupleLocationTabDeadZone() {
        return tupleLocationTabDeadZone;
    }

    public List<Pair<Integer, Integer>> getAllAsteroidsList() {
        return allAsteroidsList;
    }

    public List<Pair<Integer, Integer>> getCondensedScorditeList() {
        return condensedScorditeList;
    }

    public List<Pair<Integer, Integer>> getListScordite() {
        return listScordite;
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

    public Quartet<Integer, Integer, Integer, Integer> getTupleLockTargetDeadZone() {
        return tupleLockTargetDeadZone;
    }

    public List<Pair<Integer, Integer>> getMaxCargoWxHList() {
        return maxCargoWxHList;
    }

    public List<Pair<Integer, Integer>> getMinCargoWxHList() {
        return minCargoWxHList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getInventoryDeadzone() {
        return inventoryDeadzone;
    }

    public List<Pair<Integer, Integer>> getCompactMaxCargoList() {
        return compactMaxCargoList;
    }

    public Quartet<Integer, Integer, Integer, Integer> getDragItensDeadZoneList() {
        return dragItensDeadZoneList;
    }

    public List<Pair<Integer, Integer>> getUndockButtonWxHlist() {
        return undockButtonWxHlist;
    }

    public Quartet<Integer, Integer, Integer, Integer> getUndockDeadZone() {
        return undockDeadZone;
    }

    public Quartet<Integer, Integer, Integer, Integer> getCompactMaxCargoDeadZone() {
        return compactMaxCargoDeadZone;
    }

    public List<Pair<Integer, Integer>> getVentureXcannonCoordinates() {
        return ventureXcannonCoordinates;
    }

    public List<Pair<Integer, Integer>> getVentureCannonWxHlist() {
        return ventureCannonWxHlist;
    }
    
    
    
    
    
    

}
/**
 *
 * @author Devmachine BLOCKSCREEN calculator: x1 = x; x2 = x + w; y1 = y; y2 = y
 * + h.
 *
 */
