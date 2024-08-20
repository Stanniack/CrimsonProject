        package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.scripts.CargoDeposit;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import com.mycompany.crimsonproject.scripts.SetDestination;
import com.mycompany.crimsonproject.soundlogs.SoundAlert;
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

        for (int i = 0; i < 3; i++) {
            Long start = System.currentTimeMillis();
            try {

                new CargoDeposit().startScript();
                Thread.sleep(18000);

                new SetDestination(1).startScript();
                Thread.sleep(62000);

                boolean isFalse = new ExtractOre().startScript();
                if (!isFalse) {
                    break;
                }
                Thread.sleep(62000);

            } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
                Logger.getLogger(MainPC.class.getName()).log(Level.SEVERE, null, ex);
            }

            new TextLogs().timePerRoute("C:\\Users\\Flavio\\Desktop\\spr.txt", Long.toString((System.currentTimeMillis() - start) / 1000));
        }
        
        //end of mining
        new SoundAlert().start(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\soundlogs\\soundfiles\\endofmining.wav", 6);

    }
}
