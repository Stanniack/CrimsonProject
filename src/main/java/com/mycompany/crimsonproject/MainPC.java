package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
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
        final int GOTO_MININGBOT = 1;
        boolean isSwitchable = true;
        Thread.sleep(4000);

        for (int i = 0; i < 2; i++) {
            Long start = System.currentTimeMillis();
            try {

                new CargoDeposit().startScript();
                Thread.sleep(12000);

                new SetDestination(new R1920x1080().getAstBeltIList(), GOTO_MININGBOT).startScript();

                boolean isFalse = new ExtractOre(isSwitchable).startScript();
                if (!isFalse) {
                    break;
                }

            } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
                Logger.getLogger(MainPC.class.getName()).log(Level.SEVERE, null, ex);
            }

            new TextLogs().timePerRoute("C:\\Users\\Flavio\\Desktop\\spr.txt", Long.toString((System.currentTimeMillis() - start) / 1000) + "\n");
        }

        //end of mining
        new SoundAlert().start(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\soundlogs\\soundfiles\\endofmining.wav", 1);

    }
}
