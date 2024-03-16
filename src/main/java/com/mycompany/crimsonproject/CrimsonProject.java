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
            new FindPixels().findRangeColor(1095, 910, 42, 40, new Triplet<>(107, 107, 106), new Triplet<>(110, 110, 109));
            System.out.println((System.currentTimeMillis() - start)/100 + " secs");
        } catch (IOException ex) {
        }
    }
}
