package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.DragClickEventInInventoryStation;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.R1920x1080SMALL;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Stanniack
 *
 * No I.A recognition for stack items in mining cargo and "item hangar" Search a
 * word on EVE.exe Left hud: min, fontscale: 100%, EVE fontsize: 13 (small),
 * resolution: 1920x1080 Check cargo, drag itens and undock
 */
public class CheckCargoDeposit2 {

    Rectangle itemHangar;
    private int amountRect = 0;
    private static final int SWTICHFLAG = 3;

    public void check() throws InterruptedException, IOException, AWTException, TesseractException {

        while (this.amountRect < SWTICHFLAG) {

            boolean flagNoDragScreen = false;
            new TakeScreenShot2().take();

            switch (this.amountRect) {

                case 0 -> {

                    Rectangle maxCargo = new SegmentedRegions().get_2WxH_BlockScreen(R1920x1080SMALL.MAXCARGO1_W1, R1920x1080SMALL.MAXCARGO1_W1,
                            R1920x1080SMALL.MAXCARGO1_H1,
                            R1920x1080SMALL.INVENTORY_X1, R1920x1080SMALL.INVENTORY_X2_W,
                            R1920x1080SMALL.INVENTORY_Y1, R1920x1080SMALL.INVENTORY_Y2_H);

                    if (maxCargo != null) {
                        System.out.printf("Rect found (MAXCARGO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                maxCargo.width, maxCargo.height, maxCargo.x, maxCargo.y);

                        this.amountRect++;
                        flagNoDragScreen = true;

                    } else {

                        System.out.println("Rect (MAXCARGO_VENTURE) not found\n");

                        Rectangle minCargo = new SegmentedRegions().get_2WxH_BlockScreen(R1920x1080SMALL.MINGCARGO_WITHM3_W1, R1920x1080SMALL.MINGCARGO_WITHOUTM3_W1,
                                R1920x1080SMALL.MINGCARGO_H1,
                                R1920x1080SMALL.INVENTORY_X1, R1920x1080SMALL.INVENTORY_X2_W,
                                R1920x1080SMALL.INVENTORY_Y1, R1920x1080SMALL.INVENTORY_Y2_H);

                        if (minCargo != null) {
                            System.out.printf("Rect found (MINGCARHO_VENTURE) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                    minCargo.width, minCargo.height, minCargo.x, minCargo.y);

                            // TODO undock
                            flagNoDragScreen = true;

                        } else {
                            System.out.println("Rect (MINCARGO_VENTURE) not found\n");
                        }
                    }

                } // end case 1

                case 1 -> {

                    this.itemHangar = new SegmentedRegions().get_2WxH_BlockScreen(R1920x1080SMALL.ITEMGANGAR_W1, R1920x1080SMALL.ITEMGANGAR_W2,
                            R1920x1080SMALL.ITEMGANGAR_H1,
                            R1920x1080SMALL.INVENTORY_X1, R1920x1080SMALL.INVENTORY_X2_W,
                            R1920x1080SMALL.INVENTORY_Y1, R1920x1080SMALL.INVENTORY_Y2_H);

                    if (this.itemHangar != null) {
                        System.out.printf("Rect found (ITEM_HANGAR) - Width: %d and height: %d at coordinates (%d, %d)\n\n",
                                itemHangar.width, itemHangar.height, itemHangar.x, itemHangar.y);

                        this.amountRect++;
                        flagNoDragScreen = true;

                    } else {
                        System.out.println("Rect (ITEM_HANGAR) not found\n");

                    }
                }

                case 2 -> {
                    
                    new DragClickEventInInventoryStation().eventClick(R1920x1080SMALL.DRAGITENS_X1, R1920x1080SMALL.DRAGITENS_X2_W,
                            R1920x1080SMALL.DRAGITENS_Y1, R1920x1080SMALL.DRAGITENS_Y2_H, this.itemHangar);
                    flagNoDragScreen = true;
                    this.amountRect++;
                    this.itemHangar = null; // 
                    
                    // TODO undock
                }

            } // end switch

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

        } // end main loop

    } // end method

} // end class
