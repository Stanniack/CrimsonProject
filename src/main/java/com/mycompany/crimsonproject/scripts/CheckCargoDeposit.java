/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.scripts;

import com.mycompany.crimsonproject.CrimsonProject;
import com.mycompany.crimsonproject.robot.DragScreen;
import com.mycompany.crimsonproject.robot.TakeScreenShot;
import com.mycompany.crimsonproject.t4j.SegmentedRegionsByDeterminedBaseAndHeight;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Mateus
 */

/* Check cargo, drag itens and undock */
public class CheckCargoDeposit {

    public void check() {
        try {

            int amountRect = 0;
            // First inicialization 
            Thread.sleep(4000);

            /* Be aware about this infinite loop */
            do {
                new TakeScreenShot().take();
                amountRect = new SegmentedRegionsByDeterminedBaseAndHeight().createSegment();

                if (amountRect == 0) {
                    new DragScreen().eventClick();
                }

            } while (amountRect == 0);

        } catch (InterruptedException | IOException | TesseractException | AWTException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
