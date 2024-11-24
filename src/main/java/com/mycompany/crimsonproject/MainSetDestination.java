package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.resolutions.R1920x1080Small;
import com.mycompany.crimsonproject.scripts.SetDestination;
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
public class MainSetDestination {

    public static void main(String[] args) {
        final int GOTO_HOMESTATION = 0;
        final int GOTO_MININGBOT = 1;
        int waitForWarp = 40000;
        boolean isCheckWarpable = false;
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\switchbelt.txt";

        try {
            Thread.sleep(4000);
            List<Pair<Integer, Integer>> astBelt = new R1920x1080Small().getAstBeltsMap().get(new TextLogs().readLine(path));
            //new SetDestination(astBelt, GOTO_MININGBOT, waitForWarp, isCheckWarpable, new Triplet<>(192, 192, 192)).startScript();
            new SetDestination(new R1920x1080Small().getHomeStationList(), GOTO_HOMESTATION, waitForWarp, isCheckWarpable, new Triplet<>(192, 192, 192))
                    .startScript();

        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
