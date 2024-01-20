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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class ExtractOre2 {

    public void extract() throws IOException, TesseractException, AWTException, InterruptedException {
        int amountRect = 0;

        do {

            SegmentedRegions3 sr3 = new SegmentedRegions3();
            boolean flagNoDragScreen = false;
            new TakeScreenShot2().take();

            switch (amountRect) {
                case 0 -> {

                    HashMap<String, Rectangle> rectResult = sr3.getSegmentedRegionsAllOres_BLOCKSCREEN(
                            Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                            Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    if (rectResult != null) {
                        System.out.println("Hash map size: " + rectResult.size());
                        for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {
                            System.out.println(item.getKey() + ": " + item.getValue());
                            new ClickScreen().leftClickEvent(item.getValue());
                        }

                        //System.out.printf("Rect found - Width: %d and height: %d\n", rectResult.width, rectResult.height);
                        //new ClickScreen().rightClickEvent(rectResult);
                        amountRect++;
                        flagNoDragScreen = true;

                    } else {
                        System.out.println("rect not found");
                    }

                } // end case 0
            }

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

        } while (amountRect < amountRect);

    }

}
