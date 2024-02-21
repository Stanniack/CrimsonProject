package RobotUnitTests;

import com.mycompany.crimsonproject.robot.KeyboardEvents;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author Devmachine
 */
public class KeyBoardEventsTest {

    @Test
    public void verifyPressFn() {
        try {
            KeyboardEvents kbe = new KeyboardEvents();

            kbe.pressFn(KeyEvent.VK_F1);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(KeyBoardEventsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
