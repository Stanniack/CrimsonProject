package RobotUnitTests;

import com.mycompany.crimsonproject.robot.ClickScreen;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author Devmachine
 */


public class ClickScreenTest {

    @Test
    public void VerifydoubleClick() {
        ClickScreen cs = new ClickScreen();
        Rectangle rect = new Rectangle(600, 600, 0, 0);
        
        try {
            cs.doubleClick(rect);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(ClickScreenTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }
}
