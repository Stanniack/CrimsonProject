/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreen;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.Rect1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Mateus
 */
public class GetDestination {

    private static final int HOMESTATION = 0;
    private static final int MININGBOT = 1;

    public void getDestination(int option) throws IOException, TesseractException, AWTException, InterruptedException {
        int amountRect = 0;
        // it depends the amount of switch cases
        int switchFlag = 4;

        do {
            boolean flagNoDragScreen = false;
            new TakeScreenShot2().take();

            switch (amountRect) {

                case 0 -> {

                    if (this.locationButton()) {
                        flagNoDragScreen = true;
                        amountRect++;
                    }
                }

                case 1 -> {

                    if (option == MININGBOT) {
                        if (this.miningbot1Label()) {
                            flagNoDragScreen = true;
                            amountRect++;
                        }

                    } else if (option == HOMESTATION) {
                        if (this.homestationLabel()) {
                            flagNoDragScreen = true;
                            amountRect++;
                        }

                    }

                    /* Close location windows if doesnt find the MININGBOT1 or HOMESTATION1 */
                    if (!flagNoDragScreen) {
                        this.closeLocationWindow();
                        amountRect--; // return to case 1 to open the location windows again
                    }
                }

                case 2 -> {

                    if (option == MININGBOT) {
                        if (this.warpArrow()) {
                            flagNoDragScreen = true;
                            amountRect++;
                        }

                    } else if (option == HOMESTATION) {
                        if (this.dockArrow()) {
                            flagNoDragScreen = true;
                            amountRect++;
                        }

                    }

                    // back to case 1 and find the MININGBOT1 or HOMESTATION1  to restart finding WARPARROW/DOCKARROW
                    if (!flagNoDragScreen) {
                        this.closeLocationWindow();
                        amountRect--;
                    }
                }

                case 3 -> {

                    if (this.closeLocationWindow()) {
                        flagNoDragScreen = true;
                        amountRect++;
                    }

                }
            }

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

        } while (amountRect < switchFlag);
    }

    private boolean locationButton() throws IOException, TesseractException, AWTException, InterruptedException {
        /* Disable "Help EVE" button because its attribute have same width and height to Localization button
                       Location symbol must be the last shortcut in fixed hub on right side with min scale hud  */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle locationButton = sr3
                .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.LOCATIONSYMBOL_WIDTH1, Rect1920x1080.LOCATIONSYMBOL_HEIGHT1,
                        Rect1920x1080.LOCATIONSYMBOL_X, Rect1920x1080.LOCATIONSYMBOL_X2_W_BLOCKSCREEN,
                        Rect1920x1080.LOCATIONSYMBOL_Y, Rect1920x1080.LOCATIONSYMBOL_Y2_H_BLOCKSCREEN);

        if (locationButton != null) {
            System.out.printf("Rect found (LOCATIONSYNMBOL) case 0 - Width: %d and height: %d\n\n", locationButton.width, locationButton.height);
            new ClickScreen().leftClickCenterButton(locationButton);
            return true;

        } else {
            System.out.println("Rect not found (LOCATIONSYNMBOL) case 0");
            return false;
        }
    }

    private boolean miningbot1Label() throws IOException, TesseractException, AWTException, InterruptedException {
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle miningBot1Label = sr3
                .getSegmentedRegion_3Wx2H_BLOCKSCREEN(Rect1920x1080.MININGBOT1_WIDTH1,
                        Rect1920x1080.MININGBOT1_WIDTH2,
                        Rect1920x1080.MININGBOT1_WIDTH3,
                        Rect1920x1080.MININGBOT1_HEIGHT1,
                        Rect1920x1080.MININGBOT1_HEIGHT2,
                        Rect1920x1080.MININGBOT1_X, Rect1920x1080.MININGBOT1_X2_W_BLOCKSCREEN,
                        Rect1920x1080.MININGBOT1_Y, Rect1920x1080.MININGBOT1_Y2_H_BLOCKSCREEN);

        if (miningBot1Label != null) {
            System.out.printf("Rect found (MININGBOT1) case 1 - Width: %d and height: %d\n\n", miningBot1Label.width, miningBot1Label.height);
            new ClickScreen().rightClickCenterButton(miningBot1Label);
            return true;

        } else {
            System.out.println("Rect not found (MININGBOT1) case 1");
            return false;
        }
    }

    //(74, 9); (75, 9)
    private boolean homestationLabel() throws IOException, TesseractException, AWTException, InterruptedException {
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle homeStationLabel = sr3
                .getSegmentedRegion_2WxH_BLOCKSCREEN(Rect1920x1080.HOMESTATION1_WIDTH1, Rect1920x1080.HOMESTATION1_WIDTH2, Rect1920x1080.HOMESTATION1_HEIGHT1,
                        Rect1920x1080.HOMESTATION1_X, Rect1920x1080.HOMESTATION1_X2_W_BLOCKSCREEN,
                        Rect1920x1080.HOMESTATION1_Y, Rect1920x1080.HOMESTATION1_Y2_H_BLOCKSCREEN);

        if (homeStationLabel != null) {
            System.out.printf("Rect found (HOMESTATION1) case 1 - Width: %d and height: %d\n\n", homeStationLabel.width, homeStationLabel.height);
            new ClickScreen().rightClickCenterButton(homeStationLabel);
            return true;

        } else {
            System.out.println("Rect not found (HOMESTATION1) case 1");
            return false;
        }
    }

    private boolean warpArrow() throws IOException, TesseractException, AWTException, InterruptedException {
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle warpArrow = sr3
                .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.WARPARROW_WIDTH2, Rect1920x1080.WARPARROW_HEIGHT1,
                        Rect1920x1080.WARPARROW_X, Rect1920x1080.WARPARROW_X2_B_BLOCKSCREEN,
                        Rect1920x1080.WARPARROW_Y, Rect1920x1080.WARPARROW_Y2_H_BLOCKSCREEN);

        if (warpArrow != null) {
            System.out.printf("Rect found (WARPARROW) - Width: %d and height: %d\n\n", warpArrow.width, warpArrow.height);
            new ClickScreen().leftClickCenterButton(warpArrow);
            return true;

        } else {
            System.out.println("Rect not found (WARPARROW) case 2");
            return false;
        }

    }

    private boolean dockArrow() throws IOException, TesseractException, AWTException, InterruptedException {
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle dockArrow = sr3
                .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.DOCKARROW_WIDTH1, Rect1920x1080.DOCKARROW_HEIGHT1,
                        Rect1920x1080.WARPARROW_X, Rect1920x1080.WARPARROW_X2_B_BLOCKSCREEN,
                        Rect1920x1080.WARPARROW_Y, Rect1920x1080.WARPARROW_Y2_H_BLOCKSCREEN);

        if (dockArrow != null) {
            System.out.printf("Rect found (DOCKARROW) - Width: %d and height: %d\n\n", dockArrow.width, dockArrow.height);
            new ClickScreen().leftClickCenterButton(dockArrow);
            return true;

        } else {
            System.out.println("Rect not found (DOCKARROW) case 2");
            return false;
        }

    }

    private boolean closeLocationWindow() throws IOException, TesseractException, AWTException, InterruptedException {
        /*Close the location window */
        SegmentedRegions sr3 = new SegmentedRegions();

        Rectangle closeButtonWindowLocation = sr3
                .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.CLOSEBUTTONLOCATION_WIDTH1, Rect1920x1080.CLOSEBUTTONLOCATION_HEIGHT1,
                        Rect1920x1080.CLOSEBUTTONLOCATION_X, Rect1920x1080.CLOSEBUTTONLOCATION_X2_W_BLOCKSCREEN,
                        Rect1920x1080.CLOSEBUTTONLOCATION_Y, Rect1920x1080.CLOSEBUTTONLOCATION1_Y2_H_BLOCKSCREEN);

        if (closeButtonWindowLocation != null) {
            System.out.printf("Rect found (CLOSEBUTTONLOCAITON) multiple cases (1,2,3) - Width: %d and height: %d\n\n", closeButtonWindowLocation.width, closeButtonWindowLocation.height);
            new ClickScreen().leftClickCenterButton(closeButtonWindowLocation);

            return true;

        } else {
            System.out.println("Rect not found (CLOSEBUTTONLOCAITON) multiple cases (1,2,3)");
            return false;
        }
    }

}
