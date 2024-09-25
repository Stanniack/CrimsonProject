package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
import com.mycompany.crimsonproject.scripts.CargoDeposit;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import com.mycompany.crimsonproject.scripts.SetDestination;
import com.mycompany.crimsonproject.soundlogs.SoundAlert;
import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;
import org.javatuples.Pair;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class MainPC {

    public static void main(String[] args) throws InterruptedException {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\switchbelt.txt";
        final int GOTO_MININGBOT = 1;
        boolean isSwitchable = false;
        int waitForWarp = 35000;
        int giveAtry = 13;
        Triplet<Integer, Integer, Integer> tonsOfGreen = new Triplet(100, 125, 100);

        Thread.sleep(4000);

        for (int i = 0; i < 27; i++) {
            Long start = System.currentTimeMillis();
            try {

                // script 1
                new CargoDeposit().startScript();
                Thread.sleep(12000);

                // script 2
                List<Pair<Integer, Integer>> astBelt = new R1920x1080().getAstBeltsMap().get(new TextLogs().readLine(path));
                new SetDestination(astBelt, GOTO_MININGBOT, waitForWarp).startScript();

                // script 3
                boolean isFalse = new ExtractOre(isSwitchable, waitForWarp, giveAtry, tonsOfGreen).startScript();
                if (!isFalse) {
                    break;
                }

                new TextLogs().timePerRoute("C:\\Users\\Flavio\\Desktop\\spr.txt", Long.toString((System.currentTimeMillis() - start) / 1000) + "\n");

            } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
                Logger.getLogger(MainPC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //end of mining
        new SoundAlert().start(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\soundlogs\\soundfiles\\endofmining.wav", 1);
    }
}
