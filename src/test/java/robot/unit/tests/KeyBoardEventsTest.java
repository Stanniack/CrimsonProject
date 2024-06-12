package robot.unit.tests;

import com.mycompany.crimsonproject.robot.KeyboardEvents;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Devmachine
 */
public class KeyBoardEventsTest {

    @BeforeEach
    public void sleep() {
        try {
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            Logger.getLogger(KeyBoardEventsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void verifyPressFn() {
        try {
            KeyboardEvents kbe = new KeyboardEvents();
            kbe.clickKey(KeyEvent.VK_F1);

        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(KeyBoardEventsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
