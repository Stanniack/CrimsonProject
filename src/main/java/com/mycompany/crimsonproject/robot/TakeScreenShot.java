package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Stanniack
 */
public class TakeScreenShot {

    private static final int SLEEP_MS = 200;

    public void take() throws InterruptedException, AWTException, IOException {
        Thread.sleep(SLEEP_MS);

        Robot bot = new Robot();
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\screenshot.png";

        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

        BufferedImage bf = bot.createScreenCapture(rectangle);
        ImageIO.write(bf, "png", new File(path));

        System.out.println("Screenshot created in path: " + path);
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());

    }

}
