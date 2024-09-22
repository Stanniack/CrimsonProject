package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.resolutions.R1920x1080;
import com.mycompany.crimsonproject.scripts.SetDestination;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class MainSetDestination {

    public static void main(String[] args) {
        final int GOTO_HOMESTATION = 0;
        final int GOTO_MININGBOT = 1;
        int waitForWarp = 55000;

        try {
            Thread.sleep(4000);
            //new SetDestination(new R1920x1080().getAstBeltIIIIIList(), GOTO_MININGBOT).startScript();
            new SetDestination(new R1920x1080().getHomeStationList(), GOTO_HOMESTATION, waitForWarp).startScript();

        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
