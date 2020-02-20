package othersPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MachineLearning
{
    private Randomizer randomizer;

    double [] [] [] skin = new double [256] [256] [256];
    double [] [] [] nonSkin = new double [256] [256] [256];
    private double [] [] [] trainedData;

    public MachineLearning(Randomizer randomizer) {
        this.randomizer = randomizer;
        trainFiles();
    }

    public double[][][] getTrainedData() {
        return trainedData;
    }

    public void trainFiles ()
    {
        for(File realFile: randomizer.getMainTrainingPoints())
        {
            for (File maskFile: randomizer.getMaskTrainingPoints())
            {
                if(realFile.getName().substring(0,4).equals(maskFile.getName().substring(0,4)))
                {
                    Training training = new Training(realFile,maskFile,skin,nonSkin);
                    skin = training.getSkin();
                    nonSkin = training.getNonSkin();
                }
            }
        }

        ImageProcessor imageProcessor = new ImageProcessor(skin,nonSkin);

        trainedData = imageProcessor.getTrainedData();
    }
}
