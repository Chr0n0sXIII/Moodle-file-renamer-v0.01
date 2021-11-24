package comp3607_group_14;

import java.io.File;
import java.util.ArrayList;

/**
 * This class provides functionality on folders
 */
public class FolderHandler {

    /**
     * This method returns the number of files in folder
     * 
     * @param location
     * @return int
     */
    public int numFilesInFolder(String location) {

        File directory = new File(location);

        if (directory.isDirectory()) {
            String[] files = directory.list();
            if (files.length > 0) {
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

    /**
     * This method creates the destination folder to be used
     * 
     * @param destination
     * @return boolean
     */
    public boolean createDestinationFolder(String destination) {

        File directory = new File(destination);

        if (directory.exists()) {
            System.out.println("Destination folder already exists.\n");
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

    /**
     * This method gets all the names of files in a folder
     * 
     * @param location
     * @return ArrayList
     */
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

    /**
     * This method gets the name of a CSV file in a folder
     * 
     * @param location
     * @return String
     */
    public String getCSVName(String location) {

        File filepath = new File(location);

        if (filepath.isDirectory()) {

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
        return "Invalid Directory";
    }

    /**
     * This method gets the name of a zipped file in a folder
     * 
     * @param location
     * @return String
     */
    public String getZippedFolderName(String location) {

        File filepath = new File(location);

        if (filepath.isDirectory()) {

            ArrayList<String> fileNames = getFileNames(location);
            ArrayList<String> ZippedFolders = new ArrayList<String>();

            for (String filename : fileNames) {
                if (filename.substring(filename.length() - 3).equals("zip"))
                    ZippedFolders.add(filename);
            }
            if (ZippedFolders.size() == 1 && numFilesInFolder(location) == 1)
                return ZippedFolders.get(0);

            if (ZippedFolders.size() == 1 && numFilesInFolder(location) > 1) {
                return "Multiple incompatible types of files";
            }

            else if (ZippedFolders.size() == 0)
                return "No zipped folders";

            else
                return "Multiple zipped folders";
        }
        return "Invalid Directory";

    }
}
