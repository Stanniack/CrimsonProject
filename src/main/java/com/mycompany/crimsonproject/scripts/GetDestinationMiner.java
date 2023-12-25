/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreen;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions2;
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

        do {
            new TakeScreenShot2().take();

            switch (amountRect) {
                case 0 -> {
                    Rectangle rectResult = new SegmentedRegions2()
                            .getSegmentedRegionWxH(Rect1920x1080.LOCATIONSYMBOL_WIDTH, Rect1920x1080.LOCATIONSYMBOL_HEIGHT);

                    if (rectResult != null) {
                        System.out.printf("Rect found - Width: %d and height: %d\n", rectResult.width, rectResult.height);
                        new ClickScreen().eventClick(rectResult);
                        amountRect++;
                    }
                }
                case 1 -> {

                    Rectangle rectResult = new SegmentedRegions2()
                            .getSegmentedRegion2WxH(Rect1920x1080.MININGBOT1_1_WIDTH, 
                                    Rect1920x1080.MININGBOT1_2_WIDTH, Rect1920x1080.MININGBOT1_HEIGHT);

                    if (rectResult != null) {
                        System.out.printf("Rect found - Width: %d and height: %d\n", rectResult.width, rectResult.height);
                        new ClickScreen().eventClick(rectResult);
                        amountRect++;
                    }
                }
            }

            new DragScreen().eventClick();

        } while (amountRect < 2);
    }
}
