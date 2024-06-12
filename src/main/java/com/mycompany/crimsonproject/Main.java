package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.robot.KeyboardEvents;
import com.mycompany.crimsonproject.utils.SortBy;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
public class Main {
    public static void main(String[] args) {
        try {
            Thread.sleep(4000);
            new KeyboardEvents().pressKey(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
            Thread.sleep(2000);
            new KeyboardEvents().clickKey(KeyEvent.VK_F);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
