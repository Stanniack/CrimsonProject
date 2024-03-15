package com.mycompany.crimsonproject.findpixels;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Devmachine
 */
public class FindPixels {

    private static final int APPR_PERCENT = 25;

    public boolean countWhitePixels(int row, int column, int width, int height) throws IOException {

        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\crimsonproject\\screenshots\\", "screenshot.png");
        BufferedImage bf = ImageIO.read(imageFile);

        Color color;
        int area = 0;

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                color = new Color(bf.getRGB(r, c));

                if (color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255) {
                    area++;
                }
            }
        }

        System.out.println("Total area: " + (width * height) + "\nFilled with white pixels area: " + area);
        System.out.println("White pixels: " + ((float) (area * 100) / (width * height)) + "%");

        if (area >= APPR_PERCENT) {
            return true;
        }

        return false;

    }
}
