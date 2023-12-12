/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.CrimsonProject;
import com.mycompany.crimsonproject.robot.DragClickEventInInventoryStation;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.robot.UndockEvent;
import com.mycompany.crimsonproject.t4j.SegmentedRegionsByDeterminedBaseAndHeight;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Mateus
 */

/* No I.A recognition for stack items in mining cargo and "item hangar" */
/* Search a word on EVE.exe fontscale: 100%, EVE fontsize: 13 (small), resolution: 1920x1080 */
/* Check cargo, drag itens and undock */
public class CheckCargoDeposit2 {

    public void check() {
        try {

            int amountRect = 0;

            // First inicialization 
            Thread.sleep(4000);

            /* Be aware about this infinite loop */
            do {
                new TakeScreenShot().take();
                List<Rectangle> result = new SegmentedRegions().createSegment();

                for (int i = 0; i < result.size(); i++) {

                    /* First search max */
                    // Right side on screen, width and height
                    if (result.get(i).x < 600 && ((result.get(i).width == 111 || result.get(i).width == 110)
                            && result.get(i).height == 24)) {

                        // Rect found
                        amountRect++;
                        System.out.printf("Width: %d and height: %d\n", result.get(i).width, result.get(i).height);

                        /* If found max cargo so deposit all cargo in hangar, UNDOCK and break for */
                        new DragClickEventInInventoryStation().eventClick();
                        //new UndockEvent().eventClick();
                        break;

                    } else {
                        /* Then Search min */
                        // Right side on screen, width and height
                        if (result.get(i).x < 600 && ((result.get(i).width == 51 || result.get(i).width == 72)
                                && result.get(i).height == 9)) {

                            // Rect found
                            amountRect++;
                            System.out.printf("Width: %d and height: %d\n", result.get(i).width, result.get(i).height);

                            /* If found min cargo then UNDOCK and break for */
                            new UndockEvent().eventClick();
                            break;

                        }
                    }
                }

                if (amountRect == 0) {
                    System.out.println("Rect not found!\n");
                    new DragScreen().eventClick();
                }

            } while (amountRect == 0);

        } catch (InterruptedException | IOException | TesseractException | AWTException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
