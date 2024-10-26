package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.facades.MiningFacade;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
import java.util.List;
import org.javatuples.Pair;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class MainFacade {

    public static void main(String[] args) throws InterruptedException {
        int waitCargoDepositMS = 12000;
        int waitForWarpMS = 65000;
        int attempts = 9;

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\switchbelt.txt";
        String logRoutePath = "C:\\Users\\Flavio\\Desktop\\spr.txt";
        List<Pair<Integer, Integer>> astBelt = new R1920x1080().getAstBeltsMap().get(new TextLogs().readLine(path));

        Triplet<Integer, Integer, Integer> whiteRGB = new Triplet<>(192, 192, 192);
        Triplet<Integer, Integer, Integer> shadesOfGreen = new Triplet(100, 125, 100);

        boolean switchAstBelt = true;
        boolean isCheckWarpable = true;

        int numberOfAlertLoops = 3;

        Thread.sleep(4000);
        new MiningFacade(
                waitCargoDepositMS,
                astBelt, waitForWarpMS, isCheckWarpable, whiteRGB, switchAstBelt, attempts, shadesOfGreen,
                logRoutePath,
                numberOfAlertLoops)
                .startMining();
    }
}
