import java.io.File;
import java.util.ArrayList;

public class FolderHandler {

    private String source;
    private String destination;

    public FolderHandler(String source, String destination) {
        this.destination = destination;
        this.source = source;
    }

    public int numFilesInFolder(String location) {

        File directory = new File(location);

        if (directory.isDirectory()) {
            String[] files = directory.list();
            if (files.length > 0) {
                System.out.println(location + " is not empty.\n");
                return files.length;
            } else {
                System.out.println(location + " is empty.\n");
                return 0;
            }
        } else {
            System.out.println(location + " is not a valid directory.\n");
            return -1;
        }
    }

    public boolean createDestinationFolder() {

        File directory = new File(destination);

        if (directory.exists()) {
            System.out.println("Destination folder already exists.\n");

            if (numFilesInFolder(destination) == 0)
                return true;

            else
                System.out.println("Destination folder \n");
            return false;
        }

        else {
            boolean flag = directory.mkdir();

            if (flag) {
                System.out.println("Destination folder successfully created.\n");
                return true;
            }

            else
                return false;

        }
    }

    public ArrayList<String> getFileNames(String location) {

        ArrayList<String> fileNames = new ArrayList<String>();

        try {
            File[] files = new File(location).listFiles();

            for (File file : files) {

                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }

        catch (Exception e) {
            System.out.println("Error getting files from " + location);
        }

        return fileNames;
    }

    public String getCSVName(String location) {

        ArrayList<String> fileNames = getFileNames(location);
        ArrayList<String> CSVs = new ArrayList<String>();

        for (String filename : fileNames) {
            if (filename.substring(filename.length() - 3).equals("csv"))
                CSVs.add(filename);
        }
        if (CSVs.size() == 1)
            return CSVs.get(0);

        else if (CSVs.size() == 0)
            return "No CSVs";

        else
            return "Multiple CSVs";
    }

    public String getZippedFolderName() {

        ArrayList<String> fileNames = getFileNames(source);
        ArrayList<String> ZippedFolders = new ArrayList<String>();

        for (String filename : fileNames) {
            if (filename.substring(filename.length() - 3).equals("zip"))
                ZippedFolders.add(filename);
        }
        if (ZippedFolders.size() == 1 && numFilesInFolder(source) == 1)
            return ZippedFolders.get(0);

        if (ZippedFolders.size() == 1 && numFilesInFolder(source) > 1) {
            return "Multiple incompatible types of files";
        }

        else if (ZippedFolders.size() == 0)
            return "No zipped folders";

        else
            return "Multiple zipped folders";
    }

}
