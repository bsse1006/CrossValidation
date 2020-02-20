package othersPackage;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Randomizer
{
    private List<File> mainPoints;
    private List<File> mainTrainingPoints = new ArrayList<File>();
    private List<File> mainTestPoints = new ArrayList<File>();
    private List<File> maskPoints;
    private List<File> maskTrainingPoints = new ArrayList<File>();
    private List<File> maskTestPoints = new ArrayList<File>();

    public Randomizer(List<File> mainPoints, List<File> maskPoints) {
        this.mainPoints = mainPoints;
        this.maskPoints = maskPoints;
        getMainTestAndTrainingData();
        getMaskTestAndTrainingData();
    }

    public void getMaskTestAndTrainingData () {
        for(File mainFile: mainTestPoints)
        {
            for(File maskFile: maskPoints)
            {
                if(mainFile.getName().substring(0,4).equals(maskFile.getName().substring(0,4)))
                {
                    maskTestPoints.add(maskFile);
                    break;
                }
            }
        }
        maskTrainingPoints = new ArrayList<>(maskPoints);

        maskTrainingPoints.removeAll(maskTestPoints);
    }

    public void getMainTestAndTrainingData () {

        for(int i=0; i<mainPoints.size()/10; i++)
        {
            Random rand = new Random();
            int temp = rand.nextInt(mainPoints.size());
            if(mainPoints.get(temp)==null)
            {
                i--;
                continue;
            }
            mainTestPoints.add(mainPoints.get(temp));
            mainPoints.set(temp,null);
        }

        for(int i=0; i<mainPoints.size(); i++)
        {
            if(mainPoints.get(i)==null)
                continue;

            mainTrainingPoints.add(mainPoints.get(i));
        }
    }

    public List<File> getMainTrainingPoints() {
        return mainTrainingPoints;
    }

    public List<File> getMainTestPoints() {
        return mainTestPoints;
    }

    public List<File> getMaskTrainingPoints() {
        return maskTrainingPoints;
    }

    public List<File> getMaskTestPoints() {
        return maskTestPoints;
    }
}
