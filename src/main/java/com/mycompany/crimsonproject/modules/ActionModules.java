package com.mycompany.crimsonproject.modules;

import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
public class ActionModules {

    public void propulsion() {
        try {
            new KeyboardEvents().clickKey(KeyEvent.VK_F3);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void launchDrones() {
        try {
            new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void engageDrones() {
        try {
            new KeyboardEvents().clickKey(KeyEvent.VK_F);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void returnDrones(int waitForDronesMS) {
        try {
            this.returnAndOrbitDrones();
            new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
            Thread.sleep(waitForDronesMS);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void returnAndOrbitDrones() {
        try {
            new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_ALT, KeyEvent.VK_R);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
