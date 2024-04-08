package findpixels.unit.tests;

import com.mycompany.crimsonproject.utils.PIXELRANGE;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.javatuples.Triplet;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Devmachine
 */
public class IsMinerCannonActionTest {

    private File imageFile;
    private BufferedImage bf;
    private Color color;

    @BeforeEach
    public void loadIMage() {
        try {
            this.imageFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\screenshots\\unit\\tests\\", "c1.png");
            this.bf = ImageIO.read(this.imageFile);
        } catch (IOException ex) {
            Logger.getLogger(IsMinerCannonActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void verifyMinerCannonAlphaPixels() {

        boolean isAlpha = false;
        int row = 1100, column = 925, width = 27, height = 12;
        Triplet<Integer, Integer, Integer> beginRange = new PIXELRANGE().tupleAlphaRGB;
        Triplet<Integer, Integer, Integer> endRange = new PIXELRANGE().tupleAlphaRGB;

        for (int r = row; r < (row + width); r++) {
            for (int c = column; c < (column + height); c++) {
                this.color = new Color(this.bf.getRGB(r, c));

                if ((this.color.getRed() >= beginRange.getValue0() && this.color.getRed() <= endRange.getValue0())
                        && (this.color.getGreen() >= beginRange.getValue1() && this.color.getGreen() <= endRange.getValue1())
                        && (this.color.getBlue() >= beginRange.getValue2() && this.color.getBlue() <= endRange.getValue2())) {

                    isAlpha = true;

                }
            }
        }

        Assert.assertTrue(isAlpha);
    }

}
