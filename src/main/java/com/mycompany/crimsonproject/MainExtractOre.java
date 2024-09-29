package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.scripts.ExtractOre;
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
        int giveAtry = 9;
        Triplet<Integer, Integer, Integer> tonsOfGreen = new Triplet(100, 125, 100);

        try {
            Thread.sleep(4000);
            new ExtractOre(isSwitchable, giveAtry, tonsOfGreen)
                    .startScript();
        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
