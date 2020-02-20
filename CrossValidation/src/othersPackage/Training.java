package othersPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Training
{
    private File realFile;
    private File maskFile;

    double [] [] [] skin;
    double [] [] [] nonSkin;

    public double[][][] getSkin() {
        return skin;
    }

    public double[][][] getNonSkin() {
        return nonSkin;
    }

    private void marchThroughImage(BufferedImage realImage, BufferedImage maskImage) {
        int w = realImage.getWidth();
        int h = realImage.getHeight();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = maskImage.getRGB(j, i);
                Color c = new Color(realImage.getRGB(j, i));
                if(pixel == -1)
                {
                    nonSkin[c.getRed()][c.getGreen()][c.getBlue()]++;
                }
                else
                {
                    skin[c.getRed()][c.getGreen()][c.getBlue()]++;
                }
            }
        }
    }

    public Training(File realFile, File maskFile, double[][][] skin, double[][][] nonSkin) {
        this.realFile = realFile;
        this.maskFile = maskFile;
        this.skin = skin;
        this.nonSkin = nonSkin;

        try {
            BufferedImage realImage, maskImage;
            realImage = ImageIO.read(realFile);
            maskImage = ImageIO.read(maskFile);
            marchThroughImage(realImage,maskImage);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
