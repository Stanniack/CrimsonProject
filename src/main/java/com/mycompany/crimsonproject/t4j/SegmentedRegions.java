package com.mycompany.crimsonproject.t4j;

import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.sort.RectComparatorByY;
import com.mycompany.crimsonproject.resolutions.R1920x1080;
import com.mycompany.crimsonproject.utils.RGBrange;
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

    private final Tesseract instance;
    private BufferedImage bf;
    private File imageFile;
    private final RGBrange rgbr;
    private final R1920x1080 fhd;

    public SegmentedRegions() {
        this.rgbr = new RGBrange();
        this.fhd = new R1920x1080();
        this.instance = new Tesseract();
        this.instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        this.instance.setLanguage("eng");
        this.instance.setVariable("user_defined_dpi", "300");
    }

    private List<Rectangle> getSegmentedFile() throws IOException, TesseractException {
        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        this.imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        this.bf = ImageIO.read(this.imageFile);
        this.instance.getSegmentedRegions(this.bf, level);

        return this.instance.getSegmentedRegions(this.bf, level);
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
     * @throws java.lang.InterruptedException
     * @throws java.awt.AWTException
     */
    public Rectangle getRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight, Quartet<Integer, Integer, Integer, Integer> tupleBlockScreen) throws IOException, TesseractException, InterruptedException, AWTException {
        List<Rectangle> result;

        try {
            result = this.getSegmentedFile();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Thread.sleep(20000);
            new TakeScreenshot().take();
            result = this.getSegmentedFile();
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
     * @throws java.lang.InterruptedException
     * @throws java.awt.AWTException
     */
    public Rectangle getRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight) throws IOException, TesseractException, InterruptedException, AWTException {
        List<Rectangle> result;

        try {
            result = this.getSegmentedFile();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Thread.sleep(20000);
            new TakeScreenshot().take();
            result = this.getSegmentedFile();
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

                for (Pair<Integer, Integer> asteroid : this.fhd.getCondensedScorditeList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P0:CS - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : this.fhd.getScorditeList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P1:S - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : this.fhd.getDenseVeldsparList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P2:DV - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : this.fhd.getConcentratedVeldsparList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P3:CV - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : this.fhd.getVeldsparList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P4:V - i: " + i, result.get(i));
                    }
                }
            }
        }

        return hm;
    }
}
