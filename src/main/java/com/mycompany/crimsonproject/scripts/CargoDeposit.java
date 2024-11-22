package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.interfaces.NetworkConnectionVerifier;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;
import com.mycompany.crimsonproject.utils.RGBrange;
import org.javatuples.Triplet;
import com.mycompany.crimsonproject.interfaces.RectangleAndColorVerifier;
import com.mycompany.crimsonproject.interfaces.RectangleVerifier;
import com.mycompany.crimsonproject.utils.CalendarUtils;
import com.mycompany.crimsonproject.utils.HostTools;

/**
 *
 * @author Devmachine
 *
 */
public class CargoDeposit implements RectangleVerifier, RectangleAndColorVerifier, NetworkConnectionVerifier {

// Attributes related to graphical interface and screen manipulation
    private final R1920x1080 resolution;
    private final RGBrange rgbr;
    private final FindPixels findPixels;
    private final ClickScreenEvents clickEvents;
    private final SegmentedRegions segmentedRegions;
    private final TakeScreenshot takeScreenshot;

// Constants for mouse click actions
    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;

// Attributes related to specific UI elements
    private Rectangle hangarButton;

// switch-case behaviour attributes
    private boolean isRunnable = true;
    private int walkThrough = 0;
    private static final int STEPS = 2;

    public CargoDeposit() {
        this.resolution = new R1920x1080();
        this.rgbr = new RGBrange();

        this.findPixels = new FindPixels();
        this.clickEvents = new ClickScreenEvents();
        this.segmentedRegions = new SegmentedRegions();
        this.takeScreenshot = new TakeScreenshot();
    }

    public boolean startScript() throws InterruptedException, IOException, AWTException, TesseractException {

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

    private void flowScript() throws AWTException, InterruptedException, IOException, TesseractException {

        switch (this.walkThrough) {

            case 0 -> {
                this.hangarButton = this.segmentedRegions.getRectangle(this.resolution.getHangarList(), this.resolution.getInventoryDeadzoneTuple());

                if (this.rectangleAndColorVerifier(hangarButton, "HANGAR", 0, this.rgbr.getMinDestination(), this.rgbr.getMaxDestination())) {
                    this.walkThrough++;
                } else {
                    this.clickEvents.dragScreen();
                }
            }

            case 1 -> {
                this.dragItens();
                this.walkThrough++;
                this.hangarButton = null;
            }

            case 2 -> {
                Rectangle undockButton = this.segmentedRegions.getRectangle(this.resolution.getUndockButtonList(), this.resolution.getUndockDeadZoneTuple());

                if (this.rectangleVerifier(undockButton, "UNDOCKBUTTON", LEFTCLICK)) {
                    this.walkThrough++;
                } else {
                    this.clickEvents.dragScreen();
                }
            }

        } // end switch
    }

    private void dragItens() throws AWTException, InterruptedException {
        this.clickEvents.dragItemsToInventory(this.resolution.getDragItensDeadZoneList(), this.hangarButton);
    }

    @Override
    public boolean rectangleVerifier(Rectangle rectangle, String itemName, int chosenClick) throws AWTException, InterruptedException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rectangle != null) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d\n\n", itemName, rectangle.width, rectangle.height);

            if (chosenClick == LEFTCLICK) {
                this.clickEvents.leftClickCenterButton(rectangle);
            } else {
                this.clickEvents.rightClickCenterButton(rectangle);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean rectangleAndColorVerifier(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> minRGB, Triplet<Integer, Integer, Integer> maxRGB) throws AWTException, InterruptedException, IOException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rect != null && this.findPixels.findByRangeColor(rect.x, rect.y, rect.width, rect.height, minRGB, maxRGB)) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);
            return true;
        }
        return false;
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
