package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.findpixels.FindPixels;
import java.io.File;
import java.io.IOException;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class CrimsonProject {

    public static void main(String[] args) {
        try {
            double start = System.currentTimeMillis();
            File imageFile = new File("C:\\Users\\Flavio\\Desktop", "StripMax.png");
            new FindPixels().findByRangeColor(imageFile, 1044 + 0, 912, 42, 46, new Triplet<>(118,167,129), new Triplet<>(146,174,161));
            //System.out.println("Area cannon 1: " + area);
            
            new FindPixels().findByRangeColor(imageFile, 1044 + 51, 912, 42, 46, new Triplet<>(118,167,129), new Triplet<>(146,174,161));
            //System.out.println("Area cannon 2: " + area);
            
            System.out.println((System.currentTimeMillis() - start)/1000 + " secs");
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
