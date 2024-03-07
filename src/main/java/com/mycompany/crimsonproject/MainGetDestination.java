package com.mycompany.crimsonproject;

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
public class MainGetDestination {

    public static void main(String[] args) {

        try {

            Thread.sleep(4000);
            new SetDestination().startScript(1);

        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
