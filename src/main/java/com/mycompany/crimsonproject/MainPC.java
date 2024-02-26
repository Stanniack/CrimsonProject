package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.scripts.CheckCargoDeposit;
import com.mycompany.crimsonproject.scripts.CheckCargoDeposit2;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import com.mycompany.crimsonproject.scripts.ExtractOre2;
import com.mycompany.crimsonproject.scripts.GetDestination;
import com.mycompany.crimsonproject.scripts.GetDestination2;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Stanniack
 */
public class MainPC {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(4000);

        for (int i = 0; i < 5; i++) {
            try {

                new CheckCargoDeposit2().check();
                Thread.sleep(18000);

                new GetDestination2().getDestination(1);
                Thread.sleep(28000);

                new ExtractOre2().extract();
                Thread.sleep(35000);

            } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
                Logger.getLogger(MainPC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
