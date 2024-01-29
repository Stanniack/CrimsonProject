
package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.robot.DragScreen;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stanniack
 */
public class Main {
    public static void main(String[] args) {
        try {
            new DragScreen().eventClick();
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
