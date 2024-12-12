package com.mycompany.crimsonproject.handlers;

import com.mycompany.crimsonproject.interfaces.Sleeper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
public class SleeperHandler implements Sleeper {

    /**
     * Pauses the execution for a specified number of milliseconds.
     *
     * @param milliseconds the duration in milliseconds to pause the execution.
     */
    @Override
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(SleeperHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
