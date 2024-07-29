package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author DevMachine
 */
public class TakeScreenshot2 extends RobotEvent {

    public void take() throws InterruptedException, AWTException, IOException {
        Thread.sleep(SLEEP_MS2);

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\screenshot.png";

        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

        BufferedImage bf = this.bot.createScreenCapture(rectangle);
        ImageIO.write(bf, "png", new File(path));

        //System.out.println("Screenshot created in path: " + path);
        //System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void take2() throws InterruptedException, AWTException, IOException {

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\screenshot.png";

        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

        BufferedImage bf = this.bot.createScreenCapture(rectangle);
        ImageIO.write(bf, "png", new File(path));

        //System.out.println("Screenshot created in path: " + path);
        //System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
    }

}
