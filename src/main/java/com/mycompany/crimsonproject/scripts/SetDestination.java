package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.R1920x1080SMALL;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class SetDestination {

    private static final int HOMESTATION = 0;
    private static final int MININGBOT = 1;
    // it depends the amount of switch cases
    private static final int SWITCHFLAG = 4;

    private int amountRect = 0;

    public void startScript(int option) throws IOException, TesseractException, AWTException, InterruptedException {

        do {

            boolean flagNoDragScreen = false;
            new TakeScreenShot().take();
            // Todo connection lost

            switch (this.amountRect) {

                case 0 -> {

                    if (this.openLocation()) {
                        flagNoDragScreen = true;
                        this.amountRect++;
                    }
                    
                } // end case 0

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
                        this.amountRect--; // return to case 0 to open the location window again
                        flagNoDragScreen = this.closeLocationWindow();
                    }
                    
                } // end case 1

                case 2 -> {

                    if (option == MININGBOT) {
                        if (this.whithin()) {
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
                        this.amountRect--;
                        flagNoDragScreen = this.closeLocationWindow();
                    }
                    
                } // end case 2

                case 3 -> {

                    if (this.closeLocationWindow()) {
                        flagNoDragScreen = true;
                        this.amountRect++;
                    }

                }
                
            } // end case 3

            if (!flagNoDragScreen) {
                new ClickScreenEvents().dragScreen();
            }

        } while (this.amountRect < SWITCHFLAG);
    }

    private boolean openLocation() throws IOException, TesseractException, AWTException, InterruptedException {
        
        new KeyboardEvents().pressKey(KeyEvent.VK_L);
        System.out.println("L clicked at case " + this.amountRect + "\n");

        return true;
    }

    private boolean miningbot1Label() throws IOException, TesseractException, AWTException, InterruptedException {
        
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle miningBot1Label = sr3
                .getT_2WxH_BlockScreen(
                        R1920x1080SMALL.MININGBOT1_W1,
                        R1920x1080SMALL.MININGBOT1_W2,
                        R1920x1080SMALL.MININGBOT1_H1,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_X1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_X2_W,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y2_H);

        if (miningBot1Label != null) {
            System.out.printf("Rect found (MININGBOT1) at case " + this.amountRect + " - Width: %d and height: %d\n\n", miningBot1Label.width, miningBot1Label.height);
            new ClickScreenEvents().rightClickCenterButton(miningBot1Label);
            return true;

        } else {
            System.out.println("Rect not found (MININGBOT1) case " + this.amountRect + "\n");
            return false;
        }
    }

    private boolean homestationLabel() throws IOException, TesseractException, AWTException, InterruptedException {
        
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle homeStationLabel = sr3
                .getT_2WxH_BlockScreen(
                        R1920x1080SMALL.HOMESTATION1_W1, R1920x1080SMALL.HOMESTATION1_W2,
                        R1920x1080SMALL.HOMESTATION1_H1,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_X1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_X2_W,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y2_H);

        if (homeStationLabel != null) {
            System.out.printf("Rect found (HOMESTATION1) at case " + this.amountRect + " - Width: %d and height: %d\n\n", homeStationLabel.width, homeStationLabel.height);
            new ClickScreenEvents().rightClickCenterButton(homeStationLabel);
            return true;

        } else {
            System.out.println("Rect not found (HOMESTATION1) at case" + this.amountRect + "\n");
            return false;
        }
    }

    private boolean whithin() throws IOException, TesseractException, AWTException, InterruptedException {
        
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle warpArrow = sr3
                .getT_WxH_BlockScreen(
                        R1920x1080SMALL.WHITIN_W1,
                        R1920x1080SMALL.WHITIN_H1,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_X1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_X2_W,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y2_H);

        if (warpArrow != null) {
            System.out.printf("Rect found (WHITHIN) at case " + this.amountRect + " - Width: %d and height: %d\n\n", warpArrow.width, warpArrow.height);
            new ClickScreenEvents().leftClickCenterButton(warpArrow);
            return true;

        } else {
            System.out.println("Rect not found (WHITHIN) at case " + this.amountRect + "\n");
            return false;
        }

    }

    private boolean dockArrow() throws IOException, TesseractException, AWTException, InterruptedException {
        
        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        SegmentedRegions sr3 = new SegmentedRegions();
        Rectangle dockArrow = sr3
                .getT_WxH_BlockScreen(
                        R1920x1080SMALL.DOCK_W1,
                        R1920x1080SMALL.DOCK_H1,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_X1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_X2_W,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y2_H);

        if (dockArrow != null) {
            System.out.printf("Rect found (DOCK) at case " + this.amountRect + " - Width: %d and height: %d\n\n", dockArrow.width, dockArrow.height);
            new ClickScreenEvents().leftClickCenterButton(dockArrow);
            return true;

        } else {
            System.out.println("Rect not found (DOCK) at case " + this.amountRect + "\n");
            return false;
        }

    }

    private boolean closeLocationWindow() throws IOException, TesseractException, AWTException, InterruptedException {
        
        /*Close the location window */
        SegmentedRegions sr3 = new SegmentedRegions();

        Rectangle closeButtonWindowLocation = sr3
                .getT_WxH_BlockScreen(
                        R1920x1080SMALL.CLOSELOCATIONBUTTON_W1,
                        R1920x1080SMALL.CLOSELOCATIONBUTTON_H1,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_X1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_X2_W,
                        R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y1, R1920x1080SMALL.LOCATIONTAB_DEADZONE_Y2_H);

        if (closeButtonWindowLocation != null) {
            System.out.printf("Rect found (CLOSEBUTTONLOCATION) at casse " + this.amountRect + " - Width: %d and height: %d\n\n", closeButtonWindowLocation.width, closeButtonWindowLocation.height);
            new ClickScreenEvents().leftClickCenterButton(closeButtonWindowLocation);

            return true;

        } else {
            System.out.println("Rect not found (CLOSEBUTTONLOCATION) at cases" + this.amountRect + "\n");
            return false;
        }
    }

}
