/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.crimsonproject;

import com.mycompany.crimsonproject.scripts.CheckCargoDeposit3;
import com.mycompany.crimsonproject.scripts.ExtractOre;
import com.mycompany.crimsonproject.scripts.ExtractOre2;
import com.mycompany.crimsonproject.scripts.ExtractOre3;
import com.mycompany.crimsonproject.scripts.GetDestinationMiner;
import com.mycompany.crimsonproject.scripts.GetDestinationMiner2;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Mateus
 */
public class CrimsonProject {

    public static void main(String[] args) {
        try {
            Thread.sleep(4000);
            //new CheckCargoDeposit3().check();
            //Thread.sleep(15000);
            //new GetDestinationMiner2().getDestination();
            new ExtractOre3().extract();
        } catch (InterruptedException | IOException | AWTException | TesseractException ex) {
            Logger.getLogger(CrimsonProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
