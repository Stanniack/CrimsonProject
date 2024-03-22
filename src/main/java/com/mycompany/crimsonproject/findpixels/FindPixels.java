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

    private final File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
    private BufferedImage bf;
    private Color color;

    private static final int APPR_PERCENT = 25;

    public boolean countWhitePixels(int row, int column, int width, int height) throws IOException {

        this.bf = ImageIO.read(this.imageFile);
        int area = 0;

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                this.color = new Color(this.bf.getRGB(r, c));

                if (this.color.getRed() == 255 && this.color.getGreen() == 255 && this.color.getBlue() == 255) {
                    area++;
                }
            }
        }

        System.out.println("Total area: " + (width * height) + "\nFilled with white pixels area: " + area);
        System.out.println("White pixels: " + ((float) (area * 100) / (width * height)) + "%");

        return area >= APPR_PERCENT;
    }

    public int pixelContainsColorByRange(int row, int column, int width, int height, Triplet<Integer, Integer, Integer> beginRange, Triplet<Integer, Integer, Integer> endRange) throws IOException {

        this.bf = ImageIO.read(this.imageFile);
        int area = 0;

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                this.color = new Color(this.bf.getRGB(r, c));

                if ((this.color.getRed() >= beginRange.getValue0() && this.color.getRed() <= endRange.getValue0())
                        && (this.color.getGreen() >= beginRange.getValue1() && this.color.getGreen() <= endRange.getValue1())
                        && (this.color.getBlue() >= beginRange.getValue2() && this.color.getBlue() <= endRange.getValue2())) {

                    area++;

                }
            }
        }

        return area;
    }

    public boolean findRangeColor(int row, int column, int width, int height, Triplet<Integer, Integer, Integer> beginRange, Triplet<Integer, Integer, Integer> endRange) throws IOException {

        this.bf = ImageIO.read(this.imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                this.color = new Color(this.bf.getRGB(r, c));

                if ((this.color.getRed() >= beginRange.getValue0() && this.color.getRed() <= endRange.getValue0())
                        && (this.color.getGreen() >= beginRange.getValue1() && this.color.getGreen() <= endRange.getValue1())
                        && (this.color.getBlue() >= beginRange.getValue2() && this.color.getBlue() <= endRange.getValue2())) {

                    return true;

                }
            }
        }

        return false;
    }

    public boolean findByColor(int row, int column, int width, int height, Triplet<Integer, Integer, Integer> RGBcolor) throws IOException {

        this.bf = ImageIO.read(this.imageFile);

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                this.color = new Color(this.bf.getRGB(r, c));

                if (this.color.getRed() == RGBcolor.getValue0() && this.color.getGreen() == RGBcolor.getValue1() && this.color.getBlue() == RGBcolor.getValue2()) {
                    return true;
                }
            }
        }

        return false;
    }

}
