package com.mycompany.crimsonproject.t4j;

import com.mycompany.crimsonproject.handlers.SleeperHandler;
import com.mycompany.crimsonproject.robot.TakeScreenshot;
import com.mycompany.crimsonproject.sort.RectComparatorByY;
import com.mycompany.crimsonproject.resolutions.R1920x1080Small;
import com.mycompany.crimsonproject.utils.RGBrange;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private final Tesseract tesseract;
    private BufferedImage bf;
    private File imageFile;
    private final RGBrange rgbr;
    private final R1920x1080Small fhd;
    private SleeperHandler sleeper;

    public SegmentedRegions() {
        rgbr = new RGBrange();
        fhd = new R1920x1080Small();
        sleeper = new SleeperHandler();
        tesseract = new Tesseract();
        tesseract.setDatapath(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\datatreiners\\");
        tesseract.setLanguage("eng");
        tesseract.setVariable("user_defined_dpi", "300");
    }

    /**
     * Retrieves a list of segmented regions from an image file based on
     * word-level segmentation.
     *
     * @return a list of {@code Rectangle} objects representing the bounding
     * boxes of segmented regions.
     * @throws IOException if there is an error reading the image file.
     * @throws TesseractException if an error occurs during the OCR process.
     */
    private List<Rectangle> getSegmentedFile() throws IOException, TesseractException {
        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        bf = ImageIO.read(imageFile);
        List<Rectangle> segmentedRegions = tesseract.getSegmentedRegions(bf, level);

        return segmentedRegions;
    }

    /**
     * Searches for a rectangle in the segmented regions that matches specific
     * dimensions and positional constraints, and performs OCR on the found
     * region.
     *
     * @param listOfWidthAndHeight a list of {@code Pair<Integer, Integer>}
     * objects representing the target width and height dimensions.
     * @param tupleBlockScreen a
     * {@code Quartet<Integer, Integer, Integer, Integer>} specifying the x and
     * y bounds for the rectangle's position.
     * @return a {@code Pair<Rectangle, String>} where the first element is the
     * matching {@code Rectangle} and the second element is the OCR result for
     * that region.
     * @throws IOException if an error occurs while reading the image file.
     * @throws TesseractException if an error occurs during the OCR process.
     * @throws AWTException if there is an issue with screen capture during
     * fallback operations.
     */
    public Pair<Rectangle, String> getOcrRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight, Quartet<Integer, Integer, Integer, Integer> tupleBlockScreen) throws IOException, TesseractException, AWTException {
        List<Rectangle> result;

        try {
            result = getSegmentedFile();
        } catch (NullPointerException ex) {
            Logger.getLogger(SegmentedRegions.class.getName()).log(Level.SEVERE, null, ex);
            sleeper.sleep(20000);
            new TakeScreenshot().take();
            result = getSegmentedFile();
        }

        for (Rectangle rect : result) {
            for (Pair<Integer, Integer> pair : listOfWidthAndHeight) {
                if ((rect.width == pair.getValue0() && rect.height == pair.getValue1())
                        && (rect.x >= tupleBlockScreen.getValue0() && rect.x <= tupleBlockScreen.getValue1())
                        && (rect.y >= tupleBlockScreen.getValue2() && rect.y <= tupleBlockScreen.getValue3())) {

                    BufferedImage subImage = bf.getSubimage(rect.x, rect.y, rect.width, rect.height); // Busca sub imagem do retângulo achado
                    String treatedRect = this.tesseract.doOCR(subImage); // busca OCR do retângulo achado

                    return new Pair(rect, treatedRect.trim()); // retorna o retângulo para ação com click e seu OCR tratado com trim()
                }
            }
        }
        return null;
    }

    /**
     * Searches for a rectangle in the segmented regions that matches specific
     * dimensions and positional constraints.
     *
     * @param listOfWidthAndHeight a list of {@code Pair<Integer, Integer>}
     * objects representing the target width and height dimensions.
     * @param tupleBlockScreen a
     * {@code Quartet<Integer, Integer, Integer, Integer>} specifying the x and
     * y bounds for the rectangle's position.
     * @return the matching {@code Rectangle} if found, or {@code null} if no
     * match exists.
     * @throws IOException if an error occurs while reading the image file.
     * @throws TesseractException if an error occurs during the OCR process.
     * @throws AWTException if there is an issue with screen capture during
     * fallback operations.
     */
    public Rectangle getRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight, Quartet<Integer, Integer, Integer, Integer> tupleBlockScreen) throws IOException, TesseractException, AWTException {
        List<Rectangle> result;

        try {
            result = getSegmentedFile();
        } catch (NullPointerException ex) {
            Logger.getLogger(SegmentedRegions.class.getName()).log(Level.SEVERE, null, ex);
            sleeper.sleep(20000);
            new TakeScreenshot().take();
            result = getSegmentedFile();
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
     * Searches for a rectangle in the segmented regions that matches specific
     * width and height dimensions.
     *
     * @param listOfWidthAndHeight a list of {@code Pair<Integer, Integer>}
     * objects representing the target width and height dimensions.
     * @return the matching {@code Rectangle} if found, or {@code null} if no
     * match exists.
     * @throws IOException if an error occurs while reading the image file.
     * @throws TesseractException if an error occurs during the OCR process.
     * @throws AWTException if there is an issue with screen capture during
     * fallback operations.
     */
    public Rectangle getRectangle(List<Pair<Integer, Integer>> listOfWidthAndHeight) throws IOException, TesseractException, AWTException {
        List<Rectangle> result;

        try {
            result = getSegmentedFile();
        } catch (NullPointerException ex) {
            Logger.getLogger(SegmentedRegions.class.getName()).log(Level.SEVERE, null, ex);
            sleeper.sleep(20000);
            new TakeScreenshot().take();
            result = getSegmentedFile();
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
     * @param x first x_axis coordinate on the screen
     * @param x2_w final x_axis coordinate where x and x2 are the scope for the
     * loop to pass
     * @param y is the first y_axis coordinate on the screen
     * @param y2_h is the final y_axis coordinate where y and y2 are the scope
     * for the loop to pass
     * @return return all asteroid found by priority: V0 is the lower priority
     * and Vn is the highest priority
     * @throws java.io.IOException
     * @throws net.sourceforge.tess4j.TesseractException
     */
    public HashMap<String, Rectangle> getAllOres(int x, int x2_w, int y, int y2_h) throws IOException, TesseractException {

        imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        bf = ImageIO.read(imageFile);

        /* First searching: Words */
        int level = TessPageIteratorLevel.RIL_WORD;

        List<Rectangle> result = tesseract.getSegmentedRegions(bf, level);

        /* Sort from lower to bigger Y coordinate */
        Collections.sort(result, new RectComparatorByY());

        HashMap<String, Rectangle> hm = new HashMap<>();

        for (int i = 0; i < result.size(); i++) {

            /* If into block screen list ores area */
            if ((result.get(i).x >= x && result.get(i).x <= x2_w)
                    && (result.get(i).y >= y && result.get(i).y <= y2_h)) {

                for (Pair<Integer, Integer> asteroid : fhd.getCondensedScorditeList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P0:CS - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : fhd.getScorditeList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P1:S - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : fhd.getDenseVeldsparList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P2:DV - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : fhd.getConcentratedVeldsparList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P3:CV - i: " + i, result.get(i));
                    }
                }

                for (Pair<Integer, Integer> asteroid : fhd.getVeldsparList()) {
                    if (result.get(i).width == asteroid.getValue0() && result.get(i).height == asteroid.getValue1()) {
                        hm.put("P4:V - i: " + i, result.get(i));
                    }
                }
            }
        }

        return hm;
    }
}
