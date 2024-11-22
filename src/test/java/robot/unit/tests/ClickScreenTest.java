package robot.unit.tests;

import com.mycompany.crimsonproject.robot.ClickScreenEvents;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author Devmachine
 */
public class ClickScreenTest {

    @Test
    public void verifyDoubleClick() {
        ClickScreenEvents cs = new ClickScreenEvents();
        Rectangle rect = new Rectangle(600, 600, 0, 0);

        try {
            cs.doubleClick(rect);
        } catch (AWTException ex) {
            Logger.getLogger(ClickScreenTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
