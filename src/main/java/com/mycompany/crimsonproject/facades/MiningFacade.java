package com.mycompany.crimsonproject.facades;

import com.mycompany.crimsonproject.IOlogs.TextLogs;
import com.mycompany.crimsonproject.scripts.CargoDeposit;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import com.mycompany.crimsonproject.scripts.SetDestination;
import com.mycompany.crimsonproject.soundlogs.SoundAlert;
import com.mycompany.crimsonproject.utils.CalendarUtils;
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
public class MiningFacade {

    private static final int GOTO_MININGBOT = 1;
    private final int waitCargoDepositMS;
    private final int waitForWarpMS;
    private final int numberOfAlertLoops;
    private final int attempts;

    private final List<Pair<Integer, Integer>> astBelt;
    private final Triplet<Integer, Integer, Integer> whiteRGB;
    private final Triplet<Integer, Integer, Integer> shadeOfWhite;

    private final boolean switchAstbelt;
    private final boolean isCheckWarpable;

    private SetDestination setDestination;

    private final String logRoutePath;

    private Long startRouteTime = 0L;

    /**
     * @param waitCargoDepositMS to wait in milliseconds until start script
     * after cargo deposit
     * @param astBelt a list of pair containing id of asteroid belt and its
     * rectangle
     * @param waitForWarpMS to wait in milliseconds until reach to destination
     * before start the mining script
     * @param isCheckWarpable true if it is possible to verify by pixels methods
     * if the ship is still warping before start mining
     * @param whiteRGB a triplet of RGB to verify shade of white in pixels
     * method of warping
     * @param switchAstbelt true if the script is enable to switch asteroid
     * belts
     * @param attempts number of tries to check if the miner cannons are
     * activated
     * @param shadesOfGreen a triplet of shades of green to check with attempts
     * to maximize the search for active miner cannons
     * @param logRoutePath directory to create a .txt log with times in
     * milliseconds per routes of mining
     * @param numberOfAlertLoops number of alerts loopings to play
     */
    public MiningFacade(
            int waitCargoDepositMS,
            List<Pair<Integer, Integer>> astBelt, int waitForWarpMS, boolean isCheckWarpable, Triplet<Integer, Integer, Integer> whiteRGB,
            boolean switchAstbelt, int attempts, Triplet<Integer, Integer, Integer> shadesOfGreen,
            String logRoutePath,
            int numberOfAlertLoops) {

        this.waitCargoDepositMS = waitCargoDepositMS;
        this.astBelt = astBelt;
        this.waitForWarpMS = waitForWarpMS;
        this.isCheckWarpable = isCheckWarpable;
        this.whiteRGB = whiteRGB;
        this.switchAstbelt = switchAstbelt;
        this.attempts = attempts;
        this.shadeOfWhite = shadesOfGreen;
        this.logRoutePath = logRoutePath;
        this.numberOfAlertLoops = numberOfAlertLoops;
    }

    /**
     * Mine until the server shutdown
     */
    public void startMining() {
        CalendarUtils calendar = new CalendarUtils();
        int minutes = 25;

        while (!calendar.isServerSave(minutes)) {
            flowScript();
            this.playAlertOfEnd();
        }
    }

    /**
     * Mine until the end of loopings
     *
     * @param loops number of mining cycles
     */
    public void startMining(int loops) {
        for (int i = 0; i < loops; i++) {
            flowScript();
            this.playAlertOfEnd();
        }
    }

    private void flowScript() {
        this.startRouteTime = System.currentTimeMillis();
        try {
            this.cargoDeposit();
            this.gotoAstBelt();
            if (!this.extractOre()) {
                return; // Use 'return' insted of 'break'
            }
            this.logRouteTime();
        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(MiningFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargoDeposit() throws InterruptedException, IOException, AWTException, TesseractException {
        new CargoDeposit().startScript();
        Thread.sleep(waitCargoDepositMS);
    }

    private void gotoAstBelt() throws IOException, TesseractException, AWTException, InterruptedException {
        this.setDestination = new SetDestination(astBelt, GOTO_MININGBOT, waitForWarpMS, isCheckWarpable, whiteRGB);
        this.setDestination.startScript();
    }

    private boolean extractOre() throws IOException, TesseractException, AWTException, InterruptedException {
        return new ExtractOre(setDestination, switchAstbelt, attempts, shadeOfWhite).startScript();
    }

    private void playAlertOfEnd() {
        new SoundAlert().start(System.getProperty("user.dir")
                + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\soundlogs\\soundfiles\\endofmining.wav", numberOfAlertLoops);
    }

    private void logRouteTime() {
        new TextLogs().timePerRoute(logRoutePath, Long.toString((System.currentTimeMillis() - startRouteTime) / 1000) + "\n");
    }

}
