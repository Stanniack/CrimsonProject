package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.robot.DragClickEventInInventoryStation;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot2;
import com.mycompany.crimsonproject.robot.UndockEvent;
import com.mycompany.crimsonproject.t4j.SegmentedRegions;
import com.mycompany.crimsonproject.utils.Rect1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import net.sourceforge.tess4j.TesseractException;

/**
*
* @author Stanniack
* 
*  No I.A recognition for stack items in mining cargo and "item hangar"
* Search a word on EVE.exe Left hud: min, fontscale: 100%, EVE fontsize: 13 (small), resolution: 1920x1080
* Check cargo, drag itens and undock 
*/
public class CheckCargoDeposit {

    public void check() throws InterruptedException, IOException, AWTException, TesseractException {

        int amountRect = 0;


        /* Be aware about this infinite loop */
        do {
            new TakeScreenShot2().take();
            SegmentedRegions sr3 = new SegmentedRegions();
            List<Rectangle> result = sr3.createSegment();

            for (int i = 0; i < result.size(); i++) {

                /* First search max */
                // Right side on screen, width and height
                if (result.get(i).x < 600 && ((result.get(i).width == Rect1920x1080.MAXCARGO1_W1 || result.get(i).width == Rect1920x1080.MAXCARGO1_W2)
                        && result.get(i).height == Rect1920x1080.MAXCARGO1_H1)) {

                    // Rect found
                    amountRect++;
                    System.out.printf("Rect found maxCargo - Width: %d and height: %d\n\n", result.get(i).width, result.get(i).height);

                    /* If found max cargo so deposit all cargo in hangar, UNDOCK and break for */
                    new DragClickEventInInventoryStation().eventClick();
                    new UndockEvent().eventClick();
                    break;

                } else {
                    /* Then Search min */
                    // Right side on screen, width and height
                    if (result.get(i).x < 600 && ((result.get(i).width == Rect1920x1080.MINGCARGO_WITHOUTM3_W1
                            || result.get(i).width == Rect1920x1080.MINGCARGO_WITHM3_W1)
                            && result.get(i).height == Rect1920x1080.MINGCARGO_H1)) {

                        // Rect found
                        amountRect++;
                        System.out.printf("Rect found minCargo - Width: %d and height: %d\n\n", result.get(i).width, result.get(i).height);

                        /* If found min cargo then UNDOCK and break for */
                        new UndockEvent().eventClick();
                        break;

                    }
                }
            }

            if (amountRect == 0) {
                System.out.println("Rect not found!\n\n");
                new DragScreen().eventClick();
            }

        } while (amountRect == 0);

    }

}
