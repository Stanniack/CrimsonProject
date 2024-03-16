package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.findpixels.FindPixels;
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
            int area = new FindPixels().pixelContainsColorByRange(1044, 910, 42, 40, new Triplet<>(80, 86, 67), new Triplet<>(90, 113, 70));
            System.out.println("Area cannon 1: " + area);
            
            area = new FindPixels().pixelContainsColorByRange(1095, 910, 42, 40, new Triplet<>(80, 86, 67), new Triplet<>(90, 113, 70));
            System.out.println("Area cannon 2: " + area);
            
            System.out.println((System.currentTimeMillis() - start)/100 + " secs");
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
