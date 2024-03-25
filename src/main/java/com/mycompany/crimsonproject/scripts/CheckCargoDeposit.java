package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.FULLHD;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 *
 */
public class CheckCargoDeposit {

    Rectangle hangarButton;
    private int amountRect = 0;
    private static final int SWTICHFLAG = 4;

    public void startScript() throws InterruptedException, IOException, AWTException, TesseractException {

        while (this.amountRect < SWTICHFLAG) {

            boolean flagNoDragScreen = false;
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

                        flagNoDragScreen = true;

                    }

                } // end case 1

                case 1 -> {

                    if (this.findHangar()) {
                        this.amountRect++;
                        flagNoDragScreen = true;
                    }
                }

                case 2 -> {

                    this.dragItens();
                    this.amountRect++;
                    this.hangarButton = null;
                    flagNoDragScreen = true;

                }

                case 3 -> {

                    if (this.pressUndockButton()) {
                        this.amountRect++;
                        flagNoDragScreen = true;
                    }
                }

            } // end switch

            if (!flagNoDragScreen) {
                new ClickScreenEvents().dragScreen();
            }

        } // end main loop

    } // end method

    private String verifyCargoHold() throws IOException, TesseractException {

        Rectangle maxCargo = new SegmentedRegions().getRectangle(new FULLHD().listMaxCargoWxH, new FULLHD().tupleInvetoryDeadzone);

        if (maxCargo != null) {
            System.out.printf("Rect found (MAXCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                    maxCargo.width, maxCargo.height, maxCargo.x, maxCargo.y);

            return "maxCargo";

        } else {

            Rectangle minCargo = new SegmentedRegions().getRectangle(new FULLHD().listMinCargo, new FULLHD().tupleInvetoryDeadzone);

            if (minCargo != null) {
                System.out.printf("Rect found (MINGCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                        minCargo.width, minCargo.height, minCargo.x, minCargo.y);

                return "minCargo";

            } else {
                System.out.println("Rect (MINCARGO_VENTURE) not found\n");
            }
        }

        return "notFound";

    }

    private boolean findHangar() throws IOException, TesseractException {

        this.hangarButton = new SegmentedRegions().getRectangle(new FULLHD().listHangar, new FULLHD().tupleInvetoryDeadzone);

        if (this.hangarButton != null) {
            System.out.printf("Rect found (HANGAR) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                    this.hangarButton.width, this.hangarButton.height, this.hangarButton.x, this.hangarButton.y);

            return true;

        } else {
            System.out.println("Rect (HANGAR) not found\n");
        }

        return false;
    }

    private void dragItens() throws AWTException, InterruptedException {
        new ClickScreenEvents().dragItensToIventory(new FULLHD().tupleDragItensDeadZone, this.hangarButton);
    }

    private boolean pressUndockButton() throws IOException, TesseractException, AWTException, InterruptedException {

        Rectangle undockButton = new SegmentedRegions().getRectangle(new FULLHD().listUndockButton, new FULLHD().tupleUndockDeadZone);

        if (undockButton != null) {
            System.out.printf("Rect found (UNDOCK_BUTTON) - Width: %d and height: %d\n\n", undockButton.width, undockButton.height);
            new ClickScreenEvents().leftClickCenterButton(undockButton);

            return true;

        } else {
            System.out.println("Rect not found (UNDOCK_BUTTON)\n");
        }

        return false;
    }

} // end class
