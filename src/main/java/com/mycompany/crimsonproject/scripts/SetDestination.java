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
import com.mycompany.crimsonproject.interfaces.VerifyRectangle;
import org.javatuples.Quartet;

/**
 *
 * @author Devmachine
 */
public class SetDestination implements VerifyRectangle {

    private Rectangle miningBotLabel = null;
    private Rectangle homeStationLabel = null;
    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;
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
                    this.miningBotLabel = new SegmentedRegions().getRectangle(new FULLHD().listMiningBotWxH, new FULLHD().tupleLocationTabDeadZone);

                    if (option == MININGBOT && this.verifyRectangle(miningBotLabel, "MININGBOT1", RIGHTCLICK)) {
                        this.amountRect++;
                        descentFlag = false;

                    } else {
                        this.homeStationLabel = new SegmentedRegions().getRectangle(new FULLHD().listHomeStationWxH, new FULLHD().tupleLocationTabDeadZone);

                        if (option == HOMESTATION && this.verifyRectangle(homeStationLabel, "HOMESTATION1", RIGHTCLICK)) {
                            this.amountRect++;
                            descentFlag = false;
                        }
                    }

                    /* Close location windows if doesnt find the MININGBOT1 or HOMESTATION1 */
                    if (descentFlag) {
                        Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(new FULLHD().listCloseLocationButtonWxH, new FULLHD().tupleLocationTabDeadZone);
                        this.amountRect--;
                        this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK);
                    }

                } // end case 1

                case 2 -> {

                    Rectangle warpBlock = new SegmentedRegions().getRectangle(new FULLHD().listWarpWxH, this.getMiningBotTuple());

                    if (option == MININGBOT && this.verifyRectangle(warpBlock, "WARPBLOCK", LEFTCLICK)) {
                        this.amountRect++;
                        descentFlag = false;

                    } else {
                        Rectangle dock = new SegmentedRegions().getRectangle(new FULLHD().listDockWxH, this.getHomeStationTuple());

                        if (option == HOMESTATION && this.verifyRectangle(dock, "DOCK", LEFTCLICK)) {
                            this.amountRect++;
                            descentFlag = false;
                        }
                    }

                    // back to case 1 and find the MININGBOT1 or HOMESTATION1 to restart finding WITHIN/DOCK
                    if (descentFlag) {
                        Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(new FULLHD().listCloseLocationButtonWxH, new FULLHD().tupleLocationTabDeadZone);
                        this.amountRect--;
                        this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK);
                        new ClickScreenEvents().dragScreen();
                    }

                } // end case 2

                case 3 -> {
                    Rectangle closeButtonWindowLocation = new SegmentedRegions().getRectangle(new FULLHD().listCloseLocationButtonWxH, new FULLHD().tupleLocationTabDeadZone);

                    if (this.verifyRectangle(closeButtonWindowLocation, "CLOSEBUTTONLOCATION", LEFTCLICK)) {
                        this.amountRect++;
                    }

                }

            } // end case 3

        } while (this.amountRect < SWITCHFLAG);
    }

    private Quartet<Integer, Integer, Integer, Integer> getMiningBotTuple() {
        try {
            int cursorLenght = 11;
            int tabDeadZoneW = 245;
            int tabDeadZoneH = 29;
            int x1 = this.miningBotLabel.x + this.miningBotLabel.width / 2 + cursorLenght;
            int x2_w = x1 + tabDeadZoneW;
            int y1 = this.miningBotLabel.y + this.miningBotLabel.height / 2;
            int y2_h = y1 + tabDeadZoneH;

            return new Quartet<>(x1, x2_w, y1, y2_h);

        } catch (NullPointerException ex) {

        }

        return new Quartet<>(0, 0, 0, 0);
    }

    private Quartet<Integer, Integer, Integer, Integer> getHomeStationTuple() {
        try {
            int cursorLenght = 11;
            int tabDeadZoneW = 245;
            int tabDeadZoneH = 29;
            int x1 = this.homeStationLabel.x + this.homeStationLabel.width / 2 + cursorLenght;
            int x2_w = x1 + tabDeadZoneW;
            int y1 = this.homeStationLabel.y + this.homeStationLabel.height / 2;
            int y2_h = y1 + tabDeadZoneH;

            return new Quartet<>(x1, x2_w, y1, y2_h);

        } catch (NullPointerException ex) {

        }

        return new Quartet<>(0, 0, 0, 0);
    }

    private boolean openLocation() throws IOException, TesseractException, AWTException, InterruptedException {

        new KeyboardEvents().pressKey(KeyEvent.VK_L);
        System.out.println("Window location open by shortcut L\n");
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

        System.out.printf("Rect not found (%s)\n\n", itemName);
        return false;
    }

}
