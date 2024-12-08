package com.mycompany.crimsonproject.handlers;

import com.mycompany.crimsonproject.windowschecker.WindowsService;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class WindowsServiceHandler {

    private String GUIname = "EVE";
    private String exeName = "exefile.exe";

    private WindowsService wService;
    private SleeperHandler sleeper;

    public WindowsServiceHandler() {
        this.wService = new WindowsService();
        this.sleeper = sleeper = new SleeperHandler();

    }

    /**
     * Verifies the status of a window based on its name, executable, and
     * fullscreen state. If the conditions are not met, it will continuously
     * check and wait for the conditions to be true.
     *
     * @param ms The time (in milliseconds) to wait between checks when the
     * conditions are not met.
     */
    public void windowsChecker(Integer ms) {
        Triplet<String, String, Boolean> GUIstats = wService.activeWindowsChecker();

        while (GUIstats != null && !(GUIstats.getValue0().contains(GUIname) && GUIstats.getValue1().equals(exeName) && GUIstats.getValue2())) {
            sleeper.sleep(ms);
            GUIstats = wService.activeWindowsChecker();
        }
    }
}
