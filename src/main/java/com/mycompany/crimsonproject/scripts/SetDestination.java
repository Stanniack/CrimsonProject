package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.FULLHD;
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

            boolean descentFlag = true;
            new TakeScreenShot().take();
            // Todo connection lost

            switch (this.amountRect) {

                case 0 -> {

                    if (this.openLocation()) {
                        this.amountRect++;
                    }

                } // end case 0

                case 1 -> {

                    if (option == MININGBOT && this.miningbot1Label()) {
                        this.amountRect++;
                        descentFlag = false;

                    } else if (option == HOMESTATION && this.homestationLabel()) {
                        this.amountRect++;
                        descentFlag = false;
                    }

                    /* Close location windows if doesnt find the MININGBOT1 or HOMESTATION1 */
                    if (descentFlag) {
                        this.amountRect--; // return to case 0 to open the location window again
                        this.closeLocationWindow();
                    }

                } // end case 1

                case 2 -> {

                    if (option == MININGBOT && this.whithin()) {
                        this.amountRect++;
                        descentFlag = false;

                    } else if (option == HOMESTATION && this.dockArrow()) {
                        this.amountRect++;
                        descentFlag = false;
                    }

                    // back to case 1 and find the MININGBOT1 or HOMESTATION1  to restart finding WARPARROW/DOCKARROW
                    if (descentFlag) {
                        this.amountRect--;
                        this.closeLocationWindow();
                    }

                } // end case 2

                case 3 -> {

                    if (this.closeLocationWindow()) {
                        this.amountRect++;
                    }

                }

            } // end case 3

        } while (this.amountRect < SWITCHFLAG);
    }

    private boolean openLocation() throws IOException, TesseractException, AWTException, InterruptedException {

        new KeyboardEvents().pressKey(KeyEvent.VK_L);
        System.out.println("L clicked at case " + this.amountRect + "\n");
        return true;
    }

    private boolean miningbot1Label() throws IOException, TesseractException, AWTException, InterruptedException {

        Rectangle miningBotLabel = new SegmentedRegions().getRectangle(new FULLHD().listMiningBotWxH, new FULLHD().tupleLocationTabDeadZone);

        if (miningBotLabel != null) {
            System.out.printf("Rect found (MININGBOT1) at case " + this.amountRect + " - Width: %d and height: %d\n\n", miningBotLabel.width, miningBotLabel.height);
            new ClickScreenEvents().rightClickCenterButton(miningBotLabel);
            return true;
        }

        System.out.println("Rect not found (MININGBOT1) case " + this.amountRect + "\n");
        return false;
    }

    private boolean homestationLabel() throws IOException, TesseractException, AWTException, InterruptedException {

        Rectangle homeStationLabel = new SegmentedRegions().getRectangle(new FULLHD().listHomeStationWxH, new FULLHD().tupleLocationTabDeadZone);

        if (homeStationLabel != null) {
            System.out.printf("Rect found (HOMESTATION1) at case " + this.amountRect + " - Width: %d and height: %d\n\n", homeStationLabel.width, homeStationLabel.height);
            new ClickScreenEvents().rightClickCenterButton(homeStationLabel);
            return true;
        }

        System.out.println("Rect not found (HOMESTATION1) at case" + this.amountRect + "\n");
        return false;
    }

    private boolean whithin() throws IOException, TesseractException, AWTException, InterruptedException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        Rectangle warpArrow = new SegmentedRegions().getRectangle(new FULLHD().listWithinWxH, new FULLHD().tupleLocationTabDeadZone);

        if (warpArrow != null) {
            System.out.printf("Rect found (WHITHIN) at case " + this.amountRect + " - Width: %d and height: %d\n\n", warpArrow.width, warpArrow.height);
            new ClickScreenEvents().leftClickCenterButton(warpArrow);
            return true;
        }

        System.out.println("Rect not found (WHITHIN) at case " + this.amountRect + "\n");
        return false;
    }

    private boolean dockArrow() throws IOException, TesseractException, AWTException, InterruptedException {

        /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
        Rectangle dockArrow = new SegmentedRegions().getRectangle(new FULLHD().listDockWxH, new FULLHD().tupleLocationTabDeadZone);

        if (dockArrow != null) {
            System.out.printf("Rect found (DOCK) at case " + this.amountRect + " - Width: %d and height: %d\n\n", dockArrow.width, dockArrow.height);
            new ClickScreenEvents().leftClickCenterButton(dockArrow);
            return true;
        }

        System.out.println("Rect not found (DOCK) at case " + this.amountRect + "\n");
        return false;
    }

    private boolean closeLocationWindow() throws IOException, TesseractException, AWTException, InterruptedException {

        /*Close the location window */
        Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(new FULLHD().listCloseLocationButtonWxH, new FULLHD().tupleLocationTabDeadZone);

        if (closeButtonWindowLocation != null) {
            System.out.printf("Rect found (CLOSEBUTTONLOCATION) at case " + this.amountRect + " - Width: %d and height: %d\n\n", closeButtonWindowLocation.width, closeButtonWindowLocation.height);
            new ClickScreenEvents().leftClickCenterButton(closeButtonWindowLocation);
            return true;

        }

        System.out.println("Rect not found (CLOSEBUTTONLOCATION) at cases" + this.amountRect + "\n");
        return false;
    }

}
