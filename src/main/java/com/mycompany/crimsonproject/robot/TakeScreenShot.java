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
public class TakeScreenShot {
    
    public void take () {
        try {
            Robot bot = new Robot();
            String path = "C:\\Users\\Mateus\\OneDrive\\Imagens\\Screenshots\\screenshot.png";
            
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
            BufferedImage bf = bot.createScreenCapture(rectangle);
            ImageIO.write(bf, "png", new File(path));
            
            System.out.println("Screenshot created in path: " + path);
            System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
            
        } catch (AWTException | IOException ex ) {
            Logger.getLogger(TakeScreenShot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
