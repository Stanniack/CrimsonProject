package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.scripts.CargoDeposit;
import com.mycompany.crimsonproject.scripts.CheckCargoDeposit;
import com.mycompany.crimsonproject.scripts.ExtractOre;
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
public class MainPC {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(4000);

        for (int i = 0; i < 9; i++) {
            Long start = System.currentTimeMillis();
            try {

                new CargoDeposit().startScript();
                Thread.sleep(18000);

                new SetDestination().startScript(1);
                Thread.sleep(60000);

                boolean isFalse = new ExtractOre().startScript();
                if (!isFalse) {
                    break;
                }
                Thread.sleep(60000);

            } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
                Logger.getLogger(MainPC.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("\n--------------------------------------Mins PER ROUTE: " + (System.currentTimeMillis() - start) / 1000 / 60);
        }

    }
}
