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

    private final int option;
    private R1920x1080 resolution = null;
    private final RGBrange rgbr;
    private Rectangle astBeltDest = null;
    private Rectangle homeStationDest = null;
    private final List<Pair<Integer, Integer>> destination;
    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;
    private static final int HOMESTATION = 0;
    private static final int MININGBOT = 1;
    private int waitForWarp_MS;
    // it depends the amount of switch cases
    private static final int STEPS = 4;

    private int walkThrough = 0;

    /**
     * @param chosenDest is a list of Pair<Integer, Integer>
     * @param option is type int that choice wether the script will go to
     * station or asteroid belt
     */
    public SetDestination(List<Pair<Integer, Integer>> chosenDest, int option, int waitForWarp) {
        this.destination = chosenDest;
        this.option = option;
        this.waitForWarp_MS = waitForWarp;
        this.rgbr = new RGBrange();
        this.resolution = new R1920x1080();
    }

    public void startScript() throws IOException, TesseractException, AWTException, InterruptedException {

        while (this.walkThrough < STEPS) {
            // Call method
            // Todo connection lost

            // Call method
            new TakeScreenshot().take();

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
                this.astBeltDest = new SegmentedRegions().getRectangle(this.destination, this.resolution.getTupleLocationTabDeadZone());

                if (this.option == MININGBOT && this.verifyRectangle(this.astBeltDest, "ASTEROID BELT", RIGHTCLICK)) {
                    this.walkThrough++;
                    descentFlag = false;

                } else {
                    this.homeStationDest = new SegmentedRegions().getRectangle(this.destination, this.resolution.getTupleLocationTabDeadZone());

                    if (this.option == HOMESTATION && this.verifyRectangle(this.homeStationDest, "HOME STATION", RIGHTCLICK)) {
                        this.walkThrough++;
                        descentFlag = false;
                    }
                }

                /* Close location windows if doesnt find the MININGBOT1 or HOMESTATION1 */
                if (descentFlag) {
                    this.walkThrough--;
                    new ClickScreenEvents().dragScreen();
                }
            }

            case 2 -> {
                Rectangle warpBlock = new SegmentedRegions().getRectangle(this.resolution.getWarpList(), this.getFunnelRectTuple(this.astBeltDest));

                if (this.option == MININGBOT && this.verifyRectangleColor(warpBlock, "WARPBLOCK", LEFTCLICK, this.rgbr.getMinDestinationRGB(), this.rgbr.getMaxDestinationRGB())) {
                    //System.out.println(new FindPixels().findByRangeColor(warpBlock.x, warpBlock.y, warpBlock.width, warpBlock.height, new PIXELRANGE().tupleMinDestinationRGB, new PIXELRANGE().tupleMaxDestinationRGB));
                    this.walkThrough++;
                    descentFlag = false;

                } else {
                    Rectangle dock = new SegmentedRegions().getRectangle(this.resolution.getDockList(), this.getFunnelRectTuple(this.homeStationDest));

                    if (this.option == HOMESTATION && this.verifyRectangleColor(dock, "DOCK", LEFTCLICK, this.rgbr.getMinDestinationRGB(), this.rgbr.getMaxDestinationRGB())) {
                        //System.out.println(new FindPixels().findByRangeColor(dock.x, dock.y, dock.width, dock.height, new PIXELRANGE().tupleMinDestinationRGB, new PIXELRANGE().tupleMaxDestinationRGB));
                        this.walkThrough++;
                        descentFlag = false;
                    }
                }

                /* back to case 1 and find the ATEROID BELT or HOME STATION to restart finding WITHIN/DOCK */
                if (descentFlag) {
                    this.walkThrough--;
                    new ClickScreenEvents().dragScreen();
                }

            }

            case 3 -> {
                Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(this.resolution.getCloseLocationButtonList(), this.resolution.getTupleLocationTabDeadZone());

                if (this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK)) {
                    this.walkThrough++;
                }
                
                // Sleep until reach the destination
                Thread.sleep(this.waitForWarp_MS);
            }

            /*case 4 -> {
                boolean isWarping = true;
                int waitForWarpMS = 5000;
                Thread.sleep(waitForWarpMS);

                while (isWarping) {
                    new TakeScreenshot().takeSRGB();
                    isWarping = new FindPixels().greaterThanRGB(
                            R1920x1080.getCHECKPATH_X1(), R1920x1080.getCHECKPATH_Y1(),
                            R1920x1080.getCHECKPATH_W1(), R1920x1080.getCHECKPATH_H1(),
                            255, 255, 255);
                    System.out.println(isWarping);
                }

                Thread.sleep(this.option == HOMESTATION ? 1000 : 1000);
                this.walkThrough++;
            }*/
        } // while
    }

    private Quartet<Integer, Integer, Integer, Integer> getFunnelRectTuple(Rectangle rect) {
        try {
            int cursorLenght = 11;
            int tabDeadZoneW = 245;
            int tabDeadZoneH = 29;
            int x1 = rect.x + rect.width / 2 + cursorLenght;
            int x2_w = x1 + tabDeadZoneW;
            int y1 = rect.y + rect.height / 2;
            int y2_h = y1 + tabDeadZoneH;

            return new Quartet<>(x1, x2_w, y1, y2_h);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return new Quartet<>(0, 0, 0, 0);
    }

    private boolean openLocation() throws IOException, TesseractException, AWTException, InterruptedException {
        new KeyboardEvents().clickKey(KeyEvent.VK_L);
        //System.out.println("Window location open by shortcut L\n");
        return true;
    }

    @Override
    public boolean verifyRectangle(Rectangle rect, String itemName, int chosenClick) throws AWTException, InterruptedException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rect != null) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);

            if (chosenClick == LEFTCLICK) {
                new ClickScreenEvents().leftClickCenterButton(rect);
            } else {
                new ClickScreenEvents().rightClickCenterButton(rect);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyRectangleColor(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> tupleBegin, Triplet<Integer, Integer, Integer> tupleEnd) throws AWTException, InterruptedException, IOException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rect != null && new FindPixels().findByRangeColor(rect.x, rect.y, rect.width, rect.height, tupleBegin, tupleEnd)) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);

            if (chosenClick == LEFTCLICK) {
                new ClickScreenEvents().leftClickCenterButton(rect);
            } else {
                new ClickScreenEvents().rightClickCenterButton(rect);
            }
            return true;
        }
        return false;
    }

}
