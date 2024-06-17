package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.interfaces.VerifyRectangle;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.RGBrange;
import com.mycompany.crimsonproject.utils.FullHd;
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
import org.javatuples.Pair;

/**
 *
 * @author Devmachine
 */
public class ExtractOre implements VerifyRectangle {

    private Rectangle target;
    private RGBrange pr = null;
    private FullHd rgbr = null;

    private int amountRect = 0;
    private long flagTimeToBeFilled_MS = 0;
    private long flagUntilToBeFilled_MS = 0;
    private long flagLockTarget_MS = 0;

    private static final int LOCKTARGET_MS = 60000;
    private static final int SWITCHFLAG = 7;
    private static final int TIMETOWAIT_TOBEFILLED_MS = 645500; // Retriver: 1904000; Venture + Miner II: 645500; Venture + Gaussian: 718000
    private static final int TIMETOWAIT_CANNON_MS = 695500;
    private static final int GOTO_HOMESTATION = 0;
    private static final int CANNON_SLEEP = 2000;

    private long timeStartLockTarget = 0;
    private long timeStart = 0;
    private long timeStart2 = 0;
    private Integer priorityOreValue;
    private final Integer CSpriority = 0;
    private final Integer Spriority = 1;
    private final Integer DVpriority = 2;
    private final Integer CVpriority = 3;
    private final Integer Vpriority = 4;
    /* These lists must be ordered by priority, from lowest to highest to get the closest and better ore possible */
    private final List<Integer> priorityList = Arrays.asList(CSpriority, Spriority, DVpriority, CVpriority, Vpriority);

    public ExtractOre() {
        this.pr = new RGBrange();
        this.rgbr = new FullHd();
    }

    public boolean startScript() throws IOException, TesseractException, AWTException, InterruptedException {

        new KeyboardEvents().clickKey(KeyEvent.VK_F3); // afterburner
        this.timeStart2 = System.currentTimeMillis();

        while (this.amountRect < SWITCHFLAG) {

            // Call method
            new TakeScreenShot().take();
            // Call method
            this.verifyInvalidTarget(this.rgbr.getInvalidTargetList(), 195, "Invalid target found.");

            switch (this.amountRect) {

                case 0 -> {
                    if (this.getAsteroids()) {
                        this.timeStartLockTarget = System.currentTimeMillis();
                        this.amountRect++; // go to case 1
                    }
                } // end case 0

                case 1 -> {
                    target = new SegmentedRegions().getRectangle(this.rgbr.getLockTargetList(), this.rgbr.getTupleLockTargetDeadZone());

                    // target identified
                    if (this.verifyRectangle(target, "TARGET", 0)) {

                        boolean lockTarget = new FindPixels().findRangeColor(target.x, target.y, target.width, target.height, this.pr.getMinLockTargetRGB(), this.pr.getMaxLockTargetRGB());

                        // If there is a lock target, just go to next step
                        if (lockTarget) {
                            this.amountRect++; // go to case 2

                        } else {
                            boolean freeTarget = new FindPixels().findRangeColor(target.x, target.y, target.width, target.height, this.pr.getMinFreeTargetRGB(), this.pr.getMaxFreeTargetRGB());
                            // If there is no lock target but the target is free, click target and go next step
                            if (freeTarget) {
                                new ClickScreenEvents().leftClickCenterButton(target);
                                this.amountRect++; // go to case 2
                            } else {
                                System.out.println("Free target not found.");
                            }
                        }

                    } else {
                        this.flagLockTarget_MS = System.currentTimeMillis() - this.timeStartLockTarget;
                        System.out.printf("Rect (LOCKTARGET) at case 2 not found. Time to restart the script: %d/%d\n\n", this.flagLockTarget_MS / 1000, LOCKTARGET_MS / 1000);
                        new ClickScreenEvents().dragScreen();

                        if (this.flagLockTarget_MS > LOCKTARGET_MS) {
                            System.out.println("Restarting script.\n\n");
                            this.flagLockTarget_MS = 0; // reset flag
                            this.amountRect = 0; // reset script
                        }
                    }
                } // end case 1

                case 2 -> {
                    this.timeStart = System.currentTimeMillis();
                    this.launchDrones(); //!!!!
                    Thread.sleep(this.defineMinerCannonTime_MS()); // time to wait miner cannon
                    this.checkMinerCannonAction();
                    this.amountRect++; // go to case 3
                } // end case 2

                case 3 -> {
                    Rectangle compactMaxCargo = new SegmentedRegions().getRectangle(this.rgbr.getCompactMaxCargoList(), this.rgbr.getCompactMaxCargoDeadZone());

                    if (this.verifyRectangle(compactMaxCargo, "MAXCARGO_VENTURE", 0)) {
                        this.amountRect = 6; // go to case 6 - docking and drag itens to main station

                    } else {
                        if (this.flagUntilToBeFilled_MS > TIMETOWAIT_CANNON_MS) {
                            new ClickScreenEvents().dragScreen();
                        }

                        //System.out.printf("Time added until start drag screen to search maxCargo: %d/%d secs\n\n", this.flagUntilToBeFilled_MS / 1000, TIMETOWAIT_CANNON_MS / 1000);
                        this.amountRect++; // go to case 4
                    }
                } // end case 3

                case 4 -> {
                    if (!this.checkPixelsAprroaching() || (this.flagTimeToBeFilled_MS > TIMETOWAIT_TOBEFILLED_MS)) {
                        this.amountRect++; // Approaching not found or time exceeded

                    } else {
                        if (this.checkPixelsAprroaching()) {
                            this.amountRect--; // back to case and check maxCargo
                        }
                    }

                    this.flagUntilToBeFilled_MS = (System.currentTimeMillis() - this.timeStart2);
                    this.flagTimeToBeFilled_MS = (System.currentTimeMillis() - this.timeStart);
                    //System.out.printf("Time added until set another ore: %d/%d secs\n\n", this.flagTimeToBeFilled_MS/1000, TIMETOWAIT_TOBEFILLED_MS/1000);
                } // end case 4

                case 5 -> {
                    this.flagUntilToBeFilled_MS = 0;
                    this.flagTimeToBeFilled_MS = 0;
                    this.amountRect = 0;
                } // end case 5

                case 6 -> {
                    this.returnDrones();
                    new SetDestination().startScript(GOTO_HOMESTATION);
                    System.out.println("End of mining and go docking!\n");
                    this.amountRect++;
                } // end case 6

            }

        } // end while

        return true;

    }

    private boolean getAsteroids() throws IOException, TesseractException, AWTException, InterruptedException {

        /* Reset Y list coordinates to 1081y */
        List<Integer> closestOreList = Arrays.asList(1081, 1081, 1081, 1081, 1081);

        Entry<String, Rectangle> betterAteroid = null;
        this.priorityOreValue = 0;

        HashMap<String, Rectangle> rectResult = new SegmentedRegions().getAllOres(FullHd.getOVERVIEWMINING_X1(), FullHd.getOVERVIEWMINING_X2_W(),
                FullHd.getOVERVIEWMINING_Y1(), FullHd.getOVERVIEWMINING_Y2_H());

        if (!rectResult.isEmpty()) {
            System.out.println("Hash map size: " + rectResult.size());
            System.out.println("All Asteroids: " + rectResult.toString());

            for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                for (int i = (this.priorityList.size() - 1); i >= 0; i--) {

                    if (item.getKey().contains("P" + i) && this.priorityOreValue <= this.priorityList.get(i)) {
                        this.priorityOreValue = this.priorityList.get(i);

                        System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                        if (item.getValue().y <= closestOreList.get(i)) {
                            System.out.println("Temporary better asteroid found: " + item + "\n");
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
        new ClickScreenEvents().dragScreen();
        return false;
    }

    private void checkMinerCannonAction() throws IOException, InterruptedException, AWTException {

        List<Integer> events = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);

        for (int i = 0; i < events.size(); i++) {

            if (this.isMinerCannonAction(i, 11, Arrays.asList(FullHd.getVENTURECANNON1_X(), FullHd.getVENTURECANNON2_X()), FullHd.getRANGEDCANNONS_Y(), FullHd.getVENTURECANNON_W1(), FullHd.getVENTURECANNON_H1(), this.pr.getMinActivedMinerCannonRGB(), this.pr.getMaxActivedMinerCannonRGB())) {
                new KeyboardEvents().clickKey(events.get(i));
                Thread.sleep(CANNON_SLEEP);
                new KeyboardEvents().clickKey(events.get(i));
                System.out.println("The cannon was active. Press 2x cannon " + i + "\n");

            } else {
                Thread.sleep(CANNON_SLEEP); // Wait if cannon was canceled
                new KeyboardEvents().clickKey(events.get(i));
                System.out.println("Just press 1x cannon " + i + "\n");
            }
        }
    }

    @Override
    public boolean verifyRectangle(Rectangle rectangle, String itemName, int chosenClick) throws AWTException, InterruptedException {

        if (rectangle != null) {
            System.out.printf("Rect found (%s) - Width: %d and Height: %d at coordinates (%d, %d)\n\n", itemName, rectangle.width, rectangle.height, rectangle.x, rectangle.y);
            return true;
        }

        return false;
    }

    private boolean checkPixelsAprroaching() throws IOException {

        boolean approaching = new FindPixels().countPixels(FullHd.getAPPROACHING_X(), FullHd.getAPPROACHING_Y(),
                FullHd.getAPPROACHING_W1(), FullHd.getAPPROACHING_H3(), this.pr.getFullWhiteRGB());

        if (approaching == true) {
            //System.out.println("Rect found (APRROACHING)\n");
            return true;
        }

        System.out.println("Rect not found (APRROACHING)\n");
        return false;
    }

    private boolean isMinerCannonAction(int i, int flagAttempt, List<Integer> coordinatesX, int y, int width, int height, Triplet<Integer, Integer, Integer> tupleMin, Triplet<Integer, Integer, Integer> tupleMax) throws InterruptedException, AWTException, IOException {

        boolean action;

        for (int j = 0; j < flagAttempt; j++) {
            Thread.sleep((int) (250 * Math.random()));
            new TakeScreenShot().take();

            action = new FindPixels().findRangeColor(coordinatesX.get(i), y,
                    width, height, tupleMin, tupleMax);

            if (action) {
                return true;
            }
        }
        return false;
    }

    private void verifyInvalidTarget(List<Pair<Integer, Integer>> listWxHrects, int moe, String msg) throws IOException, TesseractException, AWTException, InterruptedException {
        boolean isClicked = false;

        try {
            Rectangle rect = new SegmentedRegions().getRectangle(listWxHrects, this.rgbr.getInvalidTargetDeadZoneList());
            System.out.println("Invalid target found: " + rect.toString());

            if (new FindPixels().findRangeColor(rect.x, rect.y, rect.width, rect.height, this.pr.getMinInfoRGB(), this.pr.getMaxInfoRGB())) {
                rect.y += moe;
                new ClickScreenEvents().leftClickCenterButton(rect);
                isClicked = true;
            }

            System.out.println(msg + "\n");
        } catch (NullPointerException ex) {
        }

        if (isClicked) {

            Rectangle locktarget = new SegmentedRegions().getRectangle(this.rgbr.getLockTargetList(), this.rgbr.getTupleLockTargetDeadZone());

            if (locktarget != null) {
                System.out.println("Invalid locked target found: " + locktarget.toString());
                new ClickScreenEvents().leftClickCenterButton(locktarget);
                this.amountRect = 0; //!!
                
            } else if (this.target != null) {
                int asteroidTarget = 36;
                this.target.x += asteroidTarget;
                boolean lockAsteroidTarget = new FindPixels().findRangeColor(this.target.x, this.target.y, this.target.width, this.target.height, this.pr.getMinLockTargetRGB(), this.pr.getMaxLockTargetRGB());
                if (lockAsteroidTarget) {
                    new ClickScreenEvents().leftClickCenterButton(this.target);
                    this.amountRect = 0; //!!
                }
                
            } else {
                this.amountRect = 6;
                System.out.println("Invalid locked target not found, ending script.");
            }

        }

    }

    private void launchDrones() throws AWTException, InterruptedException {
        int timeSleep_MS = 3000;
        new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
        Thread.sleep(timeSleep_MS);
        new KeyboardEvents().clickKey(KeyEvent.VK_F);
    }

    private void returnDrones() throws AWTException, InterruptedException {
        int waitDrones_MS = 5000;
        new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
        Thread.sleep(waitDrones_MS);
    }

    private long defineMinerCannonTime_MS() {
        return 0;
    }
}
