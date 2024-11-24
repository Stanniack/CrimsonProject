package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.handlers.NetworkConnectionHandler;
import com.mycompany.crimsonproject.handlers.SleeperHandler;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.resolutions.R1920x1080Small;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;
import com.mycompany.crimsonproject.utils.RGBrange;
import java.util.List;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import com.mycompany.crimsonproject.interfaces.RectangleAndColorVerifier;
import com.mycompany.crimsonproject.interfaces.RectangleVerifier;

/**
 *
 * @author Devmachine
 */
public class SetDestination implements RectangleVerifier, RectangleAndColorVerifier {

// Attributes related to graphical interface and screen manipulation
    private final R1920x1080Small resolution;
    private final RGBrange rgbr;
    private final FindPixels findPixels;
    private final ClickScreenEvents clickEvents;
    private final SegmentedRegions segmentedRegions;
    private final TakeScreenshot takeScreenshot;
    private final KeyboardEvents keyboardEvents;
    private final SleeperHandler sleeper;

// Attributes for destination and movement management
    private int option;
    private Rectangle astBeltDest;
    private Rectangle homeStationDest;
    private List<Pair<Integer, Integer>> destination;

// Constants for mouse click actions and destination types
    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;
    private static final int HOMESTATION = 0;
    private static final int MININGBOT = 1;

// Attributes related to warping and timing
    private final int waitForWarp_MS;
    private final boolean isCheckWarpable;
    private final Triplet<Integer, Integer, Integer> whiteRangeRGB;

// switch-case behaviour attributes
    private boolean isRunnable = true;
    private int walkThrough = 0;
    private static final int STEPS = 4;
    private NetworkConnectionHandler connectionHandler;

    /**
     * @param chosenDest is a list of Pair<Integer, Integer>
     * @param option is type int that choice wether the script will go to
     * station or asteroid belt
     * @param waitForWarp type int in milliseconds to sleep when warping between
     * belts or home station
     * @param isCheckWarpable true if is ckeckable to identify by pixels or
     * false by wait for warp in milliseconds
     * @param whiteRangeRGB a triplet of white range in RGB shades
     */
    public SetDestination(List<Pair<Integer, Integer>> chosenDest, int option, int waitForWarp, boolean isCheckWarpable,
            Triplet<Integer, Integer, Integer> whiteRangeRGB) {
        destination = chosenDest;
        this.option = option;
        waitForWarp_MS = waitForWarp;
        this.isCheckWarpable = isCheckWarpable;

        rgbr = new RGBrange();
        resolution = new R1920x1080Small();
        this.whiteRangeRGB = whiteRangeRGB;
        findPixels = new FindPixels();
        clickEvents = new ClickScreenEvents();
        segmentedRegions = new SegmentedRegions();
        takeScreenshot = new TakeScreenshot();
        keyboardEvents = new KeyboardEvents();
        connectionHandler = new NetworkConnectionHandler();
        sleeper = new SleeperHandler();
    }

    protected void setParameters(List<Pair<Integer, Integer>> chosenDest, int option) {
        destination = chosenDest;
        this.option = option;
        walkThrough = 0;
    }

    public boolean startScript() throws IOException, TesseractException, AWTException {

        while (walkThrough <= STEPS) {
            // If there is net, continue script
            if (connectionHandler.networkVerifier()) {
                takeScreenshot.take();
                flowScript();

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
                if (openLocation()) {
                    walkThrough++;
                }
            }

            case 1 -> {
                astBeltDest = segmentedRegions.getRectangle(destination, resolution.getLocationTabDeadZoneTuple());
                homeStationDest = segmentedRegions.getRectangle(destination, resolution.getLocationTabDeadZoneTuple());

                if (clickDestinationLabel(false, astBeltDest, MININGBOT, "ASTEROID BELT")
                        || clickDestinationLabel(false, astBeltDest, HOMESTATION, "HOME STATION")) {
                    walkThrough++;

                } else {
                    walkThrough--;
                    clickEvents.dragScreen();
                }
            }

            case 2 -> {
                Rectangle warpBlock = segmentedRegions.getRectangle(resolution.getWarpList(), getFunnelRectTuple(astBeltDest));
                Rectangle dock = segmentedRegions.getRectangle(resolution.getDockList(), getFunnelRectTuple(homeStationDest));

                if (clickDestinationLabel(true, warpBlock, MININGBOT, "WARP BLOCK")
                        || clickDestinationLabel(true, dock, HOMESTATION, "DOCK")) {
                    walkThrough++;

                } else {
                    walkThrough--;
                    clickEvents.dragScreen();
                }
            }

            case 3 -> {
                Rectangle closeButtonWindowLocation = segmentedRegions.getRectangle(resolution.getCloseLocationButtonList(), resolution.getLocationTabDeadZoneTuple());

                if (rectangleVerifier(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK)) {
                    walkThrough++;
                }
            }

            case 4 -> {
                checkWarpable();
                walkThrough++;
            }
        }
    }

    private boolean clickDestinationLabel(boolean isRectColorVerifier, Rectangle label, int option, String itemLabel)
            throws IOException, TesseractException, AWTException {
        return (this.option == option)
                && (isRectColorVerifier
                        ? rectangleAndColorVerifier(label, itemLabel, LEFTCLICK, rgbr.getMinDestination(), rgbr.getMaxDestination())
                        : rectangleVerifier(label, itemLabel, RIGHTCLICK));
    }

    private void checkWarpable() throws IOException {
        if (isCheckWarpable) {
            Long flagTimeWarp = System.currentTimeMillis();
            boolean isWarping = true;
            sleeper.sleep(5000);

            while (isWarping || (System.currentTimeMillis() - flagTimeWarp) < (waitForWarp_MS * 2)) {
                takeScreenshot.take2();
                isWarping = findPixels.greaterThan(R1920x1080Small.getCHECKPATH_X1(), R1920x1080Small.getCHECKPATH_Y1(),
                        R1920x1080Small.getCHECKPATH_W1(), R1920x1080Small.getCHECKPATH_H1(),
                        whiteRangeRGB.getValue0(), whiteRangeRGB.getValue1(), whiteRangeRGB.getValue2());
            }

            if (!isWarping) {
                takeScreenshot.take2(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\warpable.png");
            }
            sleeper.sleep(option == HOMESTATION ? 12000 : 1000);

        } else {
            sleeper.sleep(waitForWarp_MS); // Sleep until reach the destination
        }
    }

    private Quartet<Integer, Integer, Integer, Integer> getFunnelRectTuple(Rectangle rect) {
        try {
            int cursorLenght = 11;
            int tabDeadZoneW = 245;
            int tabDeadZoneH = 29;
            int x1 = rect.x + rect.width / 2 + cursorLenght;
            int x2W = x1 + tabDeadZoneW;
            int y1 = rect.y + rect.height / 2;
            int y2H = y1 + tabDeadZoneH;

            return new Quartet<>(x1, x2W, y1, y2H);

        } catch (NullPointerException ex) {
        }
        return new Quartet<>(0, 0, 0, 0);
    }

    private boolean openLocation() throws IOException, TesseractException, AWTException {
        keyboardEvents.clickKey(KeyEvent.VK_L);
        return true;
    }

    @Override
    public boolean rectangleVerifier(Rectangle rect, String itemName, int chosenClick) throws AWTException {
        if (rect != null) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);

            if (chosenClick == LEFTCLICK) {
                clickEvents.leftClickCenterButton(rect);
            } else {
                clickEvents.rightClickCenterButton(rect);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean rectangleAndColorVerifier(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> minRGB, Triplet<Integer, Integer, Integer> maxRGB)
            throws AWTException, IOException {
        if (rect != null && findPixels.findByRangeColor(rect.x, rect.y, rect.width, rect.height, minRGB, maxRGB)) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);

            if (chosenClick == LEFTCLICK) {
                clickEvents.leftClickCenterButton(rect);
            } else {
                clickEvents.rightClickCenterButton(rect);
            }
            return true;
        }
        return false;
    }
}
