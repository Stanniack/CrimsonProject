package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.scripts.GetDestination2;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Devmachine
 */
public class MainGetDestination2 {

    public static void main(String[] args) {

        try {

            Thread.sleep(4000);
            new GetDestination2().strartScript(0);

        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
