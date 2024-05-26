package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.FULLHD;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;
import com.mycompany.crimsonproject.interfaces.VerifyRectangle;

/**
 *
 * @author Devmachine
 *
 */
public class CheckCargoDeposit implements VerifyRectangle {

    private static final int RIGHTCLICK = 0;
    private static final int LEFTCLICK = 1;

    private Rectangle hangarButton;
    private int amountRect = 0;
    private static final int SWTICHFLAG = 4;

    public void startScript() throws InterruptedException, IOException, AWTException, TesseractException {

        while (this.amountRect < SWTICHFLAG) {

            new TakeScreenShot().take();
            // Todo connection lost

            switch (this.amountRect) {

                case 0 -> {
                    if (!this.verifyCargoHold().equals("notFound")) {

                        if (this.verifyCargoHold().equals("maxCargo")) {
                            this.amountRect++;

                        } else if (this.verifyCargoHold().equals("minCargo")) {
                            this.amountRect = 3;
                        }
                    } else {
                        new ClickScreenEvents().dragScreen();
                    }

                } // end case 1

                case 1 -> {
                    if (this.findHangar()) {
                        this.amountRect++;
                    } else {
                        new ClickScreenEvents().dragScreen();
                    }
                }

                case 2 -> {
                    this.dragItens();
                    this.amountRect++;
                    this.hangarButton = null;
                }

                case 3 -> {
                    Rectangle undockButton = new SegmentedRegions().getRectangle(new FULLHD().listUndockButtonWxH, new FULLHD().tupleUndockDeadZone);

                    if (this.verifyRectangle(undockButton, "UNDOCKBUTTON", LEFTCLICK)) {
                        this.amountRect++;
                    } else {
                        new ClickScreenEvents().dragScreen();
                    }
                }

            } // end switch

        } // end main loop

    } // end method

    private String verifyCargoHold() throws IOException, TesseractException {

        Rectangle maxCargo = new SegmentedRegions().getRectangle(new FULLHD().listMaxCargoWxH, new FULLHD().tupleInventoryDeadzone);

        if (maxCargo != null) {
            System.out.printf("Rect found (MAXCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                    maxCargo.width, maxCargo.height, maxCargo.x, maxCargo.y);

            return "maxCargo";
        }

        Rectangle minCargo = new SegmentedRegions().getRectangle(new FULLHD().listMinCargoWxH, new FULLHD().tupleInventoryDeadzone);

        if (minCargo != null) {
            System.out.printf("Rect found (MINGCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                    minCargo.width, minCargo.height, minCargo.x, minCargo.y);

            return "minCargo";
        }

        return "notFound";
    }

    private boolean findHangar() throws IOException, TesseractException {

        this.hangarButton = new SegmentedRegions().getRectangle(new FULLHD().listHangarWxH, new FULLHD().tupleInventoryDeadzone);

        if (this.hangarButton != null) {
            System.out.printf("Rect found (HANGAR) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                    this.hangarButton.width, this.hangarButton.height, this.hangarButton.x, this.hangarButton.y);

            return true;
        }

        return false;
    }

    private void dragItens() throws AWTException, InterruptedException {
        new ClickScreenEvents().dragItemsToInventory(new FULLHD().tupleDragItensDeadZone, this.hangarButton);
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

} // end class
