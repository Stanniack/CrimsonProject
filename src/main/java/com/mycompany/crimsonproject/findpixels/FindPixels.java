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
     * Checks if any pixel within the specified area falls within the given
     * color range.
     *
     * @param row the starting row of the area to search.
     * @param column the starting column of the area to search.
     * @param width the width of the area to search.
     * @param height the height of the area to search.
     * @param beginRange the beginning RGB range as a {@link Triplet} (red,
     * green, blue).
     * @param endRange the ending RGB range as a {@link Triplet} (red, green,
     * blue).
     * @return {@code true} if a pixel within the range is found, otherwise
     * {@code false}.
     * @throws IOException if an error occurs while reading the image file.
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
     * Checks if any pixel within the specified area matches the given RGB
     * color.
     *
     * @param row the starting row of the area to search.
     * @param column the starting column of the area to search.
     * @param width the width of the area to search.
     * @param height the height of the area to search.
     * @param red the red component of the target color.
     * @param green the green component of the target color.
     * @param blue the blue component of the target color.
     * @return {@code true} if a pixel with the exact color is found, otherwise
     * {@code false}.
     * @throws IOException if an error occurs while reading the image file.
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
     * Checks if any pixel within the specified area has RGB values greater than
     * or equal to the given values.
     *
     * @param row the starting row of the area to search.
     * @param column the starting column of the area to search.
     * @param width the width of the area to search.
     * @param height the height of the area to search.
     * @param red the minimum red value.
     * @param green the minimum green value.
     * @param blue the minimum blue value.
     * @return {@code true} if a pixel with RGB values greater than or equal to
     * the specified values is found, otherwise {@code false}.
     * @throws IOException if an error occurs while reading the image file.
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
     * Checks if any pixel within the specified area has a green value greater
     * than or equal to the specified value, while the red and blue values are
     * less than or equal to their respective specified values.
     *
     * @param row the starting row of the area to search.
     * @param column the starting column of the area to search.
     * @param width the width of the area to search.
     * @param height the height of the area to search.
     * @param red the maximum red value.
     * @param green the minimum green value.
     * @param blue the maximum blue value.
     * @return {@code true} if a pixel meeting the criteria is found, otherwise
     * {@code false}.
     * @throws IOException if an error occurs while reading the image file.
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
     * Checks if any pixel within the specified area of the provided image file
     * has a green value greater than or equal to the specified value, while the
     * red and blue values are less than or equal to their respective specified
     * values. Prints the color of the matching pixel if found.
     *
     * @param imageFile the image file to be processed.
     * @param row the starting row of the area to search.
     * @param column the starting column of the area to search.
     * @param width the width of the area to search.
     * @param height the height of the area to search.
     * @param red the maximum red value.
     * @param green the minimum green value.
     * @param blue the maximum blue value.
     * @return {@code true} if a pixel meeting the criteria is found, otherwise
     * {@code false}.
     * @throws IOException if an error occurs while reading the image file.
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
