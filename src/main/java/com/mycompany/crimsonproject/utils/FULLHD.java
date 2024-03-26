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
public class FULLHD {

    public final List<Pair<Integer, Integer>> listMiningBotWxH = Arrays.asList(new Pair<>(MININGBOT1_W1, MININGBOT1_H1), new Pair<>(MININGBOT1_W2, MININGBOT1_H1));
    public static final int MININGBOT1_W1 = 60;
    public static final int MININGBOT1_W2 = 59;
    public static final int MININGBOT1_H1 = 9; 

    public final List<Pair<Integer, Integer>> listHomeStationWxH = Arrays.asList(new Pair<>(HOMESTATION1_W1, HOMESTATION1_H1), new Pair<>(HOMESTATION1_W2, HOMESTATION1_H1));
    public static final int HOMESTATION1_W1 = 75;
    public static final int HOMESTATION1_W2 = 74;
    public static final int HOMESTATION1_H1 = 9;

    public final List<Pair<Integer, Integer>> listWithinWxH = Arrays.asList(new Pair<>(WITHIN_W1, WITHIN_H1));
    public static final int WITHIN_W1 = 31;
    public static final int WITHIN_H1 = 9;
    
    public final List<Pair<Integer, Integer>> listDockWxH = Arrays.asList(new Pair<>(DOCK_W1, DOCK_H1));
    public static final int DOCK_W1 = 23;
    public static final int DOCK_H1 = 9;

    public final List<Pair<Integer, Integer>> listHangarWxH = Arrays.asList(new Pair<>(HANGAR_W1, HANGAR_H1), new Pair<>(HANGAR_W1, HANGAR_H2));
    public static final int HANGAR_W1 = 40;
    public static final int HANGAR_H1 = 12;
    public static final int HANGAR_H2 = 11;

    public final List<Pair<Integer, Integer>> listCloseLocationButtonWxH = Arrays.asList(new Pair<>(CLOSELOCATIONBUTTON_W1, CLOSELOCATIONBUTTON_H1));
    public static final int CLOSELOCATIONBUTTON_W1 = 10;
    public static final int CLOSELOCATIONBUTTON_H1 = 10;

    public final Quartet<Integer, Integer, Integer, Integer> tupleLocationTabDeadZone
            = new Quartet<>(LOCATIONTAB_DEADZONE_X1, LOCATIONTAB_DEADZONE_X2_W, LOCATIONTAB_DEADZONE_Y1, LOCATIONTAB_DEADZONE_Y2_H);
    public static final int LOCATIONTAB_DEADZONE_X1 = 445;
    public static final int LOCATIONTAB_DEADZONE_X2_W = 889;
    public static final int LOCATIONTAB_DEADZONE_Y1 = 0;
    public static final int LOCATIONTAB_DEADZONE_Y2_H = 540;
    /* ------------------------------------------------------------- */

    // Priority 1: (62, 9); (61, 9)
    public static final int CONDENSED_W1 = 62;
    public static final int CONDENSED_W2 = 61;
    public static final int CONDENSED_H1 = 9;

    // Priority 2: (42,9); (42,10)
    public static final int SCORDITE_W1 = 42;
    public static final int SCORDITE_H1 = 9;
    public static final int SCORDITE_H2 = 10;

    // Priotity 3: (34, 9); (33, 9)
    public static final int DENSE_W1 = 34;
    public static final int DENSE_W2 = 33;
    public static final int DENSE_H1 = 9;

    // Priority 4: (73, 10); (72, 10); (72, 9); (71, 9)
    public static final int CONCENTRATED_W1 = 73;
    public static final int CONCENTRATED_W2 = 72;
    public static final int CONCENTRATED_W3 = 71;
    public static final int CONCENTRATED_H1 = 10;
    public static final int CONCENTRATED_H2 = 9;

    // Priority 5: (50, 13); (49, 13); (48, 12)
    public static final int VELDSPAR_W1 = 50;
    public static final int VELDSPAR_W2 = 49;
    public static final int VELDSPAR_W3 = 48;
    public static final int VELDSPAR_H1 = 13;
    public static final int VELDSPAR_H2 = 12;
    // This find where the floating overview->mining window is in the display
    public static final int OVERVIEWMINING_X1 = 1230;
    public static final int OVERVIEWMINING_X2_W = 1919;
    public static final int OVERVIEWMINING_Y1 = 251;
    public static final int OVERVIEWMINING_Y2_H = 1079;
    /* ------------------------------------------------------------- */

    public static final int LOCKTARGET_W1 = 26;
    public static final int LOCKTARGET_H1 = 26;
    /* This find where the button LOCK TARGET OF SELECTED ITEM WINDOW is in the display */
    public static final int LOCKTARGET_DEADZONE_X1 = 1000;
    public static final int LOCKTARGET_DEADZONE_X2_W = 1919;
    public static final int LOCKTARGET_DEADZONE_Y1 = 0;
    public static final int LOCKTARGET_DEADZONE_Y2_H = 200;
    /* ------------------------------------------------------------- */

    public final List<Pair<Integer, Integer>> listMaxCargoWxH = Arrays.asList(new Pair<>(MAXCARGO1_W1, MAXCARGO1_H1), new Pair<>(MAXCARGO1_W2, MAXCARGO1_H1));
    public static final int MAXCARGO1_W1 = 111;
    public static final int MAXCARGO1_W2 = 110;
    public static final int MAXCARGO1_H1 = 24;

    public final List<Pair<Integer, Integer>> listMinCargoWxH = Arrays.asList(new Pair<>(MINGCARGO_WITHM3_W1, MINGCARGO_H1), new Pair<>(MINGCARGO_WITHOUTM3_W1, MINGCARGO_H1));
    public static final int MINGCARGO_WITHM3_W1 = 72;
    public static final int MINGCARGO_WITHOUTM3_W1 = 51;
    public static final int MINGCARGO_H1 = 9;
    /* ------------------------------------------------------------- */
    // This find where the rectangle of MAXCARGO and ITEM (ITEM HANGAR) is in the display in STATION
    public final Quartet<Integer, Integer, Integer, Integer> tupleInvetoryDeadzone
            = new Quartet<>(INVENTORY_DEADZONE_X1, INVENTORY_DEADZONE_X2_W, INVENTORY_DEADZONE_Y1, INVENTORY_DEADZONE_Y2_H);
    public static final int INVENTORY_DEADZONE_X1 = 70;
    public static final int INVENTORY_DEADZONE_X2_W = 570;
    public static final int INVENTORY_DEADZONE_Y1 = 40;
    public static final int INVENTORY_DEADZONE_Y2_H = 1040;
    /* ------------------------------------------------------------- */

    public static final int COMPACTMAXCARGO_W1 = 339;
    public static final int COMPACTMAXCARGO_W2 = 419;
    public static final int COMPACTMAXCARGO_W3 = 331; //!!
    public static final int COMPACTMAXCARGO_H1 = 5;
    /* ------------------------------------------------------------- */

    // (122, 16); (121, 14); (121, 25)
    public static final int APPROACHING_W1 = 121;
    public static final int APPROACHING_H3 = 14;
    public static final int APPROACHING_X = 899;
    public static final int APPROACHING_Y = 787;
    /* ------------------------------------------------------------- */

    // Drag itens deadzone
    public final Quartet<Integer, Integer, Integer, Integer> tupleDragItensDeadZone
            = new Quartet<>(DRAGITENS_DEADZONE_X1, DRAGITENS_DEADZONE_X2_W, DRAGITENS_DEADZONE_Y1, DRAGITENS_DEADZONE_Y2_H);
    public static final int DRAGITENS_DEADZONE_X1 = 245;
    public static final int DRAGITENS_DEADZONE_X2_W = 530;
    public static final int DRAGITENS_DEADZONE_Y1 = 185;
    public static final int DRAGITENS_DEADZONE_Y2_H = 985;
    /* ------------------------------------------------------------- */

    public final List<Pair<Integer, Integer>> listUndockButtonWxH = Arrays.asList(new Pair<>(UNDOCK_BUTTON_W1, UNDOCK_BUTTON_H1), new Pair<>(UNDOCK_BUTTON_W2, UNDOCK_BUTTON_H1));
    public static final int UNDOCK_BUTTON_W1 = 235;
    public static final int UNDOCK_BUTTON_W2 = 234;
    public static final int UNDOCK_BUTTON_H1 = 40;
    /* This find where the word Approaching is in the display */
    public final Quartet<Integer, Integer, Integer, Integer> tupleUndockDeadZone
            = new Quartet<>(UNDOCK_DEADZONE_X1, UNDOCK_DEADZONE_X2_W, UNDOCK_DEADZONE_Y1, UNDOCK_DEADZONE_Y2_H);
    public static final int UNDOCK_DEADZONE_X1 = 571;
    public static final int UNDOCK_DEADZONE_X2_W = 1919;
    public static final int UNDOCK_DEADZONE_Y1 = 0;
    public static final int UNDOCK_DEADZONE_Y2_H = 1079;
    /* ------------------------------------------------------------- */

    public static final int COMPACTCARGO_DEADZONE_X1 = 30;
    public static final int COMPACTCARGO_DEADZONE_X2_W = 444;
    public static final int COMPACTCARGO_DEADZONE_Y1 = 0;
    public static final int COMPACTCARGO_DEADZONE_Y2_H = 540;
    /* ------------------------------------------------------------- */

    public static final int CANNON_W1 = 42;
    public static final int CANNON_H1 = 40;
    public static final int CANNON1_X = 1044;
    public static final int CANNON2_X = 1095;
    public static final int CANNONS_Y = 910;
    /* ------------------------------------------------------------- */

    public static final int RANGEDCANNON_W1 = 27;
    public static final int RANGEDCANNON_H1 = 12;
    public static final int RANGEDCANNON1_X = 1049;
    public static final int RANGEDCANNON2_X = 1100;
    public static final int RANGEDCANNONS_Y = 925;

}
/**
 *
 * @author Devmachine BLOCKSCREEN calculator: x1 = x; x2 = x + w; y1 = y; y2 = y
 * + h.
 *
 */
