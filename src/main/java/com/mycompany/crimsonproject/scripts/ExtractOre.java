/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreen;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.t4j.SegmentedRegions2;
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
        boolean flagNoDragScreen = false;

        do {

            Rectangle rectResult = new SegmentedRegions2()
                    .getSegmentedRegion_2WxH_2Xx2Y(Rect1920x1080.CONDENSED_WIDTH1, Rect1920x1080.CONDENSED_WIDTH2, Rect1920x1080.CONDENSED_HEIGHT1,
                            Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                            Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);
            
            // for percorrendo lista de rects aqui - utilizar lista ordenada

            if (rectResult != null) {
                System.out.printf("Rect found (OVERVIEW->MINING SCORDITE) - Width: %d and height: %d\n", rectResult.width, rectResult.height);
                new ClickScreen().rightClickEvent(rectResult);
                amountRect++;
                flagNoDragScreen = true;
            } else {
                System.out.println("rect not found");
            }
            
            if (!flagNoDragScreen)
                new DragScreen().eventClick();
            
        } while (amountRect < 0);

    }

}
