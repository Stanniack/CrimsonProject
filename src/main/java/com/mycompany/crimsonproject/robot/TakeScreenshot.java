package com.mycompany.crimsonproject.robot;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author DevMachine
 */
public class TakeScreenshot extends RobotEvent {

    /**
     * Pauses for a specified amount of time, then takes a screenshot of the
     * entire screen and saves it as a PNG image to the specified path. The
     * sleep duration is controlled by the constant {@code SLEEP_MS2}.
     *
     * @param path the file path where the screenshot will be saved.
     */
    public void take(String path) {
        try {
            sleep(SLEEP_MS2);

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            BufferedImage bf = bot.createScreenCapture(rectangle);

            ImageIO.write(bf, "png", new File(path));

        } catch (IOException ex) {
            Logger.getLogger(TakeScreenshot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Pauses for a specified amount of time, then takes a screenshot of the
     * entire screen and saves it as a PNG image to a default path. The sleep
     * duration is controlled by the constant {@code SLEEP_MS2}.
     */
    public void take() {
        try {
            sleep(SLEEP_MS2);

            String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\screenshot.png";

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            BufferedImage bf = bot.createScreenCapture(rectangle);

            ImageIO.write(bf, "png", new File(path));

        } catch (IOException ex) {
            Logger.getLogger(TakeScreenshot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Takes a screenshot of the entire screen and saves it as a PNG image to a
     * default path.
     */
    public void take2() {
        try {
            String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\screenshot.png";

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            BufferedImage bf = bot.createScreenCapture(rectangle);

            ImageIO.write(bf, "png", new File(path));

        } catch (IOException ex) {
            Logger.getLogger(TakeScreenshot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Takes a screenshot of the entire screen and saves it as a PNG image to
     * the specified path.
     *
     * @param path the file path where the screenshot will be saved.
     */
    public void take2(String path) {
        try {
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            BufferedImage bf = bot.createScreenCapture(rectangle);

            ImageIO.write(bf, "png", new File(path));

        } catch (IOException ex) {
            Logger.getLogger(TakeScreenshot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Takes a screenshot of the entire screen, converts it to the sRGB color
     * space, and saves it as a PNG image to a default path.
     */
    public void takeSRGB() {
        try {
            String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\screenshot.png";

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            BufferedImage bf = bot.createScreenCapture(rectangle);

            // Convert image to SRGB
            ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null);
            BufferedImage sRGBImage = op.filter(bf, null);

            ImageIO.write(sRGBImage, "png", new File(path));

        } catch (IOException ex) {
            Logger.getLogger(TakeScreenshot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
