package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.findpixels.FindPixels;
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
import com.mycompany.crimsonproject.interfaces.VerifyRectangle;
import com.mycompany.crimsonproject.interfaces.VerifyRectangleColor;
import com.mycompany.crimsonproject.utils.RGBrange;
import java.util.List;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class SetDestination implements VerifyRectangle, VerifyRectangleColor {

    private final R1920x1080 resolution;
    private final RGBrange rgbr;
    private final FindPixels findPixels;
    private final ClickScreenEvents clickEvents;
    private final SegmentedRegions segmentedRegions;
    private final TakeScreenshot takeScreenshot;
    private final KeyboardEvents keyboardEvents;

    private int option;
    private Rectangle astBeltDest;
    private Rectangle homeStationDest;
    private List<Pair<Integer, Integer>> destination;

    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;
    private static final int HOMESTATION = 0;
    private static final int MININGBOT = 1;
    private static final int STEPS = 4;

    private final int waitForWarp_MS;
    private final boolean isCheckWarpable;
    private final Triplet<Integer, Integer, Integer> greaterThan;
    // it depends the amount of switch cases
    private int walkThrough = 0;

    /**
     * @param chosenDest is a list of Pair<Integer, Integer>
     * @param option is type int that choice wether the script will go to
     * station or asteroid belt
     * @param waitForWarp type int in milliseconds to sleep when warping between
     * belts or home station
     * @param isCheckWarpable true if is ckeckable to identify by pixels or
     * false by wait for warp in milliseconds
     * @param greaterThan a triplet of white range in RGB tons
     */
    public SetDestination(List<Pair<Integer, Integer>> chosenDest, int option, int waitForWarp, boolean isCheckWarpable, Triplet<Integer, Integer, Integer> greaterThan) {
        this.destination = chosenDest;
        this.option = option;
        this.waitForWarp_MS = waitForWarp;
        this.isCheckWarpable = isCheckWarpable;

        this.rgbr = new RGBrange();
        this.resolution = new R1920x1080();
        this.greaterThan = greaterThan;
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

    public void startScript() throws IOException, TesseractException, AWTException, InterruptedException {

        while (this.walkThrough <= STEPS) {
            // Call method
            // Todo connection lost

            // Call method
            this.takeScreenshot.take();

            // Call method
            this.flowScript();
        }
    }

    private void flowScript() throws InterruptedException, AWTException, IOException, TesseractException {

        boolean descentFlag = true;

        switch (this.walkThrough) {

            case 0 -> {
                if (this.openLocation()) {
                    this.walkThrough++;
                }
            }

            case 1 -> {
                this.astBeltDest = this.segmentedRegions.getRectangle(this.destination, this.resolution.getLocationTabDeadZoneTuple());

                if (this.option == MININGBOT && this.verifyRectangle(this.astBeltDest, "ASTEROID BELT", RIGHTCLICK)) {
                    this.walkThrough++;
                    descentFlag = false;

                } else {
                    this.homeStationDest = this.segmentedRegions.getRectangle(this.destination, this.resolution.getLocationTabDeadZoneTuple());

                    if (this.option == HOMESTATION && this.verifyRectangle(this.homeStationDest, "HOME STATION", RIGHTCLICK)) {
                        this.walkThrough++;
                        descentFlag = false;
                    }
                }

                /* Close location windows if doesnt find the MININGBOT1 or HOMESTATION1 */
                if (descentFlag) {
                    this.walkThrough--;
                    this.clickEvents.dragScreen();
                }
            }

            case 2 -> {
                Rectangle warpBlock = this.segmentedRegions.getRectangle(this.resolution.getWarpList(), this.getFunnelRectTuple(this.astBeltDest));

                if (this.option == MININGBOT && this.verifyRectangleColor(warpBlock, "WARPBLOCK", LEFTCLICK, this.rgbr.getMinDestination(), this.rgbr.getMaxDestination())) {
                    this.walkThrough++;
                    descentFlag = false;

                } else {
                    Rectangle dock = this.segmentedRegions.getRectangle(this.resolution.getDockList(), this.getFunnelRectTuple(this.homeStationDest));

                    if (this.option == HOMESTATION && this.verifyRectangleColor(dock, "DOCK", LEFTCLICK, this.rgbr.getMinDestination(), this.rgbr.getMaxDestination())) {
                        this.walkThrough++;
                        descentFlag = false;
                    }
                }

                /* back to case 1 and find the ATEROID BELT or HOME STATION to restart finding WITHIN/DOCK */
                if (descentFlag) {
                    this.walkThrough--;
                    this.clickEvents.dragScreen();
                }
            }

            case 3 -> {
                Rectangle closeButtonWindowLocation = this.segmentedRegions.getRectangle(this.resolution.getCloseLocationButtonList(), this.resolution.getLocationTabDeadZoneTuple());

                if (this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK)) {
                    this.walkThrough++;
                }
            }

            case 4 -> {
                if (this.isCheckWarpable) {
                    boolean isWarping = true;
                    Thread.sleep(5000);

                    while (isWarping) {
                        this.takeScreenshot.take2();
                        isWarping = this.findPixels.greaterThan(
                                R1920x1080.getCHECKPATH_X1(), R1920x1080.getCHECKPATH_Y1(),
                                R1920x1080.getCHECKPATH_W1(), R1920x1080.getCHECKPATH_H1(),
                                this.greaterThan.getValue0(), this.greaterThan.getValue1(), this.greaterThan.getValue2());
                    }

                    if (!isWarping) {
                        this.takeScreenshot.take2(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\warpable.png");
                    }
                    Thread.sleep(this.option == HOMESTATION ? 12000 : 1000);

                } else {
                    Thread.sleep(this.waitForWarp_MS); // Sleep until reach the destination
                }
                this.walkThrough++;
            }
        } // while
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

    private boolean openLocation() throws IOException, TesseractException, AWTException, InterruptedException {
        this.keyboardEvents.clickKey(KeyEvent.VK_L);
        return true;
    }

    @Override
    public boolean verifyRectangle(Rectangle rect, String itemName, int chosenClick) throws AWTException, InterruptedException {

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
    public boolean verifyRectangleColor(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> minRGB, Triplet<Integer, Integer, Integer> maxRGB) throws AWTException, InterruptedException, IOException {

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

}
