package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.interfaces.VerifyRectangle;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.soundlogs.SoundAlert;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.RGBrange;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;
import org.javatuples.Pair;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class ExtractOre implements VerifyRectangle {

    private Rectangle target;
    private RGBrange rgbr = null;
    private R1920x1080 resolution = null;

    private final Triplet<Integer, Integer, Integer> tonsOfGreen;
    private final int giveAtry;

    private int walkThrough = 0;

    private long flagSetAnotherAstMS = 0;
    private long flagUntilToBeFilledMS = 0;
    private long flagDeactivePropulsionMS = 0;
    private long flagLockTargetMS = 0;
    private long timeStartLockTarget = 0;
    private long timeStartSetAnotherAst = 0;
    private long timeStartFilled = 0;
    private long timeStartProp = 0;

    private final int amountCannons = 2;

    private boolean isAnotherAst;
    private boolean isRunnable = true;
    private final boolean isSwitchable;

    private static final int LOCKTARGET_MS = 60000;
    private static final int WAITFORSWITCHASTBELT_MS = 20000;
    private static final int STEPS = 5;
    private static final int SETANOTHERAST_MS = 180000;
    private static final int DEACTIVEPROP_MS = 120000;
    private static final int STARTDRAGSCREEN_MS = 2200000;
    private static final int GOTO_HOMESTATION = 0;
    private static final int GOTO_ASTBELT = 1;
    private static final int CANNON_SLEEP = 1500;
    private final int waitForWarp_MS;

    private Integer priorityOreValue;
    private final Integer CSpriority = 0;
    private final Integer Spriority = 1;
    private final Integer DVpriority = 2;
    private final Integer CVpriority = 3;
    private final Integer Vpriority = 4;
    /* These lists must be ordered by priority, from lowest to highest to get the closest and better ore possible */
    private final List<Integer> priorityList = Arrays.asList(CSpriority, Spriority, DVpriority, CVpriority, Vpriority);

    // JavDoc!!!!!!!!!!!!!!!!!
    public ExtractOre(boolean isSwitchable, int waitForWarp_MS, int giveAtry, Triplet<Integer, Integer, Integer> tonsOfGreen) {
        this.isSwitchable = isSwitchable;
        this.waitForWarp_MS = waitForWarp_MS;
        this.giveAtry = giveAtry;
        this.tonsOfGreen = tonsOfGreen;

        this.rgbr = new RGBrange();
        this.resolution = new R1920x1080();
    }

    public boolean startScript() throws IOException, TesseractException, AWTException, InterruptedException {

        this.timeStartFilled = System.currentTimeMillis();

        while (this.walkThrough < STEPS) {
            // Todo connection lost 
            // TODO

            // Call method priority MAX
            new TakeScreenshot().take();

            //Call method
            this.verifyShipLife();

            // Call method
            this.flowScript();

            // Call method
            this.verifyInvalidTarget(this.resolution.getInvalidTargetList(), 195, "Invalid target found.");

            //Call method
            if (this.walkThrough > 2) {
                this.checkCannonsAction();
            }
        }
        return isRunnable;
    }

    private void flowScript() throws AWTException, InterruptedException, IOException, TesseractException {

        switch (this.walkThrough) {

            case 0 -> {
                if (this.getAsteroids()) {
                    this.timeStartLockTarget = System.currentTimeMillis();
                    this.timeStartProp = System.currentTimeMillis();
                    this.isAnotherAst = true;
                    this.propulsion();
                    this.walkThrough++; // go to case 1
                }
            } // end case 0

            case 1 -> {
                // Search for target
                this.target = new SegmentedRegions().getRectangle(this.resolution.getLockTargetList(), this.resolution.getLockTargetDeadZoneTuple());

                if (this.verifyRectangle(this.target, "TARGET", 0)) {
                    if (new FindPixels().findByRangeColor(this.target.x, this.target.y, this.target.width, this.target.height, this.rgbr.getMinLockTarget(), this.rgbr.getMaxLockTarget())) {
                        // If there is a lock target, just go to next step
                        this.walkThrough++; // go to case 2

                    } else if (new FindPixels().findByRangeColor(this.target.x, this.target.y, this.target.width, this.target.height, this.rgbr.getMinFreeTarget(), this.rgbr.getMaxFreeTarget())) {
                        // If the target is free, click target and go next step
                        new ClickScreenEvents().leftClickCenterButton(this.target);
                        this.walkThrough++; // go to case 2

                    } else {
                        System.out.println("Lock target and free target not found.");
                        new ClickScreenEvents().dragScreen();
                    }

                } else {
                    this.flagLockTargetMS = System.currentTimeMillis() - this.timeStartLockTarget;
                    //System.out.printf("Rect (LOCKTARGET) at case 2 not found. Time to restart the script: %d/%d\n\n", this.flagLockTarget_MS / 1000, LOCKTARGET_MS / 1000);
                    new ClickScreenEvents().dragScreen();

                    if (this.flagLockTargetMS > LOCKTARGET_MS) {
                        System.out.println("Restarting script.\n\n");
                        this.flagLockTargetMS = 0; // reset flag
                        this.walkThrough = 0; // reset script
                    }
                }
            }

            case 2 -> {
                this.timeStartSetAnotherAst = System.currentTimeMillis();
                this.activeCannons();
                this.launchDrones();
                this.engageDrones(); // engage drones
                this.walkThrough++; // go to case 3
            }

            case 3 -> {
                Rectangle compactMaxCargo = new SegmentedRegions().getRectangle(this.resolution.getCompactMaxCargoList(), this.resolution.getCompactMaxCargoDeadZoneTuple());

                if (this.verifyRectangle(compactMaxCargo, "MAXCARGO_VENTURE", 0)) {
                    this.walkThrough = 5; // go to case 5 - docking and drag itens to main station

                } else {
                    if (this.flagUntilToBeFilledMS > STARTDRAGSCREEN_MS) {
                        new ClickScreenEvents().dragScreen();
                    }
                    this.walkThrough++; // go to case 4
                }
            }

            case 4 -> {
                this.flagSetAnotherAstMS = (System.currentTimeMillis() - this.timeStartSetAnotherAst);
                this.flagUntilToBeFilledMS = (System.currentTimeMillis() - this.timeStartFilled);
                this.flagDeactivePropulsionMS = (System.currentTimeMillis() - this.timeStartProp);

                // if: time to set another ast is exceeded or cannons was deactivated -> reset flag & mining 
                // else if: check approaching is true -> continue mining
                // else ship is not mining -> reset mining
                if (this.flagSetAnotherAstMS > SETANOTHERAST_MS && this.checkCannonsAction() == this.amountCannons) {
                    this.walkThrough = 0; // Time exceeded, restart to search for another asteroids

                } else if (this.checkPixelsAprroaching()) {
                    this.walkThrough--; // back to case and check maxCargo

                } else {
                    this.walkThrough = 0; // Approaching not found, the ship is not mining
                }

                // check propulsion
                if (this.flagDeactivePropulsionMS > DEACTIVEPROP_MS && this.isAnotherAst == true) {
                    this.propulsion();
                    this.isAnotherAst = false;
                }
            }

            case 5 -> {
                this.returnDrones(8000);
                new SetDestination(this.resolution.getHomeStationList(), GOTO_HOMESTATION, this.waitForWarp_MS).startScript();
                System.out.println("End of mining and go docking!\n");
                this.walkThrough++;
            }
        }
    }

    private boolean getAsteroids() throws IOException, TesseractException, AWTException, InterruptedException {

        /* Reset Y list coordinates to 1081y */
        List<Integer> closestOreList = Arrays.asList(1081, 1081, 1081, 1081, 1081);

        Entry<String, Rectangle> betterAteroid = null;
        this.priorityOreValue = 0;

        HashMap<String, Rectangle> rectResult = new SegmentedRegions().getAllOres(R1920x1080.getOVERVIEWMINING_X1(), R1920x1080.getOVERVIEWMINING_X2_W(),
                R1920x1080.getOVERVIEWMINING_Y1(), R1920x1080.getOVERVIEWMINING_Y2_H());

        System.out.println("Rectangle list size: " + rectResult.size() + "\n");

        if (!rectResult.isEmpty()) {

            for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                for (int i = (this.priorityList.size() - 1); i >= 0; i--) {

                    if (item.getKey().contains("P" + i) && this.priorityOreValue <= this.priorityList.get(i)) {
                        this.priorityOreValue = this.priorityList.get(i);

                        //System.out.println(item.getKey() + ": " + item.getValue().y + "y");
                        if (item.getValue().y <= closestOreList.get(i)) {
                            //System.out.println("Temporary better asteroid found: " + item + "\n");
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

        } else {
            if (this.isSwitchable) {
                System.out.println("NO ASTEROID FOUND, SWITCHING ASTEROID BELT.");
                this.returnDrones(0);
                Thread.sleep(WAITFORSWITCHASTBELT_MS);
                this.switchAstBelt();

            } else {
                System.out.println("CLOSEST, BETTER ASTEROID IS NULL. RETURNING TO HOME STATION");
                this.isRunnable = false;
                this.walkThrough = 5;
            }
        }
        return false;

    }

    private int checkCannonsAction() {
        List<Integer> cannons = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);
        int deactivedCannons = 0;

        for (int i = 0; i < cannons.size(); i++) {
            try {
                if (!this.isCannonActivated(i, this.giveAtry,
                        (Arrays.asList(R1920x1080.getF1CANNON1_X(), R1920x1080.getF2CANNON2_X())), R1920x1080.getFNCANNON_Y(),
                        R1920x1080.getCANNON_H1(), R1920x1080.getCANNON_W1(),
                        this.tonsOfGreen.getValue0(), this.tonsOfGreen.getValue1(), this.tonsOfGreen.getValue2())) {

                    new KeyboardEvents().clickKey(cannons.get(i));
                    System.out.println("\nCannon " + (i + 1) + " was deactived. Activating again.");
                    deactivedCannons++;
                }

            } catch (InterruptedException | AWTException ex) {
                Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return deactivedCannons;
    }

    private void activeCannons() {
        List<Integer> cannons = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);

        for (int i = 0; i < cannons.size(); i++) {
            try {
                if (this.isCannonActivated(i, this.giveAtry,
                        (Arrays.asList(R1920x1080.getF1CANNON1_X(), R1920x1080.getF2CANNON2_X())), R1920x1080.getFNCANNON_Y(),
                        R1920x1080.getCANNON_H1(), R1920x1080.getCANNON_W1(),
                        this.tonsOfGreen.getValue0(), this.tonsOfGreen.getValue1(), this.tonsOfGreen.getValue2())) {

                    new KeyboardEvents().clickKey(cannons.get(i));
                    Thread.sleep(CANNON_SLEEP);
                    new KeyboardEvents().clickKey(cannons.get(i));
                    System.out.println("The cannon was active. Press 2x cannon " + i);

                } else {
                    Thread.sleep(CANNON_SLEEP); // Wait if cannon was canceled
                    new KeyboardEvents().clickKey(cannons.get(i));
                    System.out.println("Just press 1x cannon " + i);
                }

            } catch (InterruptedException | AWTException ex) {
                Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean isCannonActivated(int i, int attempt, List<Integer> coordinatesX, int y, int width, int height, int red, int green, int blue) {

        boolean action;

        for (int j = 0; j < attempt; j++) {
            try {
                new TakeScreenshot().take2();

                action = new FindPixels().findByGreenColor(coordinatesX.get(i), y, width, height, red, green, blue);
                if (action) {
                    return true;
                }

            } catch (IOException ex) {
                Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
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

        boolean approaching = new FindPixels().findByColor(
                R1920x1080.getAPPROACHING_X1(), R1920x1080.getAPPROACHING_Y1(),
                R1920x1080.getAPPROACHING_W1(), R1920x1080.getAPPROACHING_H1(),
                new Triplet<>(255, 255, 255));

        if (approaching == true) {
            return true;
        }
        System.out.println("Rect not found (APRROACHING)\n");
        return false;
    }

    private void verifyInvalidTarget(List<Pair<Integer, Integer>> listWxHrects, int moe, String msg) throws IOException, TesseractException, AWTException, InterruptedException {
        boolean isClicked = false;

        try {
            Rectangle rect = new SegmentedRegions().getRectangle(listWxHrects, this.resolution.getInvalidTargetDeadZoneList());
            System.out.println("Invalid target found: " + rect.toString());

            if (new FindPixels().findByRangeColor(rect.x, rect.y, rect.width, rect.height, this.rgbr.getMinInfo(), this.rgbr.getMaxInfo())) {
                rect.y += moe;
                new ClickScreenEvents().leftClickCenterButton(rect);
                isClicked = true;
            }

            System.out.println(msg + "\n");
        } catch (NullPointerException ex) {
            //Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isClicked) {
            Rectangle locktarget = new SegmentedRegions().getRectangle(this.resolution.getLockTargetList(), this.resolution.getLockTargetDeadZoneTuple());

            if (locktarget != null) {
                System.out.println("Invalid locked target found: " + locktarget.toString());
                new ClickScreenEvents().leftClickCenterButton(locktarget);
                this.walkThrough = 0; //!!

            } else if (this.target != null) {
                int asteroidTarget = 36;
                this.target.x += asteroidTarget;
                boolean lockAsteroidTarget = new FindPixels().findByRangeColor(this.target.x, this.target.y, this.target.width, this.target.height, this.rgbr.getMinLockTarget(), this.rgbr.getMaxLockTarget());

                if (lockAsteroidTarget) {
                    new ClickScreenEvents().leftClickCenterButton(this.target);
                    this.walkThrough = 0; //!!
                }
            } else {
                this.walkThrough = 5;
                System.out.println("Invalid locked target not found, ending script.");
            }
        }
    }

    public void verifyShipLife() {
        try {
            int row = R1920x1080.getBEINGATTACKED_X1();
            int column = R1920x1080.getBEINGATTACKED_Y1();
            int width = R1920x1080.getBEINGATTACKED_W1();
            int height = R1920x1080.getBEINGATTACKED_H1();

            if (new FindPixels().findByRangeColor(row, column, width, height, this.rgbr.getMinBeingAttacked(), this.rgbr.getMaxBeingAttacked())) {
                new SoundAlert().start(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\soundlogs\\soundfiles\\attack1.wav", 1);
            }
        } catch (IOException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void switchAstBelt() {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\switchbelt.txt";
        int label = new TextLogs().readLine(path);

        try {
            switch (label) {
                case 1 -> {
                    new SetDestination(this.resolution.getAstBeltIIList(), GOTO_ASTBELT, this.waitForWarp_MS).startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 2 -> {
                    new SetDestination(this.resolution.getAstBeltIIIList(), GOTO_ASTBELT, this.waitForWarp_MS).startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 3 -> {
                    new SetDestination(this.resolution.getAstBeltIIIIList(), GOTO_ASTBELT, this.waitForWarp_MS).startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 4 -> {
                    new SetDestination(this.resolution.getAstBeltIIIIIList(), GOTO_ASTBELT, this.waitForWarp_MS).startScript();
                    new TextLogs().writeLine(path, 0); // return to home station
                }
                default -> {
                    System.out.println("Case returned 0, returning to HOME STATION.");
                    new TextLogs().writeLine(path, 1); // reset asteroid belts
                    this.walkThrough = 5;
                    isRunnable = false; // exit the script
                }
            }

        } catch (IOException | TesseractException | AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void propulsion() {
        try {
            new KeyboardEvents().clickKey(KeyEvent.VK_F3);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void launchDrones() {
        try {
            new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void engageDrones() {
        try {
            new KeyboardEvents().clickKey(KeyEvent.VK_F);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void returnDrones(int waitForDronesMS) {
        try {
            this.returnAndOrbitDrones();
            new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
            Thread.sleep(waitForDronesMS);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void returnAndOrbitDrones() {
        try {
            new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_ALT, KeyEvent.VK_R);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
