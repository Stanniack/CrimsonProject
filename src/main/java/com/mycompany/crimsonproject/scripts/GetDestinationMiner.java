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
public class GetDestinationMiner {

    public void getDestination() throws IOException, TesseractException, AWTException, InterruptedException {
        int amountRect = 0;
        // it depends the amount of switch cases
        int switchFlag = 4;

        do {
            SegmentedRegions sr3 = new SegmentedRegions();
            boolean flagNoDragScreen = false; 
            new TakeScreenShot2().take();

            switch (amountRect) {

                case 0 -> {

                    /* Disable "Help EVE" button because its attribute have same width and height to Localization button
                       Location symbol must be the last shortcut in fixed hub on right side with min scale hud  */
                    Rectangle rectResult = sr3
                            .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.LOCATIONSYMBOL_WIDTH1, Rect1920x1080.LOCATIONSYMBOL_HEIGHT1,
                                    Rect1920x1080.LOCATIONSYMBOL_X, Rect1920x1080.LOCATIONSYMBOL_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.LOCATIONSYMBOL_Y, Rect1920x1080.LOCATIONSYMBOL_Y2_H_BLOCKSCREEN);

                    if (rectResult != null) {
                        System.out.printf("Rect found (LOCATIONSYNMBOL) - Width: %d and height: %d\n\n", rectResult.width, rectResult.height);
                        new ClickScreen().leftClickCenterButton(rectResult);
                        amountRect++;
                        flagNoDragScreen = true;
                    }
                }

                case 1 -> {

                    Rectangle rectResult = sr3
                            .getSegmentedRegion_3Wx2H_BLOCKSCREEN(Rect1920x1080.MININGBOT1_WIDTH1,
                                    Rect1920x1080.MININGBOT1_WIDTH2,
                                    Rect1920x1080.MININGBOT1_WIDTH3,
                                    Rect1920x1080.MININGBOT1_HEIGHT1,
                                    Rect1920x1080.MININGBOT1_HEIGHT2,
                                    Rect1920x1080.MININGBOT1_X, Rect1920x1080.MININGBOT1_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.MININGBOT1_Y, Rect1920x1080.MININGBOT1_Y2_H_BLOCKSCREEN);

                    if (rectResult != null) {
                        System.out.printf("Rect found (MININGBOT1) - Width: %d and height: %d\n\n", rectResult.width, rectResult.height);
                        new ClickScreen().rightClickCenterButton(rectResult);
                        amountRect++;
                        flagNoDragScreen = true;

                    } else {
                        /* Close location windows if doesnt find the MININGBOT1 */
                        flagNoDragScreen = closeLocationWindow();

                        if (!flagNoDragScreen) {
                            amountRect--; // return to case 1 to open the location windows again
                        }
                    }
                }

                case 2 -> {

                    /* For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. */
                    Rectangle rectResult = sr3
                            .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.WARPARROW_WIDTH2, Rect1920x1080.WARPARROW_HEIGHT1,
                                    Rect1920x1080.WARPARROW_X, Rect1920x1080.WARPARROW_X2_B_BLOCKSCREEN,
                                    Rect1920x1080.WARPARROW_Y, Rect1920x1080.WARPARROW_Y2_H_BLOCKSCREEN);

                    if (rectResult != null) {
                        System.out.printf("Rect found (WARPARROW) - Width: %d and height: %d\n\n", rectResult.width, rectResult.height);
                        new ClickScreen().leftClickCenterButton(rectResult);
                        amountRect++; // go to case 3
                        flagNoDragScreen = true;

                    } else {
                        amountRect--; // back to case 1 and find the MININGBOT1 to restart finding WARPARROW
                    }

                }
                case 3 -> {
                    flagNoDragScreen = closeLocationWindow();

                    if (!flagNoDragScreen) {
                        amountRect++;
                    }

                }
            }

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

            System.out.println("main loop\n");

        } while (amountRect < switchFlag);
    }

    private boolean closeLocationWindow() throws IOException, TesseractException, AWTException, InterruptedException {
        /*Close the location window */
        SegmentedRegions sr3 = new SegmentedRegions();

        Rectangle rectResult = sr3
                .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.CLOSEBUTTONLOCATION_WIDTH1, Rect1920x1080.CLOSEBUTTONLOCATION_HEIGHT1,
                        Rect1920x1080.CLOSEBUTTONLOCATION_X, Rect1920x1080.CLOSEBUTTONLOCATION_X2_W_BLOCKSCREEN,
                        Rect1920x1080.CLOSEBUTTONLOCATION_Y, Rect1920x1080.CLOSEBUTTONLOCATION1_Y2_H_BLOCKSCREEN);

        if (rectResult != null) {
            System.out.printf("Rect found (CLOSEBUTTONLOCAITON) case 3 - Width: %d and height: %d\n\n", rectResult.width, rectResult.height);
            new ClickScreen().leftClickCenterButton(rectResult);

            return true;
        }

        return false;
    }
}
