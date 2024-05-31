package com.mycompany.crimsonproject.t4j;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import com.mycompany.crimsonproject.sort.RectComparatorByY;
import com.mycompany.crimsonproject.utils.FULLHD;
import com.mycompany.crimsonproject.utils.PIXELRANGE;
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

    private final Tesseract instance;
    private BufferedImage bf;

    private File imageFile;

    public SegmentedRegions() {
        this.instance = new Tesseract();
        this.instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        this.instance.setLanguage("eng");
        this.instance.setVariable("user_defined_dpi", "300");
    }

    /**
     *
     * @param listOfWidthAndHeight list containing pair of tuples with the width
     * and height
     * @param tupleBlockScreen a tuple of 4 containing the right coordinates of
     * screen to search the rectangles
     * @return If there is a rectangle compatible with the input, it will return
     * rectangle or return NULL
     * @throws IOException
     * @throws TesseractException
     */
    public Rectangle getRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight, Quartet<Integer, Integer, Integer, Integer> tupleBlockScreen) throws IOException, TesseractException {
        List<Rectangle> result = null;
        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        try {
            this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
            this.bf = ImageIO.read(this.imageFile);
            this.instance.getSegmentedRegions(bf, level);
            result = this.instance.getSegmentedRegions(this.bf, level);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "test.png");
            this.bf = ImageIO.read(this.imageFile);
            this.instance.getSegmentedRegions(bf, level);
            result = this.instance.getSegmentedRegions(this.bf, level);
        }

        for (Rectangle rect : result) {
            for (Pair<Integer, Integer> pair : listOfWidthAndHeight) {
                if ((rect.width == pair.getValue0() && rect.height == pair.getValue1())
                        && (rect.x >= tupleBlockScreen.getValue0() && rect.x <= tupleBlockScreen.getValue1())
                        && (rect.y >= tupleBlockScreen.getValue2() && rect.y <= tupleBlockScreen.getValue3())) {

                    return rect;
                }
            }

        }
        return null;
    }

    /**
     *
     * @param listOfWidthAndHeight list containing pair of tuples with the width
     * and height
     * @return If there is a rectangle compatible with the input, it will return
     * rectangle or return NULL
     * @throws IOException
     * @throws TesseractException
     */
    public Rectangle getRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight) throws IOException, TesseractException {
        List<Rectangle> result = null;
        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        try {
            this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
            this.bf = ImageIO.read(this.imageFile);
            this.instance.getSegmentedRegions(bf, level);
            result = this.instance.getSegmentedRegions(this.bf, level);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "test.png");
            this.bf = ImageIO.read(this.imageFile);
            this.instance.getSegmentedRegions(bf, level);
            result = this.instance.getSegmentedRegions(this.bf, level);
        }

        for (Rectangle rect : result) {
            for (Pair<Integer, Integer> pair : listOfWidthAndHeight) {
                if ((rect.width == pair.getValue0() && rect.height == pair.getValue1())) {
                    return rect;
                }
            }

        }
        return null;
    }

    /**
     *
     * @param x is the first horizental coordinate on the screen
     * @param x2_w is the final horizental coordinate where x and x2 are the
     * scope for the loop to pass
     * @param y is the first vertical coordinate on the screen
     * @param y2_h is the final vertical coordinate where y and y2 are the scope
     * for the loop to pass
     * @return the method return all asteroid found by priority: V0 is the lower
     * priority and Vn is the highest priority
     * @throws java.io.IOException
     * @throws net.sourceforge.tess4j.TesseractException
     */
    public HashMap<String, Rectangle> getAllOres(int x, int x2_w, int y, int y2_h) throws IOException, TesseractException {

        this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        this.bf = ImageIO.read(this.imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(this.bf, level);

        /* Sort from lower to bigger Y coordinate */
        Collections.sort(result, new RectComparatorByY());

        HashMap<String, Rectangle> hm = new HashMap<>();

        for (int i = 0; i < result.size(); i++) {

            /* If into block screen list ores area */
            if ((result.get(i).x >= x && result.get(i).x <= x2_w)
                    && (result.get(i).y >= y && result.get(i).y <= y2_h)) {

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

    /**
     *
     * @param x is the first horizental coordinate on the screen
     * @param x2_w is the final horizental coordinate where x and x2 are the
     * scope for the loop to pass
     * @param y is the first vertical coordinate on the screen
     * @param y2_h is the final vertical coordinate where y and y2 are the scope
     * for the loop to pass
     * @return the method return all asteroid found by priority: V0 is the lower
     * priority and Vn is the highest priority
     * @throws java.io.IOException
     * @throws net.sourceforge.tess4j.TesseractException
     */
    public HashMap<String, Rectangle> getAllOres2(int x, int x2_w, int y, int y2_h) throws IOException, TesseractException {

        this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        this.bf = ImageIO.read(this.imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(this.bf, level);

        /* Sort from lower to bigger Y coordinate */
        Collections.sort(result, new RectComparatorByY());

        HashMap<String, Rectangle> hm = new HashMap<>();

        /* Margin of error to search tuple NPC/PLAYER */
        int moe = 2;

        for (int i = 0; i < result.size(); i++) {

            /* If into block screen list ores area */
            if ((result.get(i).x >= x && result.get(i).x <= x2_w)
                    && (result.get(i).y >= y && result.get(i).y <= y2_h)) {

                for (Pair<Integer, Integer> asteroid : new FULLHD().listCondensedScordite) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()
                            && !new FindPixels().findRangeColor(result.get(i).x, result.get(i).y, result.get(i).width + moe, result.get(i).height + moe, new PIXELRANGE().minTupleNpcPlayerRGB, new PIXELRANGE().maxTupleNpcPlayerRGB)) {
                        hm.put("P4:CS - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : new FULLHD().listScordite) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()
                            && !new FindPixels().findRangeColor(result.get(i).x, result.get(i).y, result.get(i).width + moe, result.get(i).height + moe, new PIXELRANGE().minTupleNpcPlayerRGB, new PIXELRANGE().maxTupleNpcPlayerRGB)) {
                        hm.put("P3:S - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : new FULLHD().listDenseVeldspar) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()
                            && !new FindPixels().findRangeColor(result.get(i).x, result.get(i).y, result.get(i).width + moe, result.get(i).height + moe, new PIXELRANGE().minTupleNpcPlayerRGB, new PIXELRANGE().maxTupleNpcPlayerRGB)) {
                        hm.put("P2:DV - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : new FULLHD().listConcentratedVeldspar) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()
                            && !new FindPixels().findRangeColor(result.get(i).x, result.get(i).y, result.get(i).width + moe, result.get(i).height + moe, new PIXELRANGE().minTupleNpcPlayerRGB, new PIXELRANGE().maxTupleNpcPlayerRGB)) {
                        hm.put("P1:CV - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : new FULLHD().listVeldspar) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()
                            && !new FindPixels().findRangeColor(result.get(i).x, result.get(i).y, result.get(i).width + moe, result.get(i).height + moe, new PIXELRANGE().minTupleNpcPlayerRGB, new PIXELRANGE().maxTupleNpcPlayerRGB)) {
                        hm.put("P0:V - i: " + i, result.get(i));
                    }
                }
            }
        }

        return hm;
    }

    /*public HashMap<String, Rectangle> getAllOres3(int x, int x2_w, int y, int y2_h) throws IOException, TesseractException {

        this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        this.bf = ImageIO.read(this.imageFile);

        // First searching: Words
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = this.instance.getSegmentedRegions(this.bf, level);

        // Sort from lower to bigger Y coordinate
        Collections.sort(result, new RectComparatorByY());

        HashMap<String, Rectangle> hm = new HashMap<>();

        for (int i = 0; i < result.size(); i++) {

            // If into block screen list ores area 
            if ((result.get(i).x >= x && result.get(i).x <= x2_w)
                    && (result.get(i).y >= y && result.get(i).y <= y2_h)) {

                for (Pair<Integer, Integer> asteroid : new FULLHD().listAsteroids) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1() && !new FindPixels().findRangeColor(result.get(i).x, result.get(i).y, result.get(i).width, result.get(i).height, new PIXELRANGE().minTupleNpcPlayerRGB, new PIXELRANGE().maxTupleNpcPlayerRGB)) {

                    }
                }

                if (((result.get(i).width == FULLHD.CONDENSED_W1 || result.get(i).width == FULLHD.CONDENSED_W2)
                        && result.get(i).height == FULLHD.CONDENSED_H1)
                        && !new FindPixels().findRangeColor(result.get(i).x, result.get(i).y, result.get(i).width, result.get(i).height, new PIXELRANGE().minTupleNpcPlayerRGB, new PIXELRANGE().maxTupleNpcPlayerRGB)) {
                    hm.put("P4:CS - i: " + i, result.get(i));  //

                }

            }
        }

        return hm; 
    } */
}
