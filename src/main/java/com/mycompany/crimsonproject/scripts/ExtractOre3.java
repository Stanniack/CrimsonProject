/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreen;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions3;
import com.mycompany.crimsonproject.utils.Rect1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class ExtractOre3 {

    private final Integer CSpriority = 5;
    private final Integer Spriority = 4;
    private final Integer DVpriority = 3;
    private final Integer CVpriority = 2;
    private final Integer Vpriority = 1;
    private Integer closestCSOre = 1081;
    private Integer closestSOre = 1081;
    private Integer closestDVOre = 1081;
    private Integer closestCVOre = 1081;
    private Integer closestVOre = 1081;

    /* These lists must be ordered by priority, from highest to lowest to get the closest and better ore possibly */
    private final List<Integer> priorityList = Arrays.asList(CSpriority, Spriority, DVpriority, CVpriority, Vpriority);
    /* In the resolution 1920x1080, 1081 isn't found */
    //List<Integer> closestOreList = Arrays.asList(closestCSOre, closestSOre, closestDVOre, closestCVOre, closestVOre);

    public void extract() throws IOException, TesseractException, AWTException, InterruptedException {
        int amountRect = 0;
        int switchFlag = 2;

        do {

            SegmentedRegions3 sr3 = new SegmentedRegions3();
            boolean flagNoDragScreen = false;
            Entry<String, Rectangle> betterOre = null;
            int priorityOre = 0;

            /* Reset Y list coordinates to 1081y */
            //this.resetYCoordinate();
            new TakeScreenShot2().take();

            switch (amountRect) {

                case 0 -> {

                    /* Reset Y list coordinates to 1081y */
                    List<Integer> closestOreList = Arrays.asList(1081, 1081, 1081, 1081, 1081);

                    HashMap<String, Rectangle> rectResult = sr3.getSegmentedRegionsAllOres_BLOCKSCREEN(
                            Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                            Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    if (rectResult.size() > 0) {

                        System.out.println("Hash map size: " + rectResult.size());

                        for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                            for (int i = 0; i < priorityList.size(); i++) {

                                if (item.getKey().contains("P" + i) && priorityOre <= priorityList.get(i)) {
                                    
                                    priorityOre = priorityList.get(i);
                                    
                                    System.out.println(item.getKey() + ": " + item.getValue().y + "y");
                                    System.out.println(item.getValue().y + " <? " + closestOreList.get(i));

                                    if (item.getValue().y <= closestOreList.get(i)) {
                                        betterOre = item;
                                        closestOreList.set(i, item.getValue().y);

                                    }
                                }
                            }

                        }

                        if (betterOre != null) {

                            System.out.println("Closest better ore found (Y Coordinate): "
                                    + betterOre.getKey() + " (X,Y) -> (" + betterOre.getValue().x + ", " + betterOre.getValue().y + ")");
                            //amountRect++;
                            flagNoDragScreen = true;
                            new ClickScreen().leftClickCenterButton(betterOre.getValue()); /////////////!!!

                        } else {
                            System.out.println("Better ore is null");
                        }

                    } else {
                        System.out.println("rect not found");
                    }

                    System.out.println(); //

                } // end case 0

                /*case 1 -> {
                    //For a millis seconds to take another screenshot, if not waiting by, the new screenshot doesn't take the right float window for click. 
                    Rectangle rectResult = sr3
                            .getSegmentedRegion_WxH_BLOCKSCREEN(Rect1920x1080.WARPARROW_WIDTH, Rect1920x1080.WARPARROW_HEIGHT,
                                    Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                                    Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    if (rectResult != null) {
                        System.out.printf("Rect found (WARPARROW) - Width: %d and height: %d at coordinates (%d, %d)\n",
                                rectResult.width, rectResult.height, rectResult.x, rectResult.y);

                        new ClickScreen().leftClickCenterButton(rectResult);
                        amountRect++; // go to case 2
                        flagNoDragScreen = true;

                    } else {
                        new ClickScreen().leftClick(); // click to disappear the arrow float window to restart the script case 1
                        amountRect--; // back to case 0 and find other ore
                    }
                }*/
            }

            if (!flagNoDragScreen) {
                new DragScreen().eventClick();
            }

        } while (true);

    }

    private void resetYCoordinate() {
        this.closestCSOre = 1081;
        this.closestSOre = 1081;
        this.closestDVOre = 1081;
        this.closestCVOre = 1081;
        this.closestVOre = 1081;
    }

}
