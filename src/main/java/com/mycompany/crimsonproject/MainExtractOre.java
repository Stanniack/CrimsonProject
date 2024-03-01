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

        try {

            Thread.sleep(4000);
            new ExtractOre().strartScript();

        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
