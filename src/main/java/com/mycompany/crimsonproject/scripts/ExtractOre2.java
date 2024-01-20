/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.ClickScreen;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.sort.HashMapRectComparatorByY;
import com.mycompany.crimsonproject.t4j.SegmentedRegions2;
import com.mycompany.crimsonproject.t4j.SegmentedRegions3;
import com.mycompany.crimsonproject.utils.Rect1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class ExtractOre2 {

    private final int CSpriority = 5;
    private final int Spriority = 4;
    private final int DVpriority = 3;
    private final int CVpriority = 2;
    private final int Vpriority = 1;
    private int closestCSOre = 1081;
    private int closestSOre = 1081;
    private int closestDVOre = 1081;
    private int closestCVOre = 1081;
    private int closestVOre = 1081;

    public void extract() throws IOException, TesseractException, AWTException, InterruptedException {
        int amountRect = 0;

        do {

            SegmentedRegions3 sr3 = new SegmentedRegions3();
            boolean flagNoDragScreen = false;
            Entry<String, Rectangle> betterOre = null;
            int priorityOre = 0;
            new TakeScreenShot2().take();

            switch (amountRect) {
                case 0 -> {

                    HashMap<String, Rectangle> rectResult = sr3.getSegmentedRegionsAllOres_BLOCKSCREEN(
                            Rect1920x1080.OVERVIEWMINING_X, Rect1920x1080.OVERVIEWMINING_X2_W_BLOCKSCREEN,
                            Rect1920x1080.OVERVIEWMINING_Y, Rect1920x1080.OVERVIEWMINING_Y2_H_BLOCKSCREEN);

                    if (rectResult.size() > 0) {

                        System.out.println("Hash map size: " + rectResult.size());

                        /* This for doesn't find the closest and better ore, just the better ore found. */
                        for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                            if (item.getKey().contains("P1:CS") && priorityOre <= CSpriority) {

                                System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                                if (item.getValue().y < closestCSOre) {
                                    betterOre = item;
                                    closestCSOre = item.getValue().y;
                                    priorityOre = 5;
                                }

                            } else if (item.getKey().contains("P2:S") && priorityOre <= Spriority) {

                                System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                                if (item.getValue().y < closestSOre) {
                                    betterOre = item;
                                    closestSOre = item.getValue().y;
                                    priorityOre = 4;
                                }

                            } else if (item.getKey().contains("P3:DV") && priorityOre <= DVpriority) {

                                System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                                if (item.getValue().y < closestDVOre) {
                                    betterOre = item;
                                    closestDVOre = item.getValue().y;
                                    priorityOre = 3;
                                }

                            } else if (item.getKey().contains("P4:CV") && priorityOre <= CVpriority) {

                                System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                                if (item.getValue().y < closestCVOre) {
                                    betterOre = item;
                                    closestCVOre = item.getValue().y;
                                    priorityOre = 2;
                                }

                            } else {
                                if (item.getKey().contains("P5:V") && priorityOre <= Vpriority) {

                                    System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                                    if (item.getValue().y < closestVOre) {
                                        betterOre = item;
                                        closestVOre = item.getValue().y;
                                        priorityOre = 1;
                                    }
                                }
                            }

                            //
                        }

                        if (betterOre != null) {
                            System.out.println("Closest better ore found (Y Coordinate): " + betterOre.getKey() + " - " + betterOre.getValue().y + "y");
                            amountRect++;
                            flagNoDragScreen = true;
                            new ClickScreen().leftClickEvent(betterOre.getValue());
                        } else {
                            System.out.println("Better ore is null");
                        }

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
