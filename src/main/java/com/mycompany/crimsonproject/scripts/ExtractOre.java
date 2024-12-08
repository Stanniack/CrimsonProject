package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.handlers.NetworkConnectionHandler;
import com.mycompany.crimsonproject.handlers.SleeperHandler;
import com.mycompany.crimsonproject.handlers.WindowsServiceHandler;
import com.mycompany.crimsonproject.modules.ActionModules;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.soundlogs.SoundAlert;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.RGBrange;
import com.mycompany.crimsonproject.resolutions.R1920x1080Small;
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
    private R1920x1080Small resolution = null;
    private ActionModules actModules;
    private FindPixels findPixels;
    private ClickScreenEvents clickEvents;
    private KeyboardEvents keyboardEvents;
    private SegmentedRegions segmentedRegions;
    private TakeScreenshot takeScreenshot;
    private SleeperHandler sleeper;
    private WindowsServiceHandler wHandler;

// Attributes for configurations and states
    private SetDestination setDestination;
    private boolean isRunnable = true;
    private boolean switchAstBelt;
    private boolean isAnotherAst;
    private int walkThrough = 0;
    private NetworkConnectionHandler connectionHandler;

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

    /**
     * Initialize all attributes required
     */
    private void initializeSharedAttributes(boolean switchAstBelt, int attempts, Triplet<Integer, Integer, Integer> shadesOfGreen,
            int returnDronesMS, int waitEngageMS) {
        this.switchAstBelt = switchAstBelt;
        this.attempts = attempts;
        this.returnDronesMS = returnDronesMS;
        this.waitEngageMS = waitEngageMS;
        this.shadeOfGreen = shadesOfGreen;

        rgbr = new RGBrange();
        resolution = new R1920x1080Small();
        actModules = new ActionModules();
        findPixels = new FindPixels();
        clickEvents = new ClickScreenEvents();
        keyboardEvents = new KeyboardEvents();
        segmentedRegions = new SegmentedRegions();
        takeScreenshot = new TakeScreenshot();
        connectionHandler = new NetworkConnectionHandler();
        sleeper = new SleeperHandler();
        wHandler = new WindowsServiceHandler();
    }

    public boolean startScript() throws IOException, TesseractException, AWTException, InterruptedException {

        timeStartFilled = System.currentTimeMillis();

        while (walkThrough <= STEPS) {

            if (connectionHandler.networkVerifier()) { // if there is net, continue script
                wHandler.windowsChecker(500);
                takeScreenshot.take();
                verifyShipLife();
                flowScript();
                verifyInvalidTarget(resolution.getInvalidTargetList());

                if (walkThrough > 2 && walkThrough < STEPS) {
                    wHandler.windowsChecker(500);
                    checkCannonsAction();
                }
            } else {
                isRunnable = false;
                break;
            }
        }
        return isRunnable;
    }

    private void flowScript() throws AWTException, IOException, TesseractException {

        switch (walkThrough) {

            case 0 -> {
                if (getAsteroids()) {
                    timeStartLockTarget = System.currentTimeMillis();
                    timeStartProp = System.currentTimeMillis();
                    isAnotherAst = true;
                    actModules.propulsion();
                    walkThrough++;
                }
            } // end case 0

            case 1 -> {
                target = segmentedRegions.getRectangle(resolution.getLockTargetList(), resolution.getLockTargetDeadZoneTuple()); // Search for target

                if (rectangleVerifier(target, "TARGET", 0)) {
                    if (findPixels.findByRangeColor(target.x, target.y, target.width, target.height, rgbr.getMinLockTarget(), rgbr.getMaxLockTarget())) {
                        walkThrough++; // If there is a lock target, just go to next step

                    } else if (findPixels.findByRangeColor(target.x, target.y, target.width, target.height, rgbr.getMinFreeTarget(), rgbr.getMaxFreeTarget())) {
                        clickEvents.leftClickCenterButton(target); // If the target is free, click target and go next step
                        walkThrough++; // go to case 2

                    } else {
                        System.out.println("Lock target and free target not found.");
                        clickEvents.dragScreen();
                    }

                } else {
                    flagLockTargetMS = System.currentTimeMillis() - timeStartLockTarget;
                    //System.out.printf("Rect (LOCKTARGET) at case 2 not found. Time to restart the script: %d/%d\n\n", flagLockTargetMS / 1000, LOCKTARGET_MS / 1000);
                    clickEvents.dragScreen();

                    if (flagLockTargetMS > LOCKTARGET_MS) {
                        System.out.println("Restarting script.\n\n");
                        flagLockTargetMS = 0; // reset flag
                        walkThrough = 0; // reset script
                    }
                }
            }

            case 2 -> {
                timeStartSetAnotherAst = System.currentTimeMillis();
                activeCannons();
                actModules.launchDrones();
                actModules.engageDrones(waitEngageMS); // engage drones
                walkThrough++; // go to case 3
            }

            case 3 -> {
                Rectangle compactMaxCargo = segmentedRegions.getRectangle(resolution.getCompactMaxCargoList(), resolution.getCompactMaxCargoDeadZoneTuple());

                if (rectangleVerifier(compactMaxCargo, "MAX CARGO", 0)) {
                    walkThrough = 5; // go to case 5 - docking and drag items to main station

                } else {
                    if (flagUntilToBeFilledMS > STARTDRAGSCREEN_MS) {
                        clickEvents.dragScreen();
                    }
                    walkThrough++; // go to case 4
                }
            }

            case 4 -> {
                flagSetAnotherAstMS = (System.currentTimeMillis() - timeStartSetAnotherAst);
                flagUntilToBeFilledMS = (System.currentTimeMillis() - timeStartFilled);
                flagDeactivePropulsionMS = (System.currentTimeMillis() - timeStartProp);

                if (flagSetAnotherAstMS > SETANOTHERAST_MS && checkCannonsAction() == amountCannons) { // if: time to set another ast is exceeded or cannons were deactivated -> reset flag & mining 
                    walkThrough = 0; // Time exceeded, restart to search for another asteroids

                } else if (checkPixelsAprroaching()) { // else if: check approaching is true -> continue mining
                    walkThrough--; // back to case and check maxCargo

                } else { // else ship is not mining -> reset mining
                    walkThrough = 0; // Approaching not found, the ship is not mining
                }

                if (flagDeactivePropulsionMS > DEACTIVEPROP_MS && isAnotherAst) { // check propulsion
                    actModules.propulsion();
                    isAnotherAst = false;
                }
            }

            case 5 -> {
                actModules.returnDrones(returnDronesMS);
                setDestination.setParameters(resolution.getHomeStationList(), GOTO_HOMESTATION);
                setDestination.startScript();
                System.out.println("End of mining and go docking!\n");
                walkThrough++;
            }
        }
    }

    private boolean getAsteroids() throws IOException, TesseractException, AWTException {
        /* Reset Y list coordinates to 1081y */
        List<Integer> closestOreList = Arrays.asList(1081, 1081, 1081, 1081, 1081);

        Entry<String, Rectangle> betterAteroid = null;
        priorityOreValue = 0;

        HashMap<String, Rectangle> rectResult = segmentedRegions.getAllOres(R1920x1080Small.getOVERVIEWMINING_X1(), R1920x1080Small.getOVERVIEWMINING_X2_W(),
                R1920x1080Small.getOVERVIEWMINING_Y1(), R1920x1080Small.getOVERVIEWMINING_Y2_H());

        System.out.println("Rectangle list size: " + rectResult.size() + "\n");

        if (!rectResult.isEmpty()) {

            for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                for (int i = (priorityList.size() - 1); i >= 0; i--) {

                    if (item.getKey().contains("P" + i) && priorityOreValue <= priorityList.get(i)) {
                        priorityOreValue = priorityList.get(i);

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

                clickEvents.doubleClick(betterAteroid.getValue());
                return true;
            }

        } else {
            clickEvents.dragScreen();
            flagSwitchBelt++;

            if (flagSwitchBelt == ASTNOTFOUND) {

                if (switchAstBelt) {
                    System.out.println("NO ASTEROID FOUND, SWITCHING ASTEROID BELT.");
                    actModules.returnDrones(0);
                    sleeper.sleep(WAITFORSWITCHASTBELT_MS);
                    switchAstBelt();

                } else {
                    System.out.println("CLOSEST, BETTER ASTEROID IS NULL. RETURNING TO HOME STATION");
                    isRunnable = false;
                    walkThrough = 5;
                }
                flagSwitchBelt = 0;
            }
        }
        return false;
    }

    private int checkCannonsAction() throws IOException {
        List<Integer> cannons = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2);
        int deactivedCannons = 0;

        if (checkPixelsAprroaching()) {
            for (int i = 0; i < cannons.size(); i++) {
                try {
                    if (!isCannonActivated(i, attempts,
                            (Arrays.asList(R1920x1080Small.getF1CANNON1_X(), R1920x1080Small.getF2CANNON2_X())), R1920x1080Small.getFNCANNON_Y(),
                            R1920x1080Small.getCANNON_H1(), R1920x1080Small.getCANNON_W1(),
                            shadeOfGreen.getValue0(), shadeOfGreen.getValue1(), shadeOfGreen.getValue2())) {

                        keyboardEvents.clickKey(cannons.get(i));
                        System.out.println("\nCannon " + (i + 1) + " was deactivated. Activating again.");
                        deactivedCannons++;
                    }

                } catch (AWTException ex) {
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
                if (isCannonActivated(i, attempts,
                        (Arrays.asList(R1920x1080Small.getF1CANNON1_X(), R1920x1080Small.getF2CANNON2_X())), R1920x1080Small.getFNCANNON_Y(),
                        R1920x1080Small.getCANNON_H1(), R1920x1080Small.getCANNON_W1(),
                        shadeOfGreen.getValue0(), shadeOfGreen.getValue1(), shadeOfGreen.getValue2())) {

                    keyboardEvents.clickKey(cannons.get(i));
                    sleeper.sleep(CANNON_SLEEP);
                    keyboardEvents.clickKey(cannons.get(i));
                    System.out.println("The cannon was active. Press 2x cannon " + i);

                } else {
                    sleeper.sleep(CANNON_SLEEP); // Wait if cannon was canceled
                    keyboardEvents.clickKey(cannons.get(i));
                    System.out.println("Just press 1x cannon " + i);
                }

            } catch (AWTException ex) {
                Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean isCannonActivated(int i, int attempt, List<Integer> coordinatesX, int y, int width, int height, int red, int green, int blue) {
        boolean action;

        for (int j = 0; j < attempt; j++) {
            try {
                takeScreenshot.take2();
                action = findPixels.findByGreenColor(coordinatesX.get(i), y, width, height, red, green, blue);
                if (action) {
                    return true;
                }

            } catch (IOException ex) {
                Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private boolean checkPixelsAprroaching() throws IOException {

        boolean approaching = findPixels.findByColor(R1920x1080Small.getAPPROACHING_X1(), R1920x1080Small.getAPPROACHING_Y1(),
                R1920x1080Small.getAPPROACHING_W1(), R1920x1080Small.getAPPROACHING_H1(),
                255, 255, 255);

        if (approaching == true) {
            return true;
        }
        System.out.println("Rect not found (APRROACHING)\n");
        return false;
    }

    private void verifyInvalidTarget(List<Pair<Integer, Integer>> listWxHrects) throws IOException, TesseractException, AWTException {
        boolean isClicked = false;
        int mOe = 195; // margin of error

        try {
            Rectangle rect = segmentedRegions.getRectangle(listWxHrects, resolution.getInvalidTargetDeadZoneList());
            System.out.println("Invalid target found: " + rect.toString());

            if (findPixels.findByRangeColor(rect.x, rect.y, rect.width, rect.height, rgbr.getMinInfo(), rgbr.getMaxInfo())) {
                rect.y += mOe;
                clickEvents.leftClickCenterButton(rect);
                isClicked = true;
            }

        } catch (NullPointerException ex) {
            //Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isClicked) {
            Rectangle locktarget = segmentedRegions.getRectangle(resolution.getLockTargetList(), resolution.getLockTargetDeadZoneTuple());

            if (locktarget != null) {
                System.out.println("Invalid locked target found: " + locktarget.toString());
                clickEvents.leftClickCenterButton(locktarget);
                walkThrough = 0; //!!

            } else if (target != null) {
                int asteroidTarget = 36;
                target.x += asteroidTarget;
                boolean lockAsteroidTarget = findPixels.findByRangeColor(target.x, target.y, target.width, target.height, rgbr.getMinLockTarget(), rgbr.getMaxLockTarget());

                if (lockAsteroidTarget) {
                    clickEvents.leftClickCenterButton(target);
                    walkThrough = 0; //!!
                }
            } else {
                walkThrough = 5;
                System.out.println("Invalid locked target not found, ending script.");
            }
        }
    }

    private void verifyShipLife() {
        try {
            int row = R1920x1080Small.getBEINGATTACKED_X1();
            int column = R1920x1080Small.getBEINGATTACKED_Y1();
            int width = R1920x1080Small.getBEINGATTACKED_W1();
            int height = R1920x1080Small.getBEINGATTACKED_H1();

            if (findPixels.findByRangeColor(row, column, width, height, rgbr.getMinBeingAttacked(), rgbr.getMaxBeingAttacked())) {
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
                    setDestination.setParameters(resolution.getAstBeltIIList(), GOTO_ASTBELT);
                    setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 2 -> {
                    setDestination.setParameters(resolution.getAstBeltIIIList(), GOTO_ASTBELT);
                    setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 3 -> {
                    setDestination.setParameters(resolution.getAstBeltIIIIList(), GOTO_ASTBELT);
                    setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1);
                }
                case 4 -> {
                    setDestination.setParameters(resolution.getAstBeltIIIIIList(), GOTO_ASTBELT);
                    setDestination.startScript();
                    new TextLogs().writeLine(path, label + 1); // return to home station
                }
                case 5 -> {
                    System.out.println("Last asteroid belt farmed, returning to HOME STATION.");
                    new TextLogs().writeLine(path, 1); // reset asteroid belts
                    walkThrough = 5;
                    isRunnable = false; // exit the script
                }
            }

        } catch (IOException | TesseractException | AWTException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean rectangleVerifier(Rectangle rectangle, String itemName, int chosenClick) {

        if (rectangle != null) {
            System.out.printf("Rect found (%s) - Width: %d and Height: %d at coordinates (%d, %d)\n\n", itemName, rectangle.width, rectangle.height, rectangle.x, rectangle.y);
            return true;
        }
        return false;
    }
}
