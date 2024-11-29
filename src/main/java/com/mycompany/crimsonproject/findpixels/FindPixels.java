package com.mycompany.crimsonproject.findpixels;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class FindPixels {

    private final File imageFile;
    private BufferedImage bf;
    private Color color;

    public FindPixels() {
        imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
    }

    /**
     * Find a pixel greater/equal than beginRange and less/equal than endRange
     *
     * @param row first row to search range color
     * @param column first column to search range color
     * @param width last row to search range color (row + width)
     * @param height last column to search range color (column + height)
     * @param beginRange a triplet containing RGB
     * @param endRange triplet containing RGB
     * @return true if found a pixel according to input given
     * @throws IOException
     */
    public boolean findByRangeColor(int row, int column, int width, int height, Triplet<Integer, Integer, Integer> beginRange, Triplet<Integer, Integer, Integer> endRange) throws IOException {
        bf = ImageIO.read(imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if ((color.getRed() >= beginRange.getValue0() && color.getRed() <= endRange.getValue0())
                        && (color.getGreen() >= beginRange.getValue1() && color.getGreen() <= endRange.getValue1())
                        && (color.getBlue() >= beginRange.getValue2() && color.getBlue() <= endRange.getValue2())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Find a pixel equal than input RGB given
     *
     * @param row first row to search range color
     * @param column first column to search range color
     * @param width last row to search range color (row + width)
     * @param height last column to search range color (column + height)
     * @param red color equal red
     * @param green color equal green
     * @param blue color equal blue
     * @return true if found a pixel according to input given
     * @throws IOException BufferedRead exception
     */
    public boolean findByColor(int row, int column, int width, int height, int red, int green, int blue) throws IOException {
        bf = ImageIO.read(imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if (color.getRed() == red && color.getGreen() == green && color.getBlue() == blue) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Find a pixel greater/equal than input RGB given
     *
     * @param row first row to search range color
     * @param column first column to search range color
     * @param width last row to search range color (row + width)
     * @param height last column to search range color (column + height)
     * @param red color greater/equal than red
     * @param green color greater/equal than green
     * @param blue color greater/equal than blue
     * @return true if found a pixel according to input given
     * @throws IOException BufferedRead exception
     */
    public boolean greaterThan(int row, int column, int width, int height, int red, int green, int blue) throws IOException {
        bf = ImageIO.read(imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if (color.getRed() >= red && color.getGreen() >= green && color.getBlue() >= blue) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * Find a unique green pixel in a given multiarray scope
     *
     * @param row first row to search range color
     * @param column first column to search range color
     * @param width last row to search range color (row + width)
     * @param height last column to search range color (column + height)
     * @param red color less/equal than red
     * @param green color greater/equal than green
     * @param blue color less/equal than blue
     * @return true if found a pixel according to input given
     * @throws IOException BufferedRead exception
     */
    public boolean findByGreenColor(int row, int column, int width, int height, int red, int green, int blue) throws IOException {
        bf = ImageIO.read(imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if (color.getRed() <= red && color.getGreen() >= green && color.getBlue() <= blue) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Find a unique green pixel in a given multiarray scope
     *
     * @param imageFile image file given
     * @param row first row to search range color
     * @param column first column to search range color
     * @param width last row to search range color (row + width)
     * @param height last column to search range color (column + height)
     * @param red color less/equal than red
     * @param green color greater/equal than green
     * @param blue color less/equal than blue
     * @return true if found a pixel according to input given
     * @throws IOException BufferedRead exception
     */
    public boolean findByGreenColor(File imageFile, int row, int column, int width, int height, int red, int green, int blue) throws IOException {
        bf = ImageIO.read(imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if (color.getRed() <= red && color.getGreen() >= green && color.getBlue() <= blue) {
                    System.out.println(color.toString());
                    return true;
                }
            }
        }
        return false;
    }
}
