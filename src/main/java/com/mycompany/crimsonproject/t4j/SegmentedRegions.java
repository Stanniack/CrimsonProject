package com.mycompany.crimsonproject.t4j;

import com.mycompany.crimsonproject.sort.RectComparatorByY;
import com.mycompany.crimsonproject.utils.R1920x1080SMALL;
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
 * @author Devmachine
 */
public class SegmentedRegions {

    Tesseract instance;
    BufferedImage bf;

    File imageFile;

    public SegmentedRegions() {
        this.instance = new Tesseract();
        this.instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        this.instance.setLanguage("eng");
        this.instance.setTessVariable("user_defined_dpi", "300");
        this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
    }

    public List<Rectangle> createSegment() throws IOException, TesseractException, AWTException, InterruptedException {

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        this.bf = ImageIO.read(this.imageFile);

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

        return result;

    }

    /* --------------------------------------- Methods with block screen --------------------------------------- */
    public Rectangle getT_WxH_BlocksScreen(int width1, int height1, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(this.imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

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

    public Rectangle getT_2WxH_BlockScreen(int width1, int width2, int height1, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(this.imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);


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

    public Rectangle getT_Wx2H_BlockScreen(int width1, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

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

    public Rectangle getT_2Wx2H_BlockScreen(int width1, int width2, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

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

    public Rectangle getT_3Wx2H_BlockScreen(int width1, int width2, int width3, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(this.imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

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

    public Rectangle getShipHangar_2Wx2H_BlockScreen(int width1, int width2, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

        /* it will have pixel ranges in coordinates X or Y or both sent by who calls this method. */
        for (int i = 0; i < result.size(); i++) {
            if (((result.get(i).width == width1
                    && (result.get(i).height == height1 || result.get(i).height == height2))
                    || (result.get(i).width == width2 && result.get(i).height == height2))
                    && (result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {
                return result.get(i);
            }

        }

        return null;

    }

    public Rectangle getApproaching_2Wx3H_BlockScreen(int width1, int width2, int height1, int height2, int height3, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

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

    public Rectangle getConcentrated_3Wx2H_BlockScreen(int width1, int width2, int width3, int height1, int height2, int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

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

    public HashMap<String, Rectangle> getAllOres_BlockScreen(int x, int x2, int y, int y2) throws IOException, TesseractException {

        this.bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

        /* Sort from lower to bigger Y coordinate */
        Collections.sort(result, new RectComparatorByY());

        HashMap<String, Rectangle> hm = new HashMap<>();

        for (int i = 0; i < result.size(); i++) {

            /* If into block screen list ores area */
            if ((result.get(i).x >= x && result.get(i).x <= x2)
                    && (result.get(i).y >= y && result.get(i).y <= y2)) {

                if ((result.get(i).width == R1920x1080SMALL.CONDENSED_W1 || result.get(i).width == R1920x1080SMALL.CONDENSED_W2)
                        && result.get(i).height == R1920x1080SMALL.CONDENSED_H1) {
                    hm.put("P4:CS - i: " + i, result.get(i));

                }

                if (result.get(i).width == R1920x1080SMALL.SCORDITE_W1
                        && (result.get(i).height == R1920x1080SMALL.SCORDITE_H1 || result.get(i).height == R1920x1080SMALL.SCORDITE_H2)) {
                    hm.put("P3:S - i: " + i, result.get(i));

                }

                if ((result.get(i).width == R1920x1080SMALL.DENSE_W1 || result.get(i).width == R1920x1080SMALL.DENSE_W2)
                        && result.get(i).height == R1920x1080SMALL.DENSE_H1) {
                    hm.put("P2:DV - i: " + i, result.get(i));
                }

                if ((result.get(i).width == R1920x1080SMALL.CONCENTRATED_W1 || result.get(i).width == R1920x1080SMALL.CONCENTRATED_W2)
                        && result.get(i).height == R1920x1080SMALL.CONCENTRATED_H1
                        || (result.get(i).width == R1920x1080SMALL.CONCENTRATED_W2 || result.get(i).width == R1920x1080SMALL.CONCENTRATED_W3)
                        && result.get(i).height == R1920x1080SMALL.CONCENTRATED_H2) {
                    hm.put("P1:CV - i: " + i, result.get(i));
                }

                if (((result.get(i).width == R1920x1080SMALL.VELDSPAR_W1 || result.get(i).width == R1920x1080SMALL.VELDSPAR_W2)
                        && result.get(i).height == R1920x1080SMALL.VELDSPAR_H1)
                        || result.get(i).width == R1920x1080SMALL.VELDSPAR_W3 && result.get(i).height == R1920x1080SMALL.VELDSPAR_H2) {
                    hm.put("P0:V - i: " + i, result.get(i));
                }
            }
        }

        return hm;
    }

}
