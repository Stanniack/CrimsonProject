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
    private List<Integer> closestOreList = Arrays.asList(closestCSOre, closestSOre, closestDVOre, closestCVOre, closestVOre);

    public void extract() throws IOException, TesseractException, AWTException, InterruptedException {
        int amountRect = 0;
        int switchFlag = 1;

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

                        for (Map.Entry<String, Rectangle> item : rectResult.entrySet()) {

                            for (int i = 0; i < priorityList.size(); i++) {

                                if (item.getKey().contains("P" + i) && priorityOre <= priorityList.get(i)) {

                                    System.out.println(item.getKey() + ": " + item.getValue().y + "y");

                                    if (item.getValue().y < closestOreList.get(i)) {
                                        betterOre = item;
                                        closestOreList.set(i, item.getValue().y);
                                        priorityOre = priorityList.get(i);
                                    }
                                }
                            }

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

        } while (amountRect < switchFlag);

    }

}
