package com.mycompany.crimsonproject.t4j;

import com.mycompany.crimsonproject.sort.RectComparatorByY;
import com.mycompany.crimsonproject.utils.Rect1920x1080;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Stanniack
 */
public class SegmentedRegions {

    Tesseract instance;
    File imageFile;
    BufferedImage bf;

    public SegmentedRegions() {
        instance = new Tesseract();
        instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        instance.setLanguage("eng");
        instance.setTessVariable("user_defined_dpi", "300");
        imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");

    }

    public List<Rectangle> createSegment() throws IOException, TesseractException, AWTException, InterruptedException {

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        bf = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png"));

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        return result;

    }

    /* --------------------------------------- Methods with blosck screen --------------------------------------- */
    public Rectangle getSegmentedRegion_WxH_BLOCKSCREEN(int width1, int height1, int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).width == width1
                    && result.get(i).height == height1
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getSegmentedRegion_2WxH_BLOCKSCREEN(int width1, int width2, int height1, int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {

            if ((result.get(i).width == width1 || result.get(i).width == width2)
                    && result.get(i).height == height1
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getSegmentedRegion_Wx2H_BLOCKSCREEN(int width1, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).width == width1
                    && (result.get(i).height == height1 || result.get(i).height == height2)
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getSegmentedRegion_2Wx2H_BLOCKSCREEN(int width1, int width2, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {
            if ((result.get(i).width == width1
                    && result.get(i).height == height1)
                    || (result.get(i).width == width2 && result.get(i).height == height2)
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getSegmentedRegion_3Wx2H_BLOCKSCREEN(int width1, int width2, int width3, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {
            if (((result.get(i).width == width1 || result.get(i).width == width2)
                    && result.get(i).height == height1)
                    || (result.get(i).width == width3 && result.get(i).height == height2)
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getSegmentedRegionApproaching_2Wx3H_BLOCKSCREEN(int width1, int width2, int height1, int height2, int height3, int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).width == width1 && result.get(i).height == height2
                    || (result.get(i).width == width2 && (result.get(i).height == height1 || result.get(i).height == height3))
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getSegmentedRegionConcentrated_3Wx2H_BLOCKSCREEN(int width1, int width2, int width3, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {
            if (((result.get(i).width == width1 || result.get(i).width == width2) && result.get(i).height == height1)
                    || ((result.get(i).width == width2 || result.get(i).width == width3) && result.get(i).height == height2)
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getSegmentedRegionsAllRectsMaxCargo_BLOCKSCREEN(int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);

        for (int i = 0; i < result.size(); i++) {
            if (((result.get(i).width == Rect1920x1080.MAXCARGO1_W1 || result.get(i).width == Rect1920x1080.MAXCARGO1_W2)
                    && result.get(i).height == Rect1920x1080.MAXCARGO1_HEIGHT1)
                    || (result.get(i).width == Rect1920x1080.MAXCARGO2_W1 && result.get(i).height == Rect1920x1080.MAXCARGO2_H1)
                    || (result.get(i).width == Rect1920x1080.MAXCARGO3_W1 && result.get(i).height == Rect1920x1080.MAXCARGO3_H1)
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }
        }

        return null;
    }

    public HashMap<String, Rectangle> getSegmentedRegionsAllOres_BLOCKSCREEN(int x, int x2, int y, int y2) throws IOException, TesseractException {

        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = instance.getSegmentedRegions(bf, level);
        /* Sort from lower to bigger Y coordinate */
        Collections.sort(result, new RectComparatorByY());

        HashMap<String, Rectangle> hm = new HashMap<>();

        for (int i = 0; i < result.size(); i++) {

            /* If into block screen list ores area */
            if ((result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {

                if ((result.get(i).width == Rect1920x1080.CONDENSED_W1 || result.get(i).width == Rect1920x1080.CONDENSED_W2)
                        && result.get(i).height == Rect1920x1080.CONDENSED_H1) {
                    hm.put("P1:CS - i: " + i, result.get(i));

                }

                if (result.get(i).width == Rect1920x1080.SCORDITE_W1
                        && (result.get(i).height == Rect1920x1080.SCORDITE_H1 || result.get(i).height == Rect1920x1080.SCORDITE_H2)) {
                    hm.put("P2:S - i: " + i, result.get(i));

                }

                if ((result.get(i).width == Rect1920x1080.DENSE_W1 || result.get(i).width == Rect1920x1080.DENSE_W2)
                        && result.get(i).height == Rect1920x1080.DENSE_H1) {
                    hm.put("P3:DV - i: " + i, result.get(i));
                }

                if ((result.get(i).width == Rect1920x1080.CONCENTRATED_W1 || result.get(i).width == Rect1920x1080.CONCENTRATED_W2)
                        && result.get(i).height == Rect1920x1080.CONCENTRATED_H1
                        || (result.get(i).width == Rect1920x1080.CONCENTRATED_W2 || result.get(i).width == Rect1920x1080.CONCENTRATED_W3)
                        && result.get(i).height == Rect1920x1080.CONCENTRATED_H2) {
                    hm.put("P4:CV - i: " + i, result.get(i));
                }

                if (((result.get(i).width == Rect1920x1080.VELDSPAR_W1 || result.get(i).width == Rect1920x1080.VELDSPAR_W2)
                        && result.get(i).height == Rect1920x1080.VELDSPAR_H1)
                        || result.get(i).width == Rect1920x1080.VELDSPAR_W3 && result.get(i).height == Rect1920x1080.VELDSPAR_H2) {
                    hm.put("P5:V - i: " + i, result.get(i));
                }
            }
        }

        return hm;
    }

}
