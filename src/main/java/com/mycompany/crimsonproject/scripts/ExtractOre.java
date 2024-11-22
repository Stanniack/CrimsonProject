package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.modules.ActionModules;
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
import com.mycompany.crimsonproject.interfaces.RectangleVerifier;

/**
 *
 * @author Devmachine
 */
public class ExtractOre implements RectangleVerifier {

    // Attributes related to graphical interface and screen manipulation
    private Rectangle target;
    private RGBrange rgbr = null;
    private R1920x1080 resolution = null;
    private ActionModules actModules;
    private FindPixels findPixels;
    private ClickScreenEvents clickEvents;
    private KeyboardEvents keyboardEvents;
    private SegmentedRegions segmentedRegions;
    private TakeScreenshot takeScreenshot;

    // Attributes for configurations and states
    private SetDestination setDestination;
    private boolean isRunnable = true;
    private boolean switchAstBelt;
    private boolean isAnotherAst;
    private int walkThrough = 0;

    // Attributes for time tracking and flags
    private int flagSwitchBelt = 0;
    private long flagSetAnotherAstMS = 0;
    private long flagUntilToBeFilledMS = 0;
    private long flagDeactivePropulsionMS = 0;
    private long flagLockTargetMS = 0;
    private long timeStartLockTarget = 0;
    private long timeStartSetAnotherAst = 0;
    private long timeStartFilled = 0;
    private long timeStartProp = 0;

    // Attributes for behavior and priority settings
    private Integer priorityOreValue;
    private final Integer CSpriority = 0;
    private final Integer Spriority = 1;
    private final Integer DVpriority = 2;
    private final Integer CVpriority = 3;
    private final Integer Vpriority = 4;
    private final List<Integer> priorityList = Arrays.asList(CSpriority, Spriority, DVpriority, CVpriority, Vpriority);

    // Attributes related to mining and combat
    private final int amountCannons = 2;
    private Triplet<Integer, Integer, Integer> shadeOfGreen;
    private int attempts;
    private int returnDronesMS = 0;
    private int waitEngageMS = 0;

    // Constants for program behavior
    private static final int LOCKTARGET_MS = 60000;
    private static final int WAITFORSWITCHASTBELT_MS = 10000;
    private static final int SETANOTHERAST_MS = 180000;
    private static final int DEACTIVEPROP_MS = 120000;
    private static final int STARTDRAGSCREEN_MS = 2200000;
    private static final int STEPS = 5;
    private static final int ASTNOTFOUND = 7;
    private static final int GOTO_HOMESTATION = 0;
    private static final int GOTO_ASTBELT = 1;
    private static final int CANNON_SLEEP = 1500;

    /**
     * @param setDestination is an instanced object containing some essentials
     * started attributed to recycle in ExtractOre class
     * @param switchAstBelt true if the script is enable to switch asteroid
     * belts
     * @param attempts number of tries to check if the miner cannons are
     * activated
     * @param shadesOfGreen a triplet of greem shades to check with attempts to
     * maximize the search for active miner cannons
     * @param returnDronesMS time in MS to wait drones returning to drone bay
     * @param waitEngageMS time in MS to wait for engane drones on the ast belt
     */
    public ExtractOre(SetDestination setDestination, boolean switchAstBelt, int attempts,
            Triplet<Integer, Integer, Integer> shadesOfGreen, int returnDronesMS, int waitEngageMS) {
        this.setDestination = setDestination;
        initializeSharedAttributes(switchAstBelt, attempts, shadesOfGreen, returnDronesMS, waitEngageMS);
    }

    public ExtractOre(boolean switchAstBelt, int attempts, Triplet<Integer, Integer, Integer> shadesOfGreen,
            int returnDronesMS, int waitEngageMS) {
        initializeSharedAttributes(switchAstBelt, attempts, shadesOfGreen, returnDronesMS, waitEngageMS);
    }

    private void initializeSharedAttributes(boolean switchAstBelt, int attempts, Triplet<Integer, Integer, Integer> shadesOfGreen,
            int returnDronesMS, int waitEngageMS) {
        this.switchAstBelt = switchAstBelt;
        this.attempts = attempts;
        this.returnDronesMS = returnDronesMS;
        this.waitEngageMS = waitEngageMS;
        this.shadeOfGreen = shadesOfGreen;

        this.rgbr = new RGBrange();
        this.resolution = new R1920x1080();
        this.actModules = new ActionModules();
        this.findPixels = new FindPixels();
        this.clickEvents = new ClickScreenEvents();
        this.keyboardEvents = new KeyboardEvents();
        this.segmentedRegions = new SegmentedRegions();
        this.takeScreenshot = new TakeScreenshot();
    }

    /**
     * Initialize all attributes required
     */
    private void initAttributes(boolean isSwitchable, int attempts, Triplet<Integer, Integer, Integer> shadesOfGreen, int returnDroneMS, int waitEngageMS) {

    }

    public boolean startScript() throws IOException, TesseractException, AWTException, InterruptedException {

        this.timeStartFilled = System.currentTimeMillis();

        while (this.walkThrough <= STEPS) {
            // Todo connection lost 
            // TODO

            // Call method priority MAX
            this.takeScreenshot.take();

            //Call method
            this.verifyShipLife();

            // Call method
            this.flowScript();

            // Call method
            this.verifyInvalidTarget(this.resolution.getInvalidTargetList());

            //Call method
            if (this.walkThrough > 2 && this.walkThrough < STEPS) {
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
                    this.actModules.propulsion();
                    this.walkThrough++;
                }
            } // end case 0

            case 1 -> {
                this.target = this.segmentedRegions.getRectangle(this.resolution.getLockTargetList(), this.resolution.getLockTargetDeadZoneTuple()); // Search for target

                if (this.rectangleVerifier(this.target, "TARGET", 0)) {
                    if (this.findPixels.findByRangeColor(this.target.x, this.target.y, this.target.width, this.target.height, this.rgbr.getMinLockTarget(), this.rgbr.getMaxLockTarget())) {
                        this.walkThrough++; // If there is a lock target, just go to next step

                    } else if (this.findPixels.findByRangeColor(this.target.x, this.target.y, this.target.width, this.target.height, this.rgbr.getMinFreeTarget(), this.rgbr.getMaxFreeTarget())) {
                        this.clickEvents.leftClickCenterButton(this.target); // If the target is free, click target and go next step
                        this.walkThrough++; // go to case 2

                    } else {
                        System.out.println("Lock target and free target not found.");
                        this.clickEvents.dragScreen();
                    }

                } else {
                    this.flagLockTargetMS = System.currentTimeMillis() - this.timeStartLockTarget;
                    //System.out.printf("Rect (LOCKTARGET) at case 2 not found. Time to restart the script: %d/%d\n\n", this.flagLockTarget_MS / 1000, LOCKTARGET_MS / 1000);
                    this.clickEvents.dragScreen();

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
                this.actModules.launchDrones();
                this.actModules.engageDrones(this.waitEngageMS); // engage drones
                this.walkThrough++; // go to case 3
            }

            case 3 -> {
                Rectangle compactMaxCargo = this.segmentedRegions.getRectangle(this.resolution.getCompactMaxCargoList(), this.resolution.getCompactMaxCargoDeadZoneTuple());

                if (this.rectangleVerifier(compactMaxCargo, "MAX CARGO", 0)) {
                    this.walkThrough = 5; // go to case 5 - docking and drag itens to main station

                } else {
                    if (this.flagUntilToBeFilledMS > STARTDRAGSCREEN_MS) {
                        this.clickEvents.dragScreen();
                    }
                    this.walkThrough++; // go to case 4
                }
            }

            case 4 -> {
                this.flagSetAnotherAstMS = (System.currentTimeMillis() - this.timeStartSetAnotherAst);
                this.flagUntilToBeFilledMS = (System.currentTimeMillis() - this.timeStartFilled);
                this.flagDeactivePropulsionMS = (System.currentTimeMillis() - this.timeStartProp);

                if (this.flagSetAnotherAstMS > SETANOTHERAST_MS && this.checkCannonsAction() == this.amountCannons) {//if: time to set another ast is exceeded or cannons was deactivated -> reset flag & mining 
                    this.walkThrough = 0; // Time exceeded, restart to search for another asteroids

                } else if (this.checkPixelsAprroaching()) {//else if: check approaching is true -> continue mining
                    this.walkThrough--; // back to case and check maxCargo

                } else {//else ship is not mining -> reset mining
                    this.walkThrough = 0; // Approaching not found, the ship is not mining
                }

                if (this.flagDeactivePropulsionMS > DEACTIVEPROP_MS && this.isAnotherAst == true) {//check propulsion
                    this.actModules.propulsion();
                    this.isAnotherAst = false;
                }
            }

            case 5 -> {
                this.actModules.returnDrones(this.returnDronesMS);
                this.setDestination.setParameters(this.resolution.getHomeStationList(), GOTO_HOMESTATION);
                this.setDestination.startScript();
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

        HashMap<String, Rectangle> rectResult = this.segmentedRegions.getAllOres(R1920x1080.getOVERVIEWMINING_X1(), R1920x1080.getOVERVIEWMINING_X2_W(),
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

                this.clickEvents.doubleClick(betterAteroid.getValue());
                return true;
            }

        } else {
            this.clickEvents.dragScreen();
            this.flagSwitchBelt++;

            if (this.flagSwitchBelt == ASTNOTFOUND) {

                if (this.switchAstBelt) {
                    System.out.println("NO ASTEROID FOUND, SWITCHING ASTEROID BELT.");
                    this.actModules.returnDrones(0);
                    Thread.sleep(WAITFORSWITCHASTBELT_MS);
                    this.switchAstBelt();

                } else {
                    System.out.println("CLOSEST, BETTER ASTEROID IS NULL. RETURNING TO HOME STATION");
                    this.isRunnable = false;
                    this.walkThrough = 5;
                }
                this.flagSwitchBelt = 0;
            }
        }
        return false;
    }

    private int checkCannonsAction() throws IOException {
        List<Integer> cannons = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);
        int deactivedCannons = 0;

        if (this.checkPixelsAprroaching()) {
            for (int i = 0; i < cannons.size(); i++) {
                try {
                    if (!this.isCannonActivated(i, this.attempts,
                            (Arrays.asList(R1920x1080.getF1CANNON1_X(), R1920x1080.getF2CANNON2_X())), R1920x1080.getFNCANNON_Y(),
                            R1920x1080.getCANNON_H1(), R1920x1080.getCANNON_W1(),
                            this.shadeOfGreen.getValue0(), this.shadeOfGreen.getValue1(), this.shadeOfGreen.getValue2())) {

                        this.keyboardEvents.clickKey(cannons.get(i));
                        System.out.println("\nCannon " + (i + 1) + " was deactived. Activating again.");
                        deactivedCannons++;
                    }

                } catch (InterruptedException | AWTException ex) {
                    Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deactivedCannons;
    }

    private void activeCannons() {
        List<Integer> cannons = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);

        for (int i = 0; i < cannons.size(); i++) {
            try {
                if (this.isCannonActivated(i, this.attempts,
                        (Arrays.asList(R1920x1080.getF1CANNON1_X(), R1920x1080.getF2CANNON2_X())), R1920x1080.getFNCANNON_Y(),
                        R1920x1080.getCANNON_H1(), R1920x1080.getCANNON_W1(),
                        this.shadeOfGreen.getValue0(), this.shadeOfGreen.getValue1(), this.shadeOfGreen.getValue2())) {

                    this.keyboardEvents.clickKey(cannons.get(i));
                    Thread.sleep(CANNON_SLEEP);
                    this.keyboardEvents.clickKey(cannons.get(i));
                    System.out.println("The cannon was active. Press 2x cannon " + i);

                } else {
                    Thread.sleep(CANNON_SLEEP); // Wait if cannon was canceled
                    this.keyboardEvents.clickKey(cannons.get(i));
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
                this.takeScreenshot.take2();
                action = this.findPixels.findByGreenColor(coordinatesX.get(i), y, width, height, red, green, blue);
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
    public boolean rectangleVerifier(Rectangle rectangle, String itemName, int chosenClick) throws AWTException, InterruptedException {

        if (rectangle != null) {
            System.out.printf("Rect found (%s) - Width: %d and Height: %d at coordinates (%d, %d)\n\n", itemName, rectangle.width, rectangle.height, rectangle.x, rectangle.y);
            return true;
        }
        return false;
    }

    private boolean checkPixelsAprroaching() throws IOException {

        boolean approaching = this.findPixels.findByColor(
                R1920x1080.getAPPROACHING_X1(), R1920x1080.getAPPROACHING_Y1(),
                R1920x1080.getAPPROACHING_W1(), R1920x1080.getAPPROACHING_H1(),
                255, 255, 255);

        if (approaching == true) {
            return true;
        }
        System.out.println("Rect not found (APRROACHING)\n");
        return false;
    }

    private void verifyInvalidTarget(List<Pair<Integer, Integer>> listWxHrects) throws IOException, TesseractException, AWTException, InterruptedException {
        boolean isClicked = false;
        int mOe = 195; // margin of error

        try {
            Rectangle rect = this.segmentedRegions.getRectangle(listWxHrects, this.resolution.getInvalidTargetDeadZoneList());
            System.out.println("Invalid target found: " + rect.toString());

            if (this.findPixels.findByRangeColor(rect.x, rect.y, rect.width, rect.height, this.rgbr.getMinInfo(), this.rgbr.getMaxInfo())) {
                rect.y += mOe;
                this.clickEvents.leftClickCenterButton(rect);
                isClicked = true;
            }

        } catch (NullPointerException ex) {
            //Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isClicked) {
            Rectangle locktarget = this.segmentedRegions.getRectangle(this.resolution.getLockTargetList(), this.resolution.getLockTargetDeadZoneTuple());

            if (locktarget != null) {
                System.out.println("Invalid locked target found: " + locktarget.toString());
                this.clickEvents.leftClickCenterButton(locktarget);
                this.walkThrough = 0; //!!

            } else if (this.target != null) {
                int asteroidTarget = 36;
                this.target.x += asteroidTarget;
                boolean lockAsteroidTarget = this.findPixels.findByRangeColor(this.target.x, this.target.y, this.target.width, this.target.height, this.rgbr.getMinLockTarget(), this.rgbr.getMaxLockTarget());

                if (lockAsteroidTarget) {
                    this.clickEvents.leftClickCenterButton(this.target);
                    this.walkThrough = 0; //!!
                }
            } else {
                this.walkThrough = 5;
                System.out.println("Invalid locked target not found, ending script.");
            }
        }
    }

    private void verifyShipLife() {
        try {
            int row = R1920x1080.getBEINGATTACKED_X1();
            int column = R1920x1080.getBEINGATTACKED_Y1();
            int width = R1920x1080.getBEINGATTACKED_W1();
            int height = R1920x1080.getBEINGATTACKED_H1();

            if (this.findPixels.findByRangeColor(row, column, width, height, this.rgbr.getMinBeingAttacked(), this.rgbr.getMaxBeingAttacked())) {
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
                    this.setDestination.setParameters(this.resolution.getAstBeltIIList(), GOTO_ASTBELT);
                    this.setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 2 -> {
                    this.setDestination.setParameters(this.resolution.getAstBeltIIIList(), GOTO_ASTBELT);
                    this.setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 3 -> {
                    this.setDestination.setParameters(this.resolution.getAstBeltIIIIList(), GOTO_ASTBELT);
                    this.setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 4 -> {
                    this.setDestination.setParameters(this.resolution.getAstBeltIIIIIList(), GOTO_ASTBELT);
                    this.setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1); // return to home station
                }
                case 5 -> {
                    System.out.println("Last asteroid belt farmed, returning to HOME STATION.");
                    new TextLogs().writeLine(path, 1); // reset asteroid belts
                    this.walkThrough = 5;
                    isRunnable = false; // exit the script
                }
            }

        } catch (IOException | TesseractException | AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
