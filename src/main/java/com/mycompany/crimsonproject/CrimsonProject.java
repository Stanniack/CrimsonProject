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
            new FindPixels().findRangeColor(1044, 910, 42, 40, new Triplet<>(79, 110, 67), new Triplet<>(90, 113, 70));
        } catch (IOException ex) {
        }
    }
}
