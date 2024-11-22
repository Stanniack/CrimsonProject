package com.mycompany.crimsonproject.handlers;

import com.mycompany.crimsonproject.interfaces.Sleeper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devmachine
 */
public class SleeperHandler implements Sleeper {

    @Override
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(SleeperHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
