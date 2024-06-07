package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.TestFullhd;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;
import com.mycompany.crimsonproject.interfaces.VerifyRectangle;
import com.mycompany.crimsonproject.interfaces.VerifyRectangleColor;
import com.mycompany.crimsonproject.utils.RGBrange;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class SetDestination implements VerifyRectangle, VerifyRectangleColor {

    private TestFullhd fhd = null;
    private RGBrange rgbr;
    private Rectangle miningBotLabel = null;
    private Rectangle homeStationLabel = null;
    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;
    private static final int HOMESTATION = 0;
    private static final int MININGBOT = 1;
    // it depends the amount of switch cases
    private static final int SWITCHFLAG = 4;

    private int amountRect = 0;

    public SetDestination() {
        this.rgbr = new RGBrange();
        this.fhd = new TestFullhd();
    }

    public void startScript(int option) throws IOException, TesseractException, AWTException, InterruptedException {

        do {

            boolean descentFlag = true;
            new TakeScreenShot().take();

            switch (this.amountRect) {

                case 0 -> {
                    if (this.openLocation()) {
                        this.amountRect++;
                    }

                } // end case 0

                case 1 -> {
                    this.miningBotLabel = new SegmentedRegions().getRectangle(this.fhd.getMiningBotWxH(), this.fhd.getTupleLocationTabDeadZone());

                    if (option == MININGBOT && this.verifyRectangle(miningBotLabel, "MININGBOT1", RIGHTCLICK)) {
                        this.amountRect++;
                        descentFlag = false;

                    } else {
                        this.homeStationLabel = new SegmentedRegions().getRectangle(this.fhd.getHomeStationWxHlist(), this.fhd.getTupleLocationTabDeadZone());

                        if (option == HOMESTATION && this.verifyRectangle(homeStationLabel, "HOMESTATION1", RIGHTCLICK)) {
                            this.amountRect++;
                            descentFlag = false;
                        }
                    }

                    /* Close location windows if doesnt find the MININGBOT1 or HOMESTATION1 */
                    if (descentFlag) {
                        Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(this.fhd.getCloseLocationButtonWxHlist(), this.fhd.getTupleLocationTabDeadZone());
                        this.amountRect--;
                        this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK);
                        new ClickScreenEvents().dragScreen();
                    }

                } // end case 1

                case 2 -> {
                    Rectangle warpBlock = new SegmentedRegions().getRectangle(this.fhd.getWarpWxHlist(), this.getRectTuple(this.miningBotLabel));

                    if (option == MININGBOT && this.verifyRectangleColor(warpBlock, "WARPBLOCK", LEFTCLICK, this.rgbr.getMinDestinationRGB(), this.rgbr.getMaxDestinationRGB())) {
                        //System.out.println(new FindPixels().findRangeColor(warpBlock.x, warpBlock.y, warpBlock.width, warpBlock.height, new PIXELRANGE().tupleMinDestinationRGB, new PIXELRANGE().tupleMaxDestinationRGB));
                        this.amountRect++;
                        descentFlag = false;

                    } else {
                        Rectangle dock = new SegmentedRegions().getRectangle(this.fhd.getDockWxHlist(), this.getRectTuple(this.homeStationLabel));

                        if (option == HOMESTATION && this.verifyRectangleColor(dock, "DOCK", LEFTCLICK, this.rgbr.getMinDestinationRGB(), this.rgbr.getMaxDestinationRGB())) {
                            //System.out.println(new FindPixels().findRangeColor(dock.x, dock.y, dock.width, dock.height, new PIXELRANGE().tupleMinDestinationRGB, new PIXELRANGE().tupleMaxDestinationRGB));
                            this.amountRect++;
                            descentFlag = false;
                        }
                    }

                    // back to case 1 and find the MININGBOT1 or HOMESTATION1 to restart finding WITHIN/DOCK
                    if (descentFlag) {
                        Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(this.fhd.getCloseLocationButtonWxHlist(), this.fhd.getTupleLocationTabDeadZone());
                        this.amountRect--;
                        this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK);
                        new ClickScreenEvents().dragScreen();
                    }

                } // end case 2

                case 3 -> {
                    Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(this.fhd.getCloseLocationButtonWxHlist(), this.fhd.getTupleLocationTabDeadZone());

                    if (this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK)) {
                        this.amountRect++;
                    }

                }

            } // end case 3

        } while (this.amountRect < SWITCHFLAG);
    }

    private Quartet<Integer, Integer, Integer, Integer> getRectTuple(Rectangle rect) {
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

        new KeyboardEvents().pressKey(KeyEvent.VK_L);
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
        if (rect != null && new FindPixels().findRangeColor(rect.x, rect.y, rect.width, rect.height, tupleBegin, tupleEnd)) {
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
