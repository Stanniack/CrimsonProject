package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.interfaces.NetworkConnectionVerifier;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
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
import com.mycompany.crimsonproject.interfaces.Sleeper;
import com.mycompany.crimsonproject.utils.CalendarUtils;
import com.mycompany.crimsonproject.utils.HostTools;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
public class SetDestination implements RectangleVerifier, RectangleAndColorVerifier, Sleeper, NetworkConnectionVerifier {

// Attributes related to graphical interface and screen manipulation
    private final R1920x1080 resolution;
    private final RGBrange rgbr;
    private final FindPixels findPixels;
    private final ClickScreenEvents clickEvents;
    private final SegmentedRegions segmentedRegions;
    private final TakeScreenshot takeScreenshot;
    private final KeyboardEvents keyboardEvents;

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
        this.destination = chosenDest;
        this.option = option;
        this.waitForWarp_MS = waitForWarp;
        this.isCheckWarpable = isCheckWarpable;

        this.rgbr = new RGBrange();
        this.resolution = new R1920x1080();
        this.whiteRangeRGB = whiteRangeRGB;
        this.findPixels = new FindPixels();
        this.clickEvents = new ClickScreenEvents();
        this.segmentedRegions = new SegmentedRegions();
        this.takeScreenshot = new TakeScreenshot();
        this.keyboardEvents = new KeyboardEvents();
    }

    protected void setParameters(List<Pair<Integer, Integer>> chosenDest, int option) {
        this.destination = chosenDest;
        this.option = option;
        this.walkThrough = 0;
    }

    public boolean startScript() throws IOException, TesseractException, AWTException {

        while (this.walkThrough <= STEPS) {
            // If there is net, continue script
            if (this.networkVerifier()) {
                this.takeScreenshot.take();
                this.flowScript();

            } else {
                this.isRunnable = false;
                break;
            }
        }
        return isRunnable;
    }

    private void flowScript() throws AWTException, IOException, TesseractException {

        switch (this.walkThrough) {

            case 0 -> {
                if (this.openLocation()) {
                    this.walkThrough++;
                }
            }

            case 1 -> {
                this.astBeltDest = this.segmentedRegions.getRectangle(this.destination, this.resolution.getLocationTabDeadZoneTuple());
                this.homeStationDest = this.segmentedRegions.getRectangle(this.destination, this.resolution.getLocationTabDeadZoneTuple());

                if (this.clickDestinationLabel(false, astBeltDest, MININGBOT, "ASTEROID BELT")
                        || this.clickDestinationLabel(false, astBeltDest, HOMESTATION, "HOME STATION")) {
                    this.walkThrough++;

                } else {
                    this.walkThrough--;
                    this.clickEvents.dragScreen();
                }
            }

            case 2 -> {
                Rectangle warpBlock = this.segmentedRegions.getRectangle(this.resolution.getWarpList(), this.getFunnelRectTuple(this.astBeltDest));
                Rectangle dock = this.segmentedRegions.getRectangle(this.resolution.getDockList(), this.getFunnelRectTuple(this.homeStationDest));

                if (this.clickDestinationLabel(true, warpBlock, MININGBOT, "WARP BLOCK")
                        || this.clickDestinationLabel(true, dock, HOMESTATION, "DOCK")) {
                    this.walkThrough++;

                } else {
                    this.walkThrough--;
                    this.clickEvents.dragScreen();
                }
            }

            case 3 -> {
                Rectangle closeButtonWindowLocation = this.segmentedRegions.getRectangle(this.resolution.getCloseLocationButtonList(), this.resolution.getLocationTabDeadZoneTuple());

                if (this.rectangleVerifier(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK)) {
                    this.walkThrough++;
                }
            }

            case 4 -> {
                this.checkWarpable();
                this.walkThrough++;
            }
        }
    }

    private boolean clickDestinationLabel(boolean isRectColorVerifier, Rectangle label, int option, String itemLabel)
            throws IOException, TesseractException, AWTException {
        return (this.option == option)
                && (isRectColorVerifier
                        ? this.rectangleAndColorVerifier(label, itemLabel, LEFTCLICK, this.rgbr.getMinDestination(), this.rgbr.getMaxDestination())
                        : this.rectangleVerifier(label, itemLabel, RIGHTCLICK));
    }

    private void checkWarpable() throws IOException {
        if (this.isCheckWarpable) {
            Long flagTimeWarp = System.currentTimeMillis();
            boolean isWarping = true;
            this.sleep(5000);

            while (isWarping || (System.currentTimeMillis() - flagTimeWarp) < (this.waitForWarp_MS * 2)) {
                this.takeScreenshot.take2();
                isWarping = this.findPixels.greaterThan(
                        R1920x1080.getCHECKPATH_X1(), R1920x1080.getCHECKPATH_Y1(),
                        R1920x1080.getCHECKPATH_W1(), R1920x1080.getCHECKPATH_H1(),
                        this.whiteRangeRGB.getValue0(), this.whiteRangeRGB.getValue1(), this.whiteRangeRGB.getValue2());
            }

            if (!isWarping) {
                this.takeScreenshot.take2(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\warpable.png");
            }
            this.sleep(this.option == HOMESTATION ? 12000 : 1000);

        } else {
            this.sleep(this.waitForWarp_MS); // Sleep until reach the destination
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
        this.keyboardEvents.clickKey(KeyEvent.VK_L);
        return true;
    }

    @Override
    public boolean rectangleVerifier(Rectangle rect, String itemName, int chosenClick) throws AWTException {
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rect != null) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);

            if (chosenClick == LEFTCLICK) {
                this.clickEvents.leftClickCenterButton(rect);
            } else {
                this.clickEvents.rightClickCenterButton(rect);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean rectangleAndColorVerifier(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> minRGB, Triplet<Integer, Integer, Integer> maxRGB)
            throws AWTException, IOException {
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rect != null && this.findPixels.findByRangeColor(rect.x, rect.y, rect.width, rect.height, minRGB, maxRGB)) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);

            if (chosenClick == LEFTCLICK) {
                this.clickEvents.leftClickCenterButton(rect);
            } else {
                this.clickEvents.rightClickCenterButton(rect);
            }
            return true;
        }
        return false;
    }

    @Override
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(SetDestination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean networkVerifier() {
        HostTools host = new HostTools();

        if (!host.checkHostConnection()) {
            CalendarUtils cu = new CalendarUtils();
            TextLogs textLogs = new TextLogs();
            String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\lostconnection.txt";
            String message = "Lost connection at " + cu.getDate();
            textLogs.createLogMessage(path, message);
            return false;
        }
        return true;
    }

}
