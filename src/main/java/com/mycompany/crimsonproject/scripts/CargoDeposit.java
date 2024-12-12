package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.IOlogs.JsonLogs;
import com.mycompany.crimsonproject.handlers.NetworkConnectionHandler;
import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.handlers.WindowsServiceHandler;
import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.resolutions.R1920x1080Small;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;
import com.mycompany.crimsonproject.utils.RGBrange;
import org.javatuples.Triplet;
import com.mycompany.crimsonproject.interfaces.RectangleAndColorVerifier;
import com.mycompany.crimsonproject.interfaces.RectangleAndOcrVerifier;
import com.mycompany.crimsonproject.interfaces.RectangleVerifier;
import com.mycompany.crimsonproject.keywords.OriginalWords;
import com.mycompany.crimsonproject.keywords.SmallWords;
import org.javatuples.Pair;

/**
 *
 * @author Devmachine
 *
 */
public class CargoDeposit implements RectangleVerifier, RectangleAndColorVerifier, RectangleAndOcrVerifier {

// Attributes related to graphical interface and screen manipulation
    private final R1920x1080Small resolution;
    private final RGBrange rgbr;
    private final FindPixels findPixels;
    private final ClickScreenEvents clickEvents;
    private final SegmentedRegions segmentedRegions;
    private final TakeScreenshot takeScreenshot;

// Constants for mouse click actions
    private static final int RIGHTCLICK = 0;
    private static final int NONCLICK = -1;
    private static final int LEFTCLICK = 1;

// Attributes related to specific UI elements
    private Pair<Rectangle, String> hangarButton;

// switch-case behaviour attributes
    private boolean isRunnable = true;
    private final boolean isStaticClicker = true;
    private int walkThrough = 0;
    private static final int STEPS = 2;
    private final NetworkConnectionHandler connectionHandler;
    private final JsonLogs jsonLogs;
    private final WindowsServiceHandler wHandler;
    private final OriginalWords ocrWords;

    public CargoDeposit() {
        resolution = new R1920x1080Small(); // construtor
        rgbr = new RGBrange();

        findPixels = new FindPixels();
        clickEvents = new ClickScreenEvents();
        segmentedRegions = new SegmentedRegions();
        takeScreenshot = new TakeScreenshot();
        connectionHandler = new NetworkConnectionHandler();
        jsonLogs = new JsonLogs();
        wHandler = new WindowsServiceHandler();
        ocrWords = new SmallWords(); // construtor

    }

    public boolean startScript() throws InterruptedException, IOException, AWTException, TesseractException {

        while (walkThrough <= STEPS) {

            if (connectionHandler.networkVerifier()) { // if there is net, continue script
                wHandler.windowsChecker(500);
                takeScreenshot.take();
                flowScript();

            } else {
                isRunnable = false;
                break;
            }
        }
        return isRunnable;
    }

    private void flowScript() throws AWTException, InterruptedException, IOException, TesseractException {

        switch (walkThrough) {

            case 0 -> {
                hangarButton = segmentedRegions.getOcrRectangle(resolution.getHangarList(), resolution.getInventoryDeadzoneTuple());

                if (rectangleAndOcrVerifier(hangarButton, ocrWords.getHangar(), "HANGAR", NONCLICK)) {
                    walkThrough++;
                } else {
                    clickEvents.dragScreen();
                }
            }

            case 1 -> {
                dragItens();
                walkThrough++;
                hangarButton = null;
            }

            case 2 -> {
                Rectangle undockButton = segmentedRegions.getRectangle(resolution.getUndockButtonList(), resolution.getUndockDeadZoneTuple());

                if ((isStaticClicker && staticChecker(undockButton)) || dynamicChecker(undockButton)) {
                    walkThrough++;
                } else {
                    clickEvents.dragScreen();
                }
            }

        } // end switch
    }

    private void dragItens() throws AWTException, InterruptedException {
        clickEvents.dragItemsToInventory(resolution.getDragItensDeadZoneList(), hangarButton.getValue0());
    }

    @Override
    public boolean rectangleVerifier(Rectangle rectangle, String itemName, int chosenClick) throws AWTException, InterruptedException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rectangle != null) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d\n\n", itemName, rectangle.width, rectangle.height);

            if (chosenClick == LEFTCLICK) {
                clickEvents.leftClickCenterButton(rectangle);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean rectangleAndOcrVerifier(Pair<Rectangle, String> pair, String ocrMatch, String itemName, int chosenClick) throws AWTException, InterruptedException {

        if (pair != null && pair.getValue1().equals(ocrMatch)) {
            Rectangle rect = pair.getValue0();
            String ocrRect = pair.getValue1();
            System.out.printf("Rect found (%s) - Width: %d, Height: %d and its OCR: %s\n\n", itemName, rect.width, rect.height, ocrRect);

            if (chosenClick == LEFTCLICK) {
                clickEvents.leftClickCenterButton(rect);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean rectangleAndColorVerifier(Rectangle rect, String itemName, int chosenClick, Triplet<Integer, Integer, Integer> minRGB, Triplet<Integer, Integer, Integer> maxRGB) throws AWTException, InterruptedException, IOException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        if (rect != null && findPixels.findByRangeColor(rect.x, rect.y, rect.width, rect.height, minRGB, maxRGB)) {
            System.out.printf("Rect found (%s): Width: %d and Height: %d - (%d, %d)\n\n", itemName, rect.width, rect.height, rect.x, rect.y);
            return true;
        }
        return false;
    }

    private boolean isClickerDeprecated(String clicker) {
        return jsonLogs.readClicker(clicker) == null || (boolean) jsonLogs.readClicker(clicker).get("deprecated");
    }

    private boolean saveClicker(Rectangle rect, String clicker) {
        return jsonLogs.saveClicker(rect, clicker);
    }

    private Rectangle getClicker(String clicker) {
        return jsonLogs.getClicker(clicker);
    }

    private boolean staticChecker(Rectangle undockButton) throws IOException, TesseractException, AWTException, InterruptedException {
        // If true, search a clicker and save it
        if (isClickerDeprecated("undock")) {

            // if true save clicker to another cycle, else there is a clicker saved
            if (dynamicChecker(undockButton)) {
                saveClicker(undockButton, "undock");
                return true;
            }

        } else {
            return rectangleVerifier(this.getClicker("undock"), "STATIC CLICKER UNDOCK BUTTON", LEFTCLICK);
        }
        return false;
    }

    private boolean dynamicChecker(Rectangle undockButton) throws IOException, TesseractException, AWTException, InterruptedException {
        return rectangleVerifier(undockButton, "DYNAMICALLY IDENTIFIED UNDOCK BUTTON", LEFTCLICK);
    }

}
