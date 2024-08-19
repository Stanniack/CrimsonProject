package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Devmachine
 */
public class CrimsonProject {

    public static void main(String[] args) {
        try {
            double start = System.currentTimeMillis();
            File imageFile = new File("C:\\Users\\Flavio\\Desktop", "e11.png");
            new FindPixels().findByGreenColor(imageFile, 1044 + 0, 912, 42, 46, 100, 125, 100);
            //System.out.println("Area cannon 1: " + area);

            new FindPixels().findByGreenColor(imageFile, 1044 + 51, 912, 42, 46, 100, 125, 100);
            //System.out.println("Area cannon 2: " + area);

            System.out.println((System.currentTimeMillis() - start) / 1000 + " secs");
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
