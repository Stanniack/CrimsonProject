package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.R1920x1080SMALL;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
    private long flagUntilBeFilled_MS = 0;
    private long flagLockTarget_MS = 0;
    private int flagUntilBeFilled_AMOUNT = 0;

    private static final int LOCKTARGET_MS = 60000;
    private static final int CANNONS_MS = 15000;
    private static final int SWITCHFLAG = 7;
    private static final int AMOUNT_APRROACHING_NOTFOUND = 20;
    private static final int TIMETOWAIT_APPROACHING_MS = 10000; // 10 secs
    private static final int TIMETOWAIT_TOBEFILLED_MS = 1200000; // 1200 secs
    private static final int GOTO_HOMESTATION = 0;

    private long timeStartLockTarget = 0;
    private long timeStart = 0;
    private Integer priorityOreValue;
    private final Integer CSpriority = 4;
    private final Integer Spriority = 3;
    private final Integer DVpriority = 2;
    private final Integer CVpriority = 1;
    private final Integer Vpriority = 0;

    /* These lists must be ordered by priority, from highest to lowest to get the closest and better ore possibly */
    private final List<Integer> priorityList = Arrays.asList(Vpriority, CVpriority, DVpriority, Spriority, CSpriority);

    public void strartScript() throws IOException, TesseractException, AWTException, InterruptedException {

        new KeyboardEvents().pressKey(KeyEvent.VK_F3); // afterburner

        SegmentedRegions sr3 = new SegmentedRegions();

        do {

            boolean flagNoDragScreen = false;
            Entry<String, Rectangle> betterOre = null;
            this.priorityOreValue = 0;

            new TakeScreenShot().take();
            // Todo connection lost

            switch (this.amountRect) {

                case 0 -> {

                    /* Reset Y list coordinates to 1081y */
                    List<Integer> closestOreList = Arrays.asList(1081, 1081, 1081, 1081, 1081);

                    HashMap<String, Rectangle> rectResult = sr3.getAllOres_BlockScreen(
                            R1920x1080SMALL.OVERVIEWMINING_X1, R1920x1080SMALL.OVERVIEWMINING_X2_W,
                            R1920x1080SMALL.OVERVIEWMINING_Y1, R1920x1080SMALL.OVERVIEWMINING_Y2_H);

                    if (!rectResult.isEmpty()) {

                        System.out.println("Hash map size: " + rectResult.size());

                        for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                            for (int i = (this.priorityList.size() - 1); i >= 0; i--) {

                                if (item.getKey().contains("P" + i) && this.priorityOreValue <= this.priorityList.get(i)) {

                                    this.priorityOreValue = this.priorityList.get(i);

                                    System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                                    if (item.getValue().y <= closestOreList.get(i)) {
                                        
                                        System.out.println("Temp better ore found: " + item + "\n");
                                        betterOre = item;
                                        closestOreList.set(i, item.getValue().y);

                                    }
                                }
                            }

                        }

                        if (betterOre != null) {

                            System.out.println("Closest, better ore found (Y Coordinate): "
                                    + betterOre.getKey() + " (X,Y) -> (" + betterOre.getValue().x + ", " + betterOre.getValue().y + ")\n");

                            this.amountRect++; // go to case 1
                            flagNoDragScreen = true;
                            new ClickScreenEvents().doubleClick(betterOre.getValue());

                            /* Check case 1 of lock target */
                            this.timeStartLockTarget = System.currentTimeMillis();

                        } else {
                            System.out.println("Better asteroid is null");
                        }

                    } else {
                        System.out.println("Asteroids not found\n");
                    }

                } // end case 0

                case 1 -> {

                    Rectangle lockTargetFromSelectedItem = sr3.getT_WxH_BlockScreen(
                            R1920x1080SMALL.LOCKTARGET_W1,
                            R1920x1080SMALL.LOCKTARGET_H1,
                            R1920x1080SMALL.LOCKTARGET_DEADZONE_X1, R1920x1080SMALL.LOCKTARGET_DEADZONE_X2_W,
                            R1920x1080SMALL.LOCKTARGET_DEADZONE_Y1, R1920x1080SMALL.LOCKTARGET_DEADZONE_Y2_H);

                    if (lockTargetFromSelectedItem != null) {

                        System.out.printf("Rect found (LOCKTARGET) at case 2 - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                lockTargetFromSelectedItem.width, lockTargetFromSelectedItem.height, lockTargetFromSelectedItem.x, lockTargetFromSelectedItem.y);

                        new ClickScreenEvents().leftClickCenterButton(lockTargetFromSelectedItem);
                        this.amountRect++; // go to case 2
                        flagNoDragScreen = true;

                    } else {

                        this.flagLockTarget_MS = System.currentTimeMillis() - this.timeStartLockTarget;
                        System.out.println("Rect (LOCKTARGET) at case 2 not found. Time to restart the script: "
                                + this.flagLockTarget_MS / 1000 + "/" + LOCKTARGET_MS / 1000);

                        if (this.flagLockTarget_MS > LOCKTARGET_MS) {

                            System.out.println("Lock target not found. Restarting script.\n");
                            this.flagLockTarget_MS = 0; // reset flag
                            this.amountRect = 0; // reset script
                            
                        }
                    }
                } // end case 1

                case 2 -> {

                    /* This case needs attetion, it's a fragile code - Two miner cannons*/
                    this.timeStart = System.currentTimeMillis();

                    Thread.sleep(CANNONS_MS);
                    new KeyboardEvents().pressKey(KeyEvent.VK_F1); // cannon 1
                    Thread.sleep(500);
                    new KeyboardEvents().pressKey(KeyEvent.VK_F2); // cannon 2

                    this.amountRect++; // go to case 3
                    flagNoDragScreen = true;
                } // end case 2

                case 3 -> {

                    Rectangle compactMaxCargo = sr3.getT_WxH_BlockScreen(
                            R1920x1080SMALL.COMPACTMAXCARGO_W3,
                            R1920x1080SMALL.COMPACTMAXCARGO_H1,
                            R1920x1080SMALL.COMPACTCARGO_DEADZONE_X1, R1920x1080SMALL.COMPACTCARGO_DEADZONE_X2_W,
                            R1920x1080SMALL.COMPACTCARGO_DEADZONE_Y1, R1920x1080SMALL.COMPACTCARGO_DEADZONE_Y2_H);

                    /* go to the station and drag itens */
                    if (compactMaxCargo != null) {

                        System.out.printf("Rect found (MAXCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                compactMaxCargo.width, compactMaxCargo.height, compactMaxCargo.x, compactMaxCargo.y);

                        this.amountRect = 6; // go to case 6 - docking and drag itens to main station
                        flagNoDragScreen = true;

                    } else {

                        System.out.println("Rect (MAXCARGO_VENTURE) not found\n");
                        this.amountRect++; // go to case 4
                        
                    }
                } // end case 3

                case 4 -> {

                    /* If true, there is no max cargo neither minering ore */
                    if (this.flagUntilBeFilled_AMOUNT > AMOUNT_APRROACHING_NOTFOUND || this.flagUntilBeFilled_MS > TIMETOWAIT_TOBEFILLED_MS) {
                        
                        this.flagUntilBeFilled_AMOUNT = 0;
                        this.amountRect++; // go to case 5

                    } else {

                        Rectangle approaching = sr3.getApproaching_2Wx3H_BlockScreen(
                                R1920x1080SMALL.APPROACHING_W1, R1920x1080SMALL.APPROACHING_W2,
                                R1920x1080SMALL.APPROACHING_H1, R1920x1080SMALL.APPROACHING_H2, R1920x1080SMALL.APPROACHING_H3,
                                R1920x1080SMALL.APPROACHING_DEADZONE_X1, R1920x1080SMALL.APPROACHING_DEADZONE_X2_W,
                                R1920x1080SMALL.APPROACHING_DEADZONE_Y1, R1920x1080SMALL.APPROACHING_DEADZONE_Y2_H);

                        if (approaching != null) {

                            System.out.printf("Rect found (APRROACHING) - Width: %d and height: %d at coordinates (%d, %d)\n",
                                    approaching.width, approaching.height, approaching.x, approaching.y);

                            this.amountRect--; // go back to case 3
                            flagNoDragScreen = true;

                            Thread.sleep(TIMETOWAIT_APPROACHING_MS);

                        } else {

                            System.out.println("Rect (APRROACHING) not found");
                            System.out.println("Added 1 to amount var until set another ore - Max var tolerance: "
                                    + this.flagUntilBeFilled_AMOUNT + "/" + AMOUNT_APRROACHING_NOTFOUND + "\n");

                            this.flagUntilBeFilled_AMOUNT += 1;
                        }

                    }

                    this.flagUntilBeFilled_MS = (System.currentTimeMillis() - this.timeStart);
                    System.out.println("Time added until set another ore: "
                            + (this.flagUntilBeFilled_MS / 1000) + "/" + (TIMETOWAIT_TOBEFILLED_MS / 1000) + " seconds\n");

                } // end case 4

                case 5 -> {

                    // just find another asteroid and restart script after 'flagUntilBeFilled_MS' secs or get limit of no found approaching
                    this.amountRect = 0;
                    this.flagUntilBeFilled_MS = 0;
                    System.out.println("Time limit or Aprroaching limit exceded. Searching for another asteroid.\n");

                } // end case 5

                case 6 -> {

                    System.out.println("End of mining and go docking!\n");
                    new GetDestination().strartScript(GOTO_HOMESTATION);
                    this.amountRect++;

                } // end case 6

            }

            if (!flagNoDragScreen) {
                new ClickScreenEvents().dragScreen();
            }

        } while (this.amountRect < SWITCHFLAG);

    }

}
