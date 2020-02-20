package othersPackage;

import java.io.File;
import java.util.List;

public class InputProcessing
{
    List<File> listOfMainFiles;
    List<File> listOfMaskFiles;
    String mainFolderPath;
    String maskFolderPath;

    Randomizer randomizer;

    public InputProcessing(String mainFolderPath, String maskFolderPath) {
        this.mainFolderPath = mainFolderPath;
        this.maskFolderPath = maskFolderPath;
        processInput();
    }

    public void processInput ()
    {
        listOfMainFiles = FolderToFiles.getListOfFiles(mainFolderPath);
        listOfMaskFiles = FolderToFiles.getListOfFiles(maskFolderPath);

        randomizer = new Randomizer(listOfMainFiles,listOfMaskFiles);
    }

    public Randomizer getRandomizer() {
        return randomizer;
    }
}
