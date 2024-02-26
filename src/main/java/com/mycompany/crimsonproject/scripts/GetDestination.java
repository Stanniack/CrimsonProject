package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.R1920x1080SMALL;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Stanniack
 */
public class GetDestination {

    private static final int HOMESTATION = 0;
    private static final int MININGBOT = 1;
    // it depends the amount of switch cases
    private static final int SWITCHFLAG = 4;

    private int amountRect = 0;

    public void getDestination(int option) throws IOException, TesseractException, AWTException, InterruptedException {

        do {
            
            boolean flagNoDragScreen = false;
            new TakeScreenShot().take();

            switch (this.amountRect) {

                case 0 -> {

                    if (this.locationButton()) {
                        flagNoDragScreen = true;
                        this.amountRect++;
                    }
                }

                case 1 -> {

                    if (option == MININGBOT) {
                        if (this.miningbot1Label()) {
                            flagNoDragScreen = true;
                            this.amountRect++;
                        }

                    } else if (option == HOMESTATION) {
                        if (this.homestationLabel()) {
                            flagNoDragScreen = true;
                            this.amountRect++;
                        }

                    }

                    /* Close location windows if doesnt find the MININGBOT1 or HOMESTATION1 */
                    if (!flagNoDragScreen) {
                        this.closeLocationWindow();
                        this.amountRect--; // return to case 1 to open the location windows again
                    }
                }

                case 2 -> {

                    if (option == MININGBOT) {
                        if (this.warpArrow()) {
                            flagNoDragScreen = true;
                            this.amountRect++;
                        }

                    } else if (option == HOMESTATION) {
                        if (this.dockArrow()) {
                            flagNoDragScreen = true;
                            this.amountRect++;
                        }

                    }

                    // back to case 1 and find the MININGBOT1 or HOMESTATION1  to restart finding WARPARROW/DOCKARROW
                    if (!flagNoDragScreen) {
                        this.closeLocationWindow();
                        this.amountRect--;
                    }
                }

                case 3 -> {

                    if (this.closeLocationWindow()) {
                        flagNoDragScreen = true;
                        this.amountRect++;
                    }

                }
            }

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

        } while (this.amountRect < SWITCHFLAG);
    }

    private boolean locationButton() throws IOException, TesseractException, AWTException, InterruptedException {
        /* Disable "Help EVE" button because its attribute have same width and height to Localization button
                       Location symbol must be the last shortcut in fixed hub on right side with min scale hud  */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle locationButton = sr3
                .getT_WxH_BlocksScreen(
                        R1920x1080SMALL.LOCATIONSYMBOL_W1, 
                        R1920x1080SMALL.LOCATIONSYMBOL_H1,
                        R1920x1080SMALL.LOCATIONSYMBOL_X1, R1920x1080SMALL.LOCATIONSYMBOL_X2_W,
                        R1920x1080SMALL.LOCATIONSYMBOL_Y1, R1920x1080SMALL.LOCATIONSYMBOL_Y2_H);

        if (locationButton != null) {
            System.out.printf("Rect found (LOCATIONSYNMBOL) case 0 - Width: %d and height: %d\n\n", locationButton.width, locationButton.height);
            new ClickScreenEvents().leftClickCenterButton(locationButton);
            return true;

        } else {
            System.out.println("Rect not found (LOCATIONSYNMBOL) case 0\n");
            return false;
        }
    }

    private boolean miningbot1Label() throws IOException, TesseractException, AWTException, InterruptedException {
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle miningBot1Label = sr3
                .getT_3Wx2H_BlockScreen(
                        R1920x1080SMALL.MININGBOT1_W1,
                        R1920x1080SMALL.MININGBOT1_W2,
                        R1920x1080SMALL.MININGBOT1_W3,
                        R1920x1080SMALL.MININGBOT1_H1,
                        R1920x1080SMALL.MININGBOT1_H2,
                        R1920x1080SMALL.MININGBOT1_X1, R1920x1080SMALL.MININGBOT1_X2_W,
                        R1920x1080SMALL.MININGBOT1_Y1, R1920x1080SMALL.MININGBOT1_Y2_H);

        if (miningBot1Label != null) {
            System.out.printf("Rect found (MININGBOT1) case 1 - Width: %d and height: %d\n\n", miningBot1Label.width, miningBot1Label.height);
            new ClickScreenEvents().rightClickCenterButton(miningBot1Label);
            return true;

        } else {
            System.out.println("Rect not found (MININGBOT1) case 1\n");
            return false;
        }
    }

    private boolean homestationLabel() throws IOException, TesseractException, AWTException, InterruptedException {
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle homeStationLabel = sr3
                .getT_2WxH_BlockScreen(
                        R1920x1080SMALL.HOMESTATION1_W1, R1920x1080SMALL.HOMESTATION1_W2, 
                        R1920x1080SMALL.HOMESTATION1_H1,
                        R1920x1080SMALL.HOMESTATION1_X1, R1920x1080SMALL.HOMESTATION1_X2_W,
                        R1920x1080SMALL.HOMESTATION1_Y1, R1920x1080SMALL.HOMESTATION1_Y2_H);

        if (homeStationLabel != null) {
            System.out.printf("Rect found (HOMESTATION1) case 1 - Width: %d and height: %d\n\n", homeStationLabel.width, homeStationLabel.height);
            new ClickScreenEvents().rightClickCenterButton(homeStationLabel);
            return true;

        } else {
            System.out.println("Rect not found (HOMESTATION1) case 1\n");
            return false;
        }
    }

    private boolean warpArrow() throws IOException, TesseractException, AWTException, InterruptedException {
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle warpArrow = sr3
                .getT_WxH_BlocksScreen(
                        R1920x1080SMALL.WARPARROW_W2, 
                        R1920x1080SMALL.WARPARROW_H1,
                        R1920x1080SMALL.WARPARROW_X1, R1920x1080SMALL.WARPARROW_X2_W,
                        R1920x1080SMALL.WARPARROW_Y1, R1920x1080SMALL.WARPARROW_Y2_H);

        if (warpArrow != null) {
            System.out.printf("Rect found (WARPARROW) - Width: %d and height: %d\n\n", warpArrow.width, warpArrow.height);
            new ClickScreenEvents().leftClickCenterButton(warpArrow);
            return true;

        } else {
            System.out.println("Rect not found (WARPARROW) case 2\n");
            return false;
        }

    }

    private boolean dockArrow() throws IOException, TesseractException, AWTException, InterruptedException {
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle dockArrow = sr3
                .getT_WxH_BlocksScreen(
                        R1920x1080SMALL.DOCKARROW_W1, 
                        R1920x1080SMALL.DOCKARROW_H1,
                        R1920x1080SMALL.WARPARROW_X1, R1920x1080SMALL.WARPARROW_X2_W,
                        R1920x1080SMALL.WARPARROW_Y1, R1920x1080SMALL.WARPARROW_Y2_H);

        if (dockArrow != null) {
            System.out.printf("Rect found (DOCKARROW) - Width: %d and height: %d\n\n", dockArrow.width, dockArrow.height);
            new ClickScreenEvents().leftClickCenterButton(dockArrow);
            return true;

        } else {
            System.out.println("Rect not found (DOCKARROW) case 2\n");
            return false;
        }

    }

    private boolean closeLocationWindow() throws IOException, TesseractException, AWTException, InterruptedException {
        /*Close the location window */
        SegmentedRegions sr3 = new SegmentedRegions();

        Rectangle closeButtonWindowLocation = sr3
                .getT_WxH_BlocksScreen(
                        R1920x1080SMALL.CLOSELOCATIONBUTTON_W1, 
                        R1920x1080SMALL.CLOSELOCATIONBUTTON_H1,
                        R1920x1080SMALL.CLOSELOCATIONBUTTON_X1, R1920x1080SMALL.CLOSELOCATIONBUTTON_X2_W,
                        R1920x1080SMALL.CLOSELOCATIONBUTTON_Y1, R1920x1080SMALL.CLOSELOCATIONBUTTON_Y2_H);

        if (closeButtonWindowLocation != null) {
            System.out.printf("Rect found (CLOSEBUTTONLOCATION) multiple cases (1,2,3) - Width: %d and height: %d\n\n", closeButtonWindowLocation.width, closeButtonWindowLocation.height);
            new ClickScreenEvents().leftClickCenterButton(closeButtonWindowLocation);

            return true;

        } else {
            System.out.println("Rect not found (CLOSEBUTTONLOCAITON) multiple cases (1,2,3)\n");
            return false;
        }
    }

}
