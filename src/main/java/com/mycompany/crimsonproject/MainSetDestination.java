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
public class MainSetDestination {

    public static void main(String[] args) {
        final int GOTO_HOMESTATION = 0;
        final int GOTO_MININGBOT = 1;

        try {

            Thread.sleep(4000);
            new SetDestination().startScript(GOTO_MININGBOT);

        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
