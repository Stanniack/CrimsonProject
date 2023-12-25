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
        instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");

        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        return result;

    }

    /* Width, height */
    public Rectangle getSegmentedRegion_WxH(int width, int height) throws IOException, TesseractException {

        Tesseract instance = new Tesseract();
        instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");

        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* This doesn't check which side (x) of screen the rectangle is.
           Disable Help EVE button because its attribute have same width and height to Localization button */
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).width == width && result.get(i).height == height) {
                return result.get(i);
            }

        }

        return null;

    }

    /* Width, height, X and Y */
    public Rectangle getSegmentedRegionWxH_XxY(int width, int height, int x, int y) throws IOException, TesseractException {

        Tesseract instance = new Tesseract();
        instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");

        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* This doesn't check which side (x) of screen the rectangle is.
           Disable Help EVE button because its attribute have same width and height to Localization button */
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).width == width && result.get(i).height == height) {
                return result.get(i);
            }

        }

        return null;

    }


    /* Width, width, height */
    public Rectangle getSegmentedRegion_2WxH(int width, int width2, int height) throws IOException, TesseractException {

        Tesseract instance = new Tesseract();
        instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");

        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* This doesn't check which side (x) of screen the rectangle is.
           Disable Help EVE button because its attribute have same width and height to Localization button */
        for (int i = 0; i < result.size(); i++) {
            if ((result.get(i).width == width || result.get(i).width == width2) && result.get(i).height == height) {
                return result.get(i);
            }

        }

        return null;

    }

}
