package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.scripts.ExtractOre;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class MainExtractOre {

    public static void main(String[] args) {
        boolean isSwitchable = false;
        int waitForWarp = 55000;

        try {
            Thread.sleep(4000);
            new ExtractOre(isSwitchable, waitForWarp).startScript();
        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
