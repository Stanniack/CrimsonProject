package com.mycompany.crimsonproject.t4j;

import com.mycompany.crimsonproject.sort.RectComparatorByY;
import com.mycompany.crimsonproject.utils.FULLHD;
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
import org.javatuples.Pair;
import org.javatuples.Quartet;

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
        this.instance.setVariable("user_defined_dpi", "300");
        this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
    }

    public Rectangle getT_WxH_BlockScreen(int width1, int height1, int x, int x2, int y, int y2) throws IOException, TesseractException {

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

    public Rectangle getRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight, Quartet<Integer, Integer, Integer, Integer> blockscreen) throws IOException, TesseractException {
        this.bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(bf, level);

        for (Rectangle rect : result) {
            for (Pair<Integer, Integer> pair : listOfWidthAndHeight) {
                if ((rect.width == pair.getValue0() && rect.height == pair.getValue1())
                        && (rect.x >= blockscreen.getValue0() && rect.x <= blockscreen.getValue1())
                        && (rect.y >= blockscreen.getValue2() && rect.y <= blockscreen.getValue3())) {

                    return rect;
                }
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

                if ((result.get(i).width == FULLHD.CONDENSED_W1 || result.get(i).width == FULLHD.CONDENSED_W2)
                        && result.get(i).height == FULLHD.CONDENSED_H1) {
                    hm.put("P4:CS - i: " + i, result.get(i));

                }

                if (result.get(i).width == FULLHD.SCORDITE_W1
                        && (result.get(i).height == FULLHD.SCORDITE_H1 || result.get(i).height == FULLHD.SCORDITE_H2)) {
                    hm.put("P3:S - i: " + i, result.get(i));

                }

                if ((result.get(i).width == FULLHD.DENSE_W1 || result.get(i).width == FULLHD.DENSE_W2)
                        && result.get(i).height == FULLHD.DENSE_H1) {
                    hm.put("P2:DV - i: " + i, result.get(i));
                }

                if ((result.get(i).width == FULLHD.CONCENTRATED_W1 || result.get(i).width == FULLHD.CONCENTRATED_W2)
                        && result.get(i).height == FULLHD.CONCENTRATED_H1
                        || (result.get(i).width == FULLHD.CONCENTRATED_W2 || result.get(i).width == FULLHD.CONCENTRATED_W3)
                        && result.get(i).height == FULLHD.CONCENTRATED_H2) {
                    hm.put("P1:CV - i: " + i, result.get(i));
                }

                if (((result.get(i).width == FULLHD.VELDSPAR_W1 || result.get(i).width == FULLHD.VELDSPAR_W2)
                        && result.get(i).height == FULLHD.VELDSPAR_H1)
                        || result.get(i).width == FULLHD.VELDSPAR_W3 && result.get(i).height == FULLHD.VELDSPAR_H2) {
                    hm.put("P0:V - i: " + i, result.get(i));
                }
            }
        }

        return hm;
    }

}
