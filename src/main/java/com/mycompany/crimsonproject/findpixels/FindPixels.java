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

    public int pixelContainsColorByRange(File imageFile, int row, int column, int width, int height, Triplet<Integer, Integer, Integer> beginRange, Triplet<Integer, Integer, Integer> endRange) throws IOException {
        bf = ImageIO.read(imageFile);
        int area = 0;

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if ((color.getRed() >= beginRange.getValue0() && color.getRed() <= endRange.getValue0())
                        && (color.getGreen() >= beginRange.getValue1() && color.getGreen() <= endRange.getValue1())
                        && (color.getBlue() >= beginRange.getValue2() && color.getBlue() <= endRange.getValue2())) {
                    area++;
                }
            }
        }
        return area;
    }

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

    public boolean findByRangeColor(File imageFile, int row, int column, int width, int height, Triplet<Integer, Integer, Integer> beginRange, Triplet<Integer, Integer, Integer> endRange) throws IOException {
        bf = ImageIO.read(imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if ((color.getRed() >= beginRange.getValue0() && color.getRed() <= endRange.getValue0())
                        && (color.getGreen() >= beginRange.getValue1() && color.getGreen() <= endRange.getValue1())
                        && (color.getBlue() >= beginRange.getValue2() && color.getBlue() <= endRange.getValue2())) {

                    System.out.println(color.toString());
                    return true;
                }
            }
        }
        return false;
    }

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
