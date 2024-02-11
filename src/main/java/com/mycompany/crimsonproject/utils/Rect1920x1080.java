package com.mycompany.crimsonproject.utils;

/**
 *
 * @author Stanniack
 * BLOCKSCREEN calculator: 
 * x1 = x; 
 * x2 = x + w; 
 * y1 = y; 
 * y2 + h.
 * BLOSCKSCREEN calculator with DEADZONE:
 * x1 = x; 
 * x2 = x + w + DEADZONE; 
 * y1 = y; 
 * y2 + h + DEADZONE.
 * 
 * BLOCKSCREEN: Rect limitation where to find the recognized rects
 * DEADZONE: Pixel's amount to add in the BLOCKSCREEN for caution
 */
public class Rect1920x1080 {

    /* ------------------------------------------------------------- */
    public static final int MAXCARGO1_W1 = 111;
    public static final int MAXCARGO1_W2 = 110;
    public static final int MAXCARGO1_H1 = 24;

    public static final int MAXCARGO2_W1 = 101;
    public static final int MAXCARGO2_H1 = 10;

    public static final int MAXCARGO3_W1 = 29;
    public static final int MAXCARGO3_H1 = 8;

    public static final int COMPACTMAXCARGO_W1 = 339;
    public static final int COMPACTMAXCARGO_W2 = 419;
    public static final int COMPACTMAXCARGO_H1 = 5;

    /* ------------------------------------------------------------- */
    public static final int MINGCARGO_WITHM3_W1 = 72;
    public static final int MINGCARGO_WITHOUTM3_W1 = 51;
    public static final int MINGCARGO_H1 = 9;

    /* ------------------------------------------------------------- */
    public static final int LOCATIONSYMBOL_W1 = 10;
    public static final int LOCATIONSYMBOL_H1 = 14;
    /* This find where the button Location is in the display */
    public static final int LOCATIONSYMBOL_X1 = 0;
    public static final int LOCATIONSYMBOL_Y1 = 460;
    public static final int LOCATIONSYMBOL_X2_W = 50;
    public static final int LOCATIONSYMBOL_Y2_H = 530;

    /* ------------------------------------------------------------- */
    public static final int MININGBOT1_W1 = 59;
    public static final int MININGBOT1_W2 = 60;
    public static final int MININGBOT1_W3 = 8;
    public static final int MININGBOT1_H1 = 9;
    public static final int MININGBOT1_H2 = 12;
    /* This find where the button MINNINGBOT1 is in the display */
    public static final int MININGBOT1_X1 = 650;
    public static final int MININGBOT1_Y1 = 380;
    public static final int MININGBOT1_X2_W = 680;
    public static final int MININGBOT1_Y2_H = 410;

    /* ------------------------------------------------------------- */
    public static final int WARPARROW_W1 = 13;
    public static final int WARPARROW_W2 = 12;
    public static final int WARPARROW_H1 = 6;
    
    public static final int DOCKARROW_W1 = 12;
    public static final int DOCKARROW_H1 = 12;
    /* This find where the button WARPARROW is in the display */
    public static final int WARPARROW_X1 = 600;
    public static final int WARPARROW_Y1 = 360;
    public static final int WARPARROW_X2_W = 1000;
    public static final int WARPARROW_Y2_H = 450;

    /* ------------------------------------------------------------- */
    public static final int HOMESTATION1_W1 = 75;
    public static final int HOMESTATION1_W2 = 74;
    public static final int HOMESTATION1_W3 = 91; // must be removed!!
    public static final int HOMESTATION1_H1 = 9;
    public static final int HOMESTATION1_H2 = 12; // must be removed!
    /* This find where the button HOMESTATION1 is in the display */
    public static final int HOMESTATION1_X1 = 620;
    public static final int HOMESTATION1_Y1 = 340;
    public static final int HOMESTATION1_X2_W = 770;
    public static final int HOMESTATION1_Y2_H = 420;

    /* ------------------------------------------------------------- */
    public static final int CLOSELOCATIONBUTTON_W1 = 10;
    public static final int CLOSELOCATIONBUTTON_H1 = 10;
    /* This find where the button CLOSEBUTTONLOCATION is in the display */
    public static final int CLOSELOCATIONBUTTON_X1 = 1000;
    public static final int CLOSELOCATIONBUTTON_Y1 = 250;
    public static final int CLOSELOCATIONBUTTON_X2_W = 1170;
    public static final int CLOSELOCATIONBUTTON_Y2_H = 320;

    /* ------------------------------------------------------------- 

    * Priority 1: (62, 9); (61, 9) */
    public static final int CONDENSED_W1 = 62;
    public static final int CONDENSED_W2 = 61;
    public static final int CONDENSED_H1 = 9;

    /* Priority 2: (42,9); (42,10) */
    public static final int SCORDITE_W1 = 42;
    public static final int SCORDITE_H1 = 9;
    public static final int SCORDITE_H2 = 10;

    /* Priotity 3: (34, 9); (33, 9) */
    public static final int DENSE_W1 = 34;
    public static final int DENSE_W2 = 33;
    public static final int DENSE_H1 = 9;

    /* Priority 4: (73, 10); (72, 10); (72, 9); (71, 9) */
    public static final int CONCENTRATED_W1 = 73;
    public static final int CONCENTRATED_W2 = 72;
    public static final int CONCENTRATED_W3 = 71;
    public static final int CONCENTRATED_H1 = 10;
    public static final int CONCENTRATED_H2 = 9;

    /* Priority 5: (50, 13); (49, 13); (48, 12) */
    public static final int VELDSPAR_W1 = 50;
    public static final int VELDSPAR_W2 = 49;
    public static final int VELDSPAR_W3 = 48;
    public static final int VELDSPAR_H1 = 13;
    public static final int VELDSPAR_H2 = 12;

    /* This find where the floating overview->mining window is in the display */
    public static final int OVERVIEWMINING_X1 = 1300;
    public static final int OVERVIEWMINING_Y1 = 275;
    public static final int OVERVIEWMINING_X2_W = 1900;
    public static final int OVERVIEWMINING_Y2_H = 900;

    /* ------------------------------------------------------------- */
    public static final int LOCKTARGET_W1 = 26;
    public static final int LOCKTARGET_H1 = 26;
    /* This find where the button LOCK TARGET OF SELECTED ITEM WINDOW is in the display */
    public static final int LOCKTARGET_X1 = 1490;
    public static final int LOCKTARGET_Y1 = 135;
    public static final int LOCKTARGET_X2_W = 1510;
    public static final int LOCKTARGET_Y2_H = 150;

    /* ------------------------------------------------------------- 
    This find where the rectangle of MAXCARGO is in the display */
    public static final int MAXCARGO_X1 = 340;
    public static final int MAXCARGO_Y1 = 290;
    public static final int MAXCARGO_X2_W = 360;
    public static final int MAXCARGO_Y2_H = 310;

    /* ------------------------------------------------------------- 
    This find where the rectangle of COMPACTMAXCARGO is in the display */
    public static final int COMPACTEDMAXCARGO_X1 = 200;
    public static final int COMPACTEDMAXCARGO_Y1 = 250;
    public static final int COMPACTEDMAXCARGO_X2_W = 300;
    public static final int COMPACEDTMAXCARGO_Y2_H = 350;

    /* ------------------------------------------------------------- */
    // (122, 16); (121, 14); (121, 25)
    public static final int APPROACHING_W1 = 122;
    public static final int APPROACHING_W2 = 121;
    public static final int APPROACHING_H1 = 25;
    public static final int APPROACHING_H2 = 16;
    public static final int APPROACHING_H3 = 14;
    /* This find where the word Approaching is in the display */
    public static final int APPROACHING_X1 = 885;
    public static final int APPROACHING_Y1 = 770;
    public static final int APPROACHING_X2_W = 915;
    public static final int APPROACHING_Y2_H = 795;

    /* ------------------------------------------------------------- */
    public static final int ITEMGANGAR_W1 = 24;
    public static final int ITEMGANGAR_W2 = 23;
    public static final int ITEMGANGAR_H1 = 9;
    /* This find where the word ITEM (Not HANGAR) is in the display */
    public static final int ITEMHANGAR_X1 = 80;
    public static final int ITEMHANGAR_Y1 = 290;
    public static final int ITEMHANGAR_X2_W = 260;
    public static final int ITEMHANGAR_Y2_H = 720;
    
    /* ------------------------------------------------------------- */
    //public static final int 
    
    
    
    
}
