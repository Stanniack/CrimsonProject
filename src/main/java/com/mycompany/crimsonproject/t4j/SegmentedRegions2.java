/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimsonproject.t4j;

import com.mycompany.crimsonproject.robot.DragClickEventInInventoryStation;
import com.mycompany.crimsonproject.robot.UndockEvent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Mateus
 */
public class SegmentedRegions2 {

    public List<Rectangle> createSegment() throws IOException, TesseractException, AWTException, InterruptedException {

        Tesseract instance = new Tesseract();
        instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datateriners\\");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");

        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        return result;

    }

    public Rectangle getSegmentedRegion(int x, int y) throws IOException, TesseractException {

        Tesseract instance = new Tesseract();
        instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datateriners\\");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");

        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);
        
        /* This doesn't check which side (x) of screen the rectangle is. */
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).x == x && result.get(i).y == y)
                return result.get(i);
            
        }
        
        return null;
        
    }

}
