package comp3607_group_14;

import java.io.File;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileFixer {
    public static void main(String[] args) throws Exception {

        Path currentActiveDirectory = Paths.get("").toAbsolutePath();

        String source = currentActiveDirectory.normalize().toString();

        source += File.separator + "filesToRename";

        String destination = source + File.separator + "renamedFiles";

        System.out.println(currentActiveDirectory.getFileName());

        FolderHandler folderHandler = new FolderHandler();

        if (folderHandler.numFilesInFolder(source) <= 0) {
            System.out.println("No files found. Exiting . . .\n");
            return;
        }

        else {

            String zipFoldername = folderHandler.getZippedFolderName(source);

            System.out.println(zipFoldername + " found.\n");

            if (zipFoldername.equals("Multiple incompatible types of files")
                    || zipFoldername.equals("Multiple zipped folders") || zipFoldername.equals("Invalid Directory")) {
                System.out.println("Exiting. . .\n");
                return;
            }

            if (!zipFoldername.equals("No zipped folders")) {
                String zippedFolderPath = source + File.separator + zipFoldername;
                UnzipFiles unzipper = new UnzipFiles();

                if (!unzipper.unzip(zippedFolderPath, source)) {
                    System.out.println("Error unzipping\nExiting. . .\n");
                    return;
                }

                zipFoldername = zipFoldername.substring(0, zipFoldername.length() - 4);

                source += File.separator + zipFoldername;
            }

            String CSVName = folderHandler.getCSVName(source);
            System.out.println(CSVName + " found.");

            if (CSVName.equals("No CSVs") || CSVName.equals("Multiple CSVs") || CSVName.equals("Invalid Directory")) {
                System.out.println("\nExiting. . .\n");
                return;
            }

            CSVReader csvReader = new CSVReader(source, CSVName);

            if (!csvReader.readCSV()) {
                System.out.println("Exiting. . .\n");
                return;
            }

            if (!folderHandler.createDestinationFolder(destination)) {
                System.out.println("Exiting. . .\n");
                return;
            }

            FileCopier fileCopier = new FileCopier(source, destination);

            fileCopier.copyFiles();

            int numFilesCopied = folderHandler.numFilesInFolder(destination);
            System.out.println(numFilesCopied + " pdf files copied to " + destination + "\n");

            ArrayList<String> originalFileNames = folderHandler.getFileNames(destination);
            FileRenamer fileRenamer = new FileRenamer(destination, csvReader.getStudentData(), originalFileNames);
            int numFilesRenamed = fileRenamer.renameFiles();
            int numFlaggedFiles = fileRenamer.getNumFlaggedFiles();
            System.out.println(numFilesRenamed + " files renamed.\n");
            System.out.println(numFlaggedFiles + " flagged files.\n");

            ArrayList<String> renamedFileNames = folderHandler.getFileNames(destination);
            MissingSubmissions ms = new MissingSubmissions(destination, csvReader.getStudentData(), renamedFileNames);
            int numMissingSubmissions = ms.writeToCSV();
            System.out.println("Number of missing submissions: " + numMissingSubmissions);
        }
        return;
    }
}
