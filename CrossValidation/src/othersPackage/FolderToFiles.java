package othersPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderToFiles
{
    public static List<File> getListOfFiles(String folderPath)
    {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        return Arrays.asList(listOfFiles);
    }
}