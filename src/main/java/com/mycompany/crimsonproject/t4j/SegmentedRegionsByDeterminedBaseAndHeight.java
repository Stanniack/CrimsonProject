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
public class SegmentedRegionsByDeterminedBaseAndHeight {

    public int createSegment() throws IOException, TesseractException, AWTException, InterruptedException {

        Tesseract instance = new Tesseract();
        instance.setDatapath("C:\\Users\\Mateus\\OneDrive\\Documentos\\T4Jtrainers");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");

        File imageFile = new File("C:\\Users\\Mateus\\OneDrive\\Imagens\\Screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);
        List<Rectangle> desiredResult = new ArrayList<>();


        /* Search a word on EVE.exe fontscale: 100%, EVE fontsize: 13 (small), resolution: 1920x1080 */
        // First search max
        for (int i = 0; i < result.size(); i++) {

            // Right side on screen, width and height
            if (result.get(i).x < 600 && ((result.get(i).width == 111 || result.get(i).width == 110) && result.get(i).height == 24)) {
                
                desiredResult.add(result.get(i));
                System.out.printf("Width: %d and height: %d\n", result.get(i).width, result.get(i).height);

                /* If found max cargo so deposit all cargo in hangar, UNDOCK and break for */
                new DragClickEventInInventoryStation().eventClick();
                new UndockEvent().eventClick();
                break;

            } else {
                // Then Search min
                // Right side on screen, width and height
                if (result.get(i).x < 600 && ((result.get(i).width == 51 || result.get(i).width == 72) && result.get(i).height == 9)) {

                    desiredResult.add(result.get(i));
                    System.out.printf("Width: %d and height: %d\n", result.get(i).width, result.get(i).height);

                    /* If found min cargo so UNDOCK and break for */
                    new UndockEvent().eventClick();
                    break;

                }
            }
        }

        if (desiredResult.isEmpty()) {
            System.out.println("Rect not found!\n");
        }

        return desiredResult.size();

    }

}
