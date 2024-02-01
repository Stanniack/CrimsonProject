package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.scripts.CheckCargoDeposit;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import com.mycompany.crimsonproject.scripts.GetDestination;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Stanniack
 */
public class CrimsonProject {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(4000);

        for (int i = 0; i < 1; i++) {
            try {

                new CheckCargoDeposit().check();
                Thread.sleep(15000);

                new GetDestination().getDestination(1);
                Thread.sleep(25000);

                new ExtractOre().extract();
                //Thread.sleep(35000);

            } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
                Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
