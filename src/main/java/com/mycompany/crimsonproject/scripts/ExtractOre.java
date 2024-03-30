package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.PIXELRANGE;
import com.mycompany.crimsonproject.utils.FULLHD;
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
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class ExtractOre {

    private int amountRect = 0;
    private long flagUntilBeFilled_MS = 0;
    private long flagLockTarget_MS = 0;

    private static final int LOCKTARGET_MS = 60000;
    private static final int SWITCHFLAG = 7;
    private static final int TIMETOWAIT_APPROACHING_MS = 10000; // 10 secs
    private static final int TIMETOWAIT_TOBEFILLED_MS = 1100000; // 1100 secs 1100000 ms
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

    public void startScript() throws IOException, TesseractException, AWTException, InterruptedException {

        new KeyboardEvents().pressKey(KeyEvent.VK_F3); // afterburner

        do {

            new TakeScreenShot().take();
            // Todo connection lost

            switch (this.amountRect) {

                case 0 -> {

                    if (this.getAteroids()) {
                        this.timeStartLockTarget = System.currentTimeMillis();
                        this.amountRect++; // go to case 1
                    }

                } // end case 0

                case 1 -> {

                    Rectangle lockTargetFromSelectedItem = new SegmentedRegions().getRectangle(new FULLHD().listLockTarget, new FULLHD().tupleLockTargetDeadZone);

                    if (lockTargetFromSelectedItem != null) {
                        System.out.printf("Rect found (LOCKTARGET) at case 2 - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                lockTargetFromSelectedItem.width, lockTargetFromSelectedItem.height, lockTargetFromSelectedItem.x, lockTargetFromSelectedItem.y);

                        new ClickScreenEvents().leftClickCenterButton(lockTargetFromSelectedItem);
                        //!! launch and engage drones
                        this.amountRect++; // go to case 2

                    } else {
                        this.flagLockTarget_MS = System.currentTimeMillis() - this.timeStartLockTarget;
                        System.out.println("Rect (LOCKTARGET) at case 2 not found. Time to restart the script: "
                                + this.flagLockTarget_MS / 1000 + "/" + LOCKTARGET_MS / 1000 + "\n");

                        if (this.flagLockTarget_MS > LOCKTARGET_MS) {
                            System.out.println("Lock target not found. Restarting script.\n\n");
                            this.flagLockTarget_MS = 0; // reset flag
                            this.amountRect = 0; // reset script
                        }
                    }

                } // end case 1

                case 2 -> {

                    this.timeStart = System.currentTimeMillis();
                    List<Integer> events = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);

                    for (int i = 0; i < events.size(); i++) {

                        if (this.isActive(i)) {
                            new KeyboardEvents().pressKey(events.get(i));
                            Thread.sleep(2000);
                            new KeyboardEvents().pressKey(events.get(i));
                            System.out.println("The cannon was active. Press 2x cannon " + i + "\n");

                        } else if (this.isCanceled(i)) {
                            Thread.sleep(500);
                            new KeyboardEvents().pressKey(events.get(i));
                            System.out.println("The Cannon was canceled. Wait and press 1x cannon " + i + "\n");

                        } else {
                            new KeyboardEvents().pressKey(events.get(i));
                            System.out.println("Just press 1x cannon " + i + "\n");
                        }

                    }

                    this.amountRect++; // go to case 3

                } // end case 2

                case 3 -> {

                    Rectangle compactMaxCargo = new SegmentedRegions().getRectangle(new FULLHD().listCompactMaxCargo, new FULLHD().tupleCompactMaxCargoDeadZone);

                    /* go to the station and drag itens */
                    if (compactMaxCargo != null) {
                        System.out.printf("Rect found (MAXCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                compactMaxCargo.width, compactMaxCargo.height, compactMaxCargo.x, compactMaxCargo.y);

                        this.amountRect = 6; // go to case 6 - docking and drag itens to main station

                    } else {
                        System.out.println("Rect (MAXCARGO_VENTURE) not found\n");
                        this.amountRect++; // go to case 4
                    }

                } // end case 3

                case 4 -> {

                    /* If true, there is no max cargo neither minering ore */
                    if (this.flagUntilBeFilled_MS > TIMETOWAIT_TOBEFILLED_MS) {
                        this.amountRect++; // go to case 5

                    } else {
                        boolean approaching = new FindPixels().countWhitePixels(FULLHD.APPROACHING_X, FULLHD.APPROACHING_Y,
                                FULLHD.APPROACHING_W1, FULLHD.APPROACHING_H3);

                        if (approaching == true) {
                            System.out.println("Rect found (APRROACHING) by counting RGB(255,255,255) white pixels\n");

                            this.amountRect--; // go back to case 3

                            Thread.sleep(TIMETOWAIT_APPROACHING_MS);

                        } else {
                            System.out.println("Rect (APRROACHING) not found\n");
                            this.amountRect++;
                        }
                    }

                    this.flagUntilBeFilled_MS = (System.currentTimeMillis() - this.timeStart);
                    System.out.println("Time added until set another ore: "
                            + (this.flagUntilBeFilled_MS / 1000) + "/" + (TIMETOWAIT_TOBEFILLED_MS / 1000) + " seconds\n");

                } // end case 4

                case 5 -> {

                    System.out.println("Searching for another asteroid.\n");
                    // check opacity
                    List<Integer> events = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);

                    for (int i = 0; i < events.size(); i++) {

                        if (this.isAlpha(i)) {
                            Thread.sleep(500);
                            new KeyboardEvents().pressKey(events.get(i));
                            System.out.println("Cannon had been opacity. Press 1x cannon and search for another asteroid " + i + "\n");
                            this.amountRect = 0;
                        }
                    }

                    this.flagUntilBeFilled_MS = 0;
                    this.amountRect = 0;

                } // end case 5

                case 6 -> {

                    //!! return drones
                    new SetDestination().startScript(GOTO_HOMESTATION);
                    System.out.println("End of mining and go docking!\n");
                    this.amountRect++;

                } // end case 6

            }

        } while (this.amountRect < SWITCHFLAG);

    }

    private boolean getAteroids() throws IOException, TesseractException, AWTException, InterruptedException {

        /* Reset Y list coordinates to 1081y */
        List<Integer> closestOreList = Arrays.asList(1081, 1081, 1081, 1081, 1081);

        Entry<String, Rectangle> betterAteroid = null;
        this.priorityOreValue = 0;

        HashMap<String, Rectangle> rectResult = new SegmentedRegions().getAllOres_BlockScreen(FULLHD.OVERVIEWMINING_X1, FULLHD.OVERVIEWMINING_X2_W,
                FULLHD.OVERVIEWMINING_Y1, FULLHD.OVERVIEWMINING_Y2_H);

        if (!rectResult.isEmpty()) {
            System.out.println("Hash map size: " + rectResult.size());

            for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                for (int i = (this.priorityList.size() - 1); i >= 0; i--) {

                    if (item.getKey().contains("P" + i) && this.priorityOreValue <= this.priorityList.get(i)) {
                        this.priorityOreValue = this.priorityList.get(i);

                        System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                        if (item.getValue().y <= closestOreList.get(i)) {
                            System.out.println("Tempoorary better asteroid found: " + item + "\n");
                            betterAteroid = item;
                            closestOreList.set(i, item.getValue().y);

                        }
                    }
                }
            }

            
            if (betterAteroid != null) {
                System.out.println("CLOSEST, BETTER ASTEROID FOUND (Y Coordinate): "
                        + betterAteroid.getKey() + " (X,Y) -> (" + betterAteroid.getValue().x + ", " + betterAteroid.getValue().y + ")\n");

                new ClickScreenEvents().doubleClick(betterAteroid.getValue());
                return true;

            }
        }

        System.out.println("Closest, better asteroid is null\n");
        return false;
    }

    private boolean isActive(int i) throws IOException, InterruptedException, AWTException {

        List<Integer> coordinatesX = Arrays.asList(FULLHD.CANNON1_X, FULLHD.CANNON2_X);
        int flagAttempt = 10;
        boolean actived;

        for (int j = 0; j < flagAttempt; j++) {

            Thread.sleep(180);
            new TakeScreenShot().take();

            actived = new FindPixels().findRangeColor(coordinatesX.get(i), FULLHD.CANNONS_Y,
                    FULLHD.CANNON_W1, FULLHD.CANNON_H1,
                    new Triplet<>(PIXELRANGE.ACT_MINRED, PIXELRANGE.ACT_MINGREEN, PIXELRANGE.ACT_MINBLUE),
                    new Triplet<>(PIXELRANGE.ACT_MAXRED, PIXELRANGE.ACT_MAXGREEN, PIXELRANGE.ACT_MAXBLUE));

            if (actived) {
                return true;
            }
        }

        return false;
    }

    private boolean isCanceled(int i) throws IOException, InterruptedException, AWTException {

        List<Integer> coordinatesX = Arrays.asList(FULLHD.CANNON1_X, FULLHD.CANNON2_X);
        int flagAttempt = 5;
        boolean canceled;

        for (int j = 0; j < flagAttempt; j++) {

            //Thread.sleep(180);
            new TakeScreenShot().take();

            canceled = new FindPixels().findRangeColor(coordinatesX.get(i), FULLHD.CANNONS_Y,
                    FULLHD.CANNON_W1, FULLHD.CANNON_H1,
                    new Triplet<>(PIXELRANGE.CANCEL_MINRED, PIXELRANGE.CANCEL_MINGREEN, PIXELRANGE.CANCEL_MINBLUE),
                    new Triplet<>(PIXELRANGE.CANCEL_MAXRED, PIXELRANGE.CANCEL_MAXGREEN, PIXELRANGE.CANCEL_MAXBLUE));

            if (canceled) {
                return true;
            }
        }

        return false;
    }

    private boolean isAlpha(int i) throws IOException, InterruptedException, AWTException {

        List<Integer> coordinatesX = Arrays.asList(FULLHD.RANGEDCANNON1_X, FULLHD.RANGEDCANNON2_X);
        int flagAttempt = 10;
        boolean alpha;

        for (int j = 0; j < flagAttempt; j++) {

            //Thread.sleep(180);
            new TakeScreenShot().take();

            alpha = new FindPixels().findByColor(coordinatesX.get(i), FULLHD.RANGEDCANNONS_Y,
                    FULLHD.RANGEDCANNON_W1, FULLHD.RANGEDCANNON_H1,
                    new Triplet<>(PIXELRANGE.ALPHA_RED, PIXELRANGE.ALPHA_GREEN, PIXELRANGE.ALPHA_BLUE));

            if (alpha) {
                return true;
            }
        }
        return false;
    }

}
