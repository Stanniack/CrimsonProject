/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreen;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions3;
import com.mycompany.crimsonproject.utils.Rect1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class ExtractOre {

    public void extract() throws IOException, TesseractException, AWTException, InterruptedException {
        int amountRect = 0;

        do {

            SegmentedRegions3 sr3 = new SegmentedRegions3();
            boolean flagNoDragScreen = false;
            boolean foundRect = false;
            new TakeScreenShot2().take();

            switch (amountRect) {
                case 0 -> {
                    /* P1 */
                    Rectangle condensedRectResult = sr3
                            .getSegmentedRegion_2WxH_BLOCKSCREEN(Rect1920x1080.CONDENSED_WIDTH1, Rect1920x1080.CONDENSED_WIDTH2, Rect1920x1080.CONDENSED_HEIGHT1,
                                    Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    /* P2 */
                    Rectangle scorditeRectResult = sr3
                            .getSegmentedRegion_Wx2H_BLOCKSCREEN(Rect1920x1080.SCORDITE_WIDTH1, Rect1920x1080.SCORDITE_HEIGHT1, Rect1920x1080.SCORDITE_HEIGHT2,
                                    Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    /* P3 */
                    Rectangle denseRectResult = sr3
                            .getSegmentedRegion_2WxH_BLOCKSCREEN(Rect1920x1080.DENSE_WIDHT1, Rect1920x1080.DENSE_WIDHT2, Rect1920x1080.DENSE_HEIGHT1,
                                    Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    /* P4 */
                    Rectangle concentratedResult = sr3
                            .getSegmentedRegionConcentrated_3Wx2H_BLOCKSCREEN(Rect1920x1080.CONCENTRATED_WIDTH1, Rect1920x1080.CONCENTRATED_WIDTH2, Rect1920x1080.CONCENTRATED_WIDTH3,
                                    Rect1920x1080.CONDENSED_HEIGHT1, Rect1920x1080.CONCENTRATED_HEIGHT2,
                                    Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    /* P5 */
                    Rectangle veldsparResult = sr3
                            .getSegmentedRegion_3Wx2H_BLOCKSCREEN(Rect1920x1080.VELDSPAR_WIDTH1, Rect1920x1080.VELDSPAR_WIDTH2, Rect1920x1080.VELDSPAR_WIDTH3,
                                    Rect1920x1080.VELDSPAR_HEIGHT1, Rect1920x1080.CONCENTRATED_HEIGHT2,
                                    Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    if (condensedRectResult != null) {
                        System.out.printf("Rect found (OVERVIEW->MINING CONDENSED SCORDITE) - Width: %d and height: %d\n", condensedRectResult.width, condensedRectResult.height);
                        new ClickScreen().rightClickEvent(condensedRectResult);
                        foundRect = true;

                    } else if (scorditeRectResult != null) {
                        System.out.printf("Rect found (OVERVIEW->MINING - SCORDITE) - Width: %d and height: %d\n", scorditeRectResult.width, scorditeRectResult.height);
                        new ClickScreen().rightClickEvent(scorditeRectResult);
                        foundRect = true;

                    } else if (denseRectResult != null) {
                        System.out.printf("Rect found (OVERVIEW->MINING - DENSE VELDSPAR) - Width: %d and height: %d\n", denseRectResult.width, denseRectResult.height);
                        new ClickScreen().rightClickEvent(denseRectResult);
                        foundRect = true;

                    } else if (concentratedResult != null) {
                        System.out.printf("Rect found (OVERVIEW->MINING - CONCENTRATED VELDSPAR) - Width: %d and height: %d\n", concentratedResult.width, concentratedResult.height);
                        new ClickScreen().rightClickEvent(concentratedResult);
                        foundRect = true;

                    } else if (veldsparResult != null) {
                        System.out.printf("Rect found (OVERVIEW->MINING - VELDSPAR) - Width: %d and height: %d\n", veldsparResult.width, veldsparResult.height);
                        new ClickScreen().rightClickEvent(veldsparResult);
                        foundRect = true;

                    } else {
                        System.out.println("rect not found");
                    }

                    if (foundRect) {
                        amountRect++;
                        flagNoDragScreen = true;
                    }
                    
                } // end case 0
            }

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

        } while (amountRect < amountRect);

    }

}
