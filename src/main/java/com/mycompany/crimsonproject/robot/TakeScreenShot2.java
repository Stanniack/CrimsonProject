/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Mateus
 */
public class TakeScreenShot2 {
    
    public void take () throws InterruptedException {
        try {
            Thread.sleep(300);
            
            Robot bot = new Robot();
            String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\screenshot.png";
            
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
            BufferedImage bf = bot.createScreenCapture(rectangle);
            ImageIO.write(bf, "png", new File(path));
            
            System.out.println("Screenshot created in path: " + path);
            System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
            
        } catch (AWTException | IOException ex ) {
            Logger.getLogger(TakeScreenShot2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
