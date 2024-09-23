package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;
import com.mycompany.crimsonproject.interfaces.VerifyRectangle;
import com.mycompany.crimsonproject.interfaces.VerifyRectangleColor;
import com.mycompany.crimsonproject.utils.RGBrange;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 *
 */
public class CargoDeposit implements VerifyRectangle, VerifyRectangleColor {

    private final R1920x1080 resolution;
    private final RGBrange rgbr;

    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;

    private Rectangle hangarButton;
    private int walkThrough = 0;
    private static final int STEPS = 3;

    public CargoDeposit() {
        this.resolution = new R1920x1080();
        this.rgbr = new RGBrange();
    }

    public void startScript() throws InterruptedException, IOException, AWTException, TesseractException {

        while (this.walkThrough < STEPS) {
            // Call method
            // Todo connection lost

            // Call method
            new TakeScreenshot().take();

            // Call method
            this.flowScript();
        }

    }

    private void flowScript() throws AWTException, InterruptedException, IOException, TesseractException {

        switch (this.walkThrough) {

            case 0 -> {
                this.hangarButton = new SegmentedRegions().getRectangle(this.resolution.getHangarList(), this.resolution.getInventoryDeadzoneTuple());

                if (this.verifyRectangleColor(hangarButton, "HANGAR", 0, this.rgbr.getMinDestination(), this.rgbr.getMaxDestination())) {
                    this.walkThrough++;
                } else {
                    new ClickScreenEvents().dragScreen();
                }
            }

            case 1 -> {
                this.dragItens();
                this.walkThrough++;
                this.hangarButton = null;
            }

            case 2 -> {
                Rectangle undockButton = new SegmentedRegions().getRectangle(this.resolution.getUndockButtonList(), this.resolution.getUndockDeadZoneTuple());

                if (this.verifyRectangle(undockButton, "UNDOCKBUTTON", LEFTCLICK)) {
                    this.walkThrough++;
                } else {
                    new ClickScreenEvents().dragScreen();
                }
            }

        } // end switch
    }

    private void dragItens() throws AWTException, InterruptedException {
        new ClickScreenEvents().dragItemsToInventory(this.resolution.getDragItensDeadZoneList(), this.hangarButton);
    }

    @Override
    public boolean verifyRectangle(Rectangle rectangle, String itemName, int chosenClick) throws AWTException, InterruptedException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rectangle != null) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d\n\n", itemName, rectangle.width, rectangle.height);

            if (chosenClick == LEFTCLICK) {
                new ClickScreenEvents().leftClickCenterButton(rectangle);
            } else {
                new ClickScreenEvents().rightClickCenterButton(rectangle);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyRectangleColor(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> minRGB, Triplet<Integer, Integer, Integer> maxRGB) throws AWTException, InterruptedException, IOException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rect != null && new FindPixels().findByRangeColor(rect.x, rect.y, rect.width, rect.height, minRGB, maxRGB)) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);
            return true;
        }
        return false;
    }

} // end class
