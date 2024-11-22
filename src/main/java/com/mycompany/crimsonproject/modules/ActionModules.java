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

    private final KeyboardEvents keyBoardEvents;

    public ActionModules() {
        keyBoardEvents = new KeyboardEvents();
    }

    public void propulsion() {
        try {
            keyBoardEvents.clickKey(KeyEvent.VK_F3);
        } catch (AWTException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void launchDrones() {
        try {
            keyBoardEvents.pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
        } catch (AWTException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void engageDrones() {
        try {
            keyBoardEvents.clickKey(KeyEvent.VK_F);
        } catch (AWTException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void engageDrones(int waitMS) {
        try {
            Thread.sleep(waitMS);
            keyBoardEvents.clickKey(KeyEvent.VK_F);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ActionModules.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void returnDrones(int waitForDronesMS) {
        try {
            returnAndOrbitDrones();
            keyBoardEvents.pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
            Thread.sleep(waitForDronesMS);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void returnAndOrbitDrones() {
        try {
            keyBoardEvents.pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_ALT, KeyEvent.VK_R);
        } catch (AWTException ex) {
            Logger.getLogger(ExtractOre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
