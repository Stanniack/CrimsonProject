/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreen;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.Rect1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class ExtractOre {

    private int amountRect = 0;
    private int flagUntilBeDestroyed_MS = 0;
    private int flagUntilBeDestroyed_AMOUNT = 0;
    private int flagSearchLockTarget = 0;

    private static final int SWITCHFLAG = 8;
    private static final int LOCKTARGET_NOTFOUND = 5; // 10 secs
    private static final int AMOUNT_APRROACHING_NOTFOUND = 20;
    private static final int TIMETOGETCLOSE_MS = 8000; // 8 secs
    private static final int TIMETOWAIT_APPROACHING_MS = 10000; // 10 secs
    private static final int TIMETOWAIT_TOBEDSTROYED_MS = 1700000; // 1700 secs
    private static final int GOTO_HOMESTATION = 0;

    private final Integer CSpriority = 5;
    private final Integer Spriority = 4;
    private final Integer DVpriority = 3;
    private final Integer CVpriority = 2;
    private final Integer Vpriority = 1;

    /* These lists must be ordered by priority, from highest to lowest to get the closest and better ore possibly */
    private final List<Integer> priorityList = Arrays.asList(CSpriority, Spriority, DVpriority, CVpriority, Vpriority);

    public void extract() throws IOException, TesseractException, AWTException, InterruptedException {

        do {

            SegmentedRegions sr3 = new SegmentedRegions();
            boolean flagNoDragScreen = false;
            Entry<String, Rectangle> betterOre = null;
            int priorityOre = 0;

            new TakeScreenShot2().take();

            switch (amountRect) {

                case 0 -> {

                    /* Reset Y list coordinates to 1081y */
                    List<Integer> closestOreList = Arrays.asList(1081, 1081, 1081, 1081, 1081);

                    HashMap<String, Rectangle> rectResult = sr3.getSegmentedRegionsAllOres_BLOCKSCREEN(
                            Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                            Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    if (!rectResult.isEmpty()) {

                        System.out.println("Hash map size: " + rectResult.size());

                        for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                            for (int i = 0; i < priorityList.size(); i++) {

                                if (item.getKey().contains("P" + i) && priorityOre <= priorityList.get(i)) {

                                    priorityOre = priorityList.get(i);

                                    System.out.println(item.getKey() + ": " + item.getValue().y + "y");
                                    System.out.println(item.getValue().y + " <? " + closestOreList.get(i));

                                    if (item.getValue().y <= closestOreList.get(i)) {
                                        betterOre = item;
                                        closestOreList.set(i, item.getValue().y);

                                    }
                                }
                            }

                        }

                        if (betterOre != null) {

                            System.out.println("Closest better ore found (Y Coordinate): "
                                    + betterOre.getKey() + " (X,Y) -> (" + betterOre.getValue().x + ", " + betterOre.getValue().y + ")");

                            amountRect++; // go to case 1
                            flagNoDragScreen = true;
                            new ClickScreen().rightClickCenterButton(betterOre.getValue()); /////////////!!!

                        } else {
                            System.out.println("Better ore is null");
                        }

                    } else {
                        System.out.println("Ore not found");
                    }

                    System.out.println(); //

                } // end case 0

                case 1 -> {

                    //For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. 
                    Rectangle warpArrow = sr3
                            .getSegmentedRegion_2WxH_BLOCKSCREEN(Rect1920x1080.WARPARROW_WIDTH1, Rect1920x1080.WARPARROW_WIDTH2, Rect1920x1080.WARPARROW_HEIGHT1,
                                    Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    if (warpArrow != null) {
                        System.out.printf("Rect found (WARPARROW) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                warpArrow.width, warpArrow.height, warpArrow.x, warpArrow.y);

                        new ClickScreen().leftClickCenterButton(warpArrow);
                        this.amountRect++; // go to case 2
                        flagNoDragScreen = true;

                        /*Wait until ship get close to the selected ore */
                        Thread.sleep(TIMETOGETCLOSE_MS);

                    } else {
                        System.out.println("Rect (WARPARROW) not found");
                        new ClickScreen().returnCaseLeftClick(); // click to disappear the arrow float window to restart the script case 1
                        this.amountRect--; // back to case 0 and find other ore
                    }
                } // end case 1

                case 2 -> {
                    Rectangle lockTargetFromSelectedItem = sr3.getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.LOCKTARGETFROMSELECTEDITEM_WIDTH1, Rect1920x1080.LOCKTARGETFROMSELECTEDITEM_HEIGHT1,
                            Rect1920x1080.SELECTEDITEM_LOCKTARGET_X, Rect1920x1080.SELECTEDITEM_LOCKTARGET_X2_W_BLOCKSCREEN,
                            Rect1920x1080.LOCATIONSYMBOL_X2_W_BLOCKSCREEN, Rect1920x1080.LOCATIONSYMBOL_Y2_H_BLOCKSCREEN);

                    if (lockTargetFromSelectedItem != null) {
                        System.out.printf("Rect found (LOCKTARGET) at case 2 - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                lockTargetFromSelectedItem.width, lockTargetFromSelectedItem.height, lockTargetFromSelectedItem.x, lockTargetFromSelectedItem.y);

                        new ClickScreen().leftClickCenterButton(lockTargetFromSelectedItem);
                        this.amountRect++; // go to case 3
                        flagNoDragScreen = true;

                    } else {
                        System.out.println("Rect (LOCKTARGET) at case 2 not found");
                    }
                } // end case 2

                case 3 -> {
                    /* This case needs attetion, it's a fragile code */
                    new ClickScreen().leftClick(1065, 935);
                    this.amountRect++; // go to case 4
                    flagNoDragScreen = true;
                } // end case 3

                case 4 -> {
                    Rectangle maxCargo = sr3.getSegmentedRegionsAllRectsMaxCargo_BLOCKSCREEN(
                            Rect1920x1080.MAXCARGO_BLOCKSCREEN_X, Rect1920x1080.MAXCARGO_X2_W_BLOCKSCREEN,
                            Rect1920x1080.MAXCARGO_BLOCKSCREEN_Y, Rect1920x1080.MAXCARGO_Y2_H_BLOCKSCREEN);

                    /* go to the station and dragon itens */
                    if (maxCargo != null) {
                        System.out.printf("Rect found (MAXCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                maxCargo.width, maxCargo.height, maxCargo.x, maxCargo.y);

                        this.amountRect += 3; // go to case 7 - docking and drag itens to main station
                        flagNoDragScreen = true;

                    } else {
                        System.out.println("Rect (MAXCARGO_VENTURE) not found\n");
                        this.amountRect++; // go to case 5
                    }
                } // end case 4

                case 5 -> {
                    Rectangle approaching = sr3.getSegmentedRegionApproaching_2Wx3H_BLOCKSCREEN(Rect1920x1080.APPROACHING_WIDTH1, Rect1920x1080.APPROACHING_WIDTH2,
                            Rect1920x1080.APPROACHING_HEIGHT1, Rect1920x1080.APPROACHING_HEIGHT2, Rect1920x1080.APPROACHING_HEIGHT3,
                            Rect1920x1080.APPROACHING_BLOCKSCREEN_X, Rect1920x1080.APPROACHING_X2_W_BLOCKSCREEN,
                            Rect1920x1080.APPROACHING_BLOCKSCREEN_Y, Rect1920x1080.APPROACHING_Y2_H_BLOCKSCREEN);

                    if (approaching != null) {
                        System.out.printf("Rect found (APRROACHING) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                approaching.width, approaching.height, approaching.x, approaching.y);

                        this.amountRect--; // go back to case 4
                        flagNoDragScreen = true;

                        Thread.sleep(TIMETOWAIT_APPROACHING_MS);
                    } else {
                        System.out.println("Rect (APRROACHING) not found");
                        System.out.println("Time added until set another ore: " + (this.flagUntilBeDestroyed_MS / 1000) + "/" + (TIMETOWAIT_TOBEDSTROYED_MS / 1000) + " seconds");
                        System.out.println("Added 1 to amount var until set another ore - Max var tolerance: " + this.flagUntilBeDestroyed_AMOUNT + "/" + AMOUNT_APRROACHING_NOTFOUND + "\n");
                        this.flagUntilBeDestroyed_AMOUNT += 1;
                    }

                    flagUntilBeDestroyed_MS += TIMETOWAIT_APPROACHING_MS;

                    /* If true, there is no max cargo neither minering ore */
                    if (this.flagUntilBeDestroyed_AMOUNT > AMOUNT_APRROACHING_NOTFOUND || this.flagUntilBeDestroyed_MS > TIMETOWAIT_TOBEDSTROYED_MS) {
                        this.flagUntilBeDestroyed_AMOUNT = 0;
                        this.flagUntilBeDestroyed_MS = 0;
                        this.amountRect++; // go to case 6
                    }
                } // end case 5

                case 6 -> {
                    Rectangle maxCargo = sr3.getSegmentedRegionsAllRectsMaxCargo_BLOCKSCREEN(
                            Rect1920x1080.MAXCARGO_BLOCKSCREEN_X, Rect1920x1080.MAXCARGO_X2_W_BLOCKSCREEN,
                            Rect1920x1080.MAXCARGO_BLOCKSCREEN_Y, Rect1920x1080.MAXCARGO_Y2_H_BLOCKSCREEN);

                    Rectangle approaching = sr3.getSegmentedRegionApproaching_2Wx3H_BLOCKSCREEN(Rect1920x1080.APPROACHING_WIDTH1, Rect1920x1080.APPROACHING_WIDTH2,
                            Rect1920x1080.APPROACHING_HEIGHT1, Rect1920x1080.APPROACHING_HEIGHT2, Rect1920x1080.APPROACHING_HEIGHT3,
                            Rect1920x1080.APPROACHING_BLOCKSCREEN_X, Rect1920x1080.APPROACHING_X2_W_BLOCKSCREEN,
                            Rect1920x1080.APPROACHING_BLOCKSCREEN_Y, Rect1920x1080.APPROACHING_Y2_H_BLOCKSCREEN);

                    /* There is no max cargo neither minering ore */
                    if (maxCargo == null && approaching == null) {

                        /* If the asteroid won't be destoyed, the lockTarget must be desactived or the stack will broke the script*/
                        if (this.flagSearchLockTarget < LOCKTARGET_NOTFOUND) {
                            // click again in lock target to unlock it and restart minering other ore! 
                            Rectangle lockTargetFromSelectedItem = sr3.getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.LOCKTARGETFROMSELECTEDITEM_WIDTH1, Rect1920x1080.LOCKTARGETFROMSELECTEDITEM_HEIGHT1,
                                    Rect1920x1080.SELECTEDITEM_LOCKTARGET_X, Rect1920x1080.SELECTEDITEM_LOCKTARGET_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.LOCATIONSYMBOL_X2_W_BLOCKSCREEN, Rect1920x1080.LOCATIONSYMBOL_Y2_H_BLOCKSCREEN);

                            if (lockTargetFromSelectedItem != null) {
                                System.out.printf("Rect found (LOCKTARGET) at case 6 - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                        lockTargetFromSelectedItem.width, lockTargetFromSelectedItem.height, lockTargetFromSelectedItem.x, lockTargetFromSelectedItem.y);

                                new ClickScreen().leftClickCenterButton(lockTargetFromSelectedItem);
                                flagNoDragScreen = true;
                                amountRect = 0; // return to case 0 and find another ore
                            } else {
                                System.out.println("Rect (LOCKTARGET) not found at case 6");
                                System.out.println("Max tolerance until search another asteroid: " + (this.flagSearchLockTarget + 1) + "/" + LOCKTARGET_NOTFOUND + "\n");
                                this.flagSearchLockTarget++;
                            }

                        } else {
                            System.out.println("Rect (LOCKTARGET) not found at case 6 -> Searching another asteroid\n");
                            flagNoDragScreen = true;
                            this.flagSearchLockTarget = 0;
                            this.amountRect = 0; // return to case 0 and find another ore
                        }

                    } else {
                        this.amountRect--; // Return to case 5
                    }
                } // end case 6

                case 7 -> {
                    System.out.println("End of mining and go docking!\n");
                    new GetDestination().getDestination(GOTO_HOMESTATION);
                    this.amountRect++;
                } // end case 7

            }

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

        } while (this.amountRect < SWITCHFLAG);

    }

}
