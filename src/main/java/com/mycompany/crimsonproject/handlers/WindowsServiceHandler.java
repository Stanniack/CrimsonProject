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
     * Checks if a specific window is active by repeatedly verifying the
     * window's status until the conditions are met.
     *
     * @param ms the time in milliseconds to wait between each check.
     */
    public void windowsChecker(Integer ms) {
        Triplet<String, String, Boolean> GUIstats = wService.activeWindowsChecker();

        while (GUIstats != null && !(GUIstats.getValue0().contains(GUIname) && GUIstats.getValue1().equals(exeName) && GUIstats.getValue2())) {
            sleeper.sleep(ms);
            GUIstats = wService.activeWindowsChecker();
        }
    }
}
