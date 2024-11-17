package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.facades.MiningFacade;
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

        String astBeltPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\IOlogs\\logsfiles\\switchbelt.txt";
        String logRoutePath = "C:\\Users\\Flavio\\Desktop\\spr.txt";

        Triplet<Integer, Integer, Integer> whiteRGB = new Triplet<>(192, 192, 192);
        Triplet<Integer, Integer, Integer> shadesOfGreen = new Triplet(100, 125, 100);

        boolean switchAstBelt = true;
        boolean isCheckWarpable = false;

        int numberOfAlertLoops = 3;

        Thread.sleep(4000);
        new MiningFacade(
                waitCargoDepositMS,
                astBeltPath, waitForWarpMS, isCheckWarpable, whiteRGB, switchAstBelt, attempts, shadesOfGreen,
                logRoutePath,
                numberOfAlertLoops)
                .startMining();
    }
}
