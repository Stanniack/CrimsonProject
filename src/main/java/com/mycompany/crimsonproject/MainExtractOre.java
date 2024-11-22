package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.resolutions.R1920x1080;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import com.mycompany.crimsonproject.scripts.SetDestination;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class MainExtractOre {

    public static void main(String[] args) {
        boolean isSwitchable = true;
        int attempts = 9;
        int returnDroneMS = 10000;
        final int GOTO_HOMESTATION = 0;
        int waitForWarp = 35000;
        boolean isCheckWarpable = true;
        Triplet<Integer, Integer, Integer> tonsOfGreen = new Triplet(100, 125, 100);

        try {
            Thread.sleep(4000);
            new SetDestination(new R1920x1080().getHomeStationList(), GOTO_HOMESTATION, waitForWarp, isCheckWarpable, new Triplet<>(192, 192, 192)).startScript();
            new ExtractOre(isSwitchable, attempts, tonsOfGreen, returnDroneMS)
                    .startScript();
        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
