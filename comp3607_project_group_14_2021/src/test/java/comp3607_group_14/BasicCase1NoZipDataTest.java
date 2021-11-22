package comp3607_group_14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class BasicCase1NoZipDataTest {

    private String source;
    private String destination;
    private FolderHandler folderHandler;

    @BeforeEach
    public void initialize() {
        Path currentActiveDirectory = Paths.get("").toAbsolutePath();
        source = currentActiveDirectory.normalize().toString();
        source += File.separator + "testingData" + File.separator + "basic no zip case_i" + File.separator
                + "filesToRename";
        destination = source + File.separator + "renamedFiles";

        folderHandler = new FolderHandler();
    }

    @Test
    @Order(1)
    public void testNumFilesInDestinationFolder() {

        boolean greaterThanZero = false;

        if (folderHandler.numFilesInFolder(source) > 0)
            greaterThanZero = true;

        assertTrue(greaterThanZero);
    }

    @Test
    @Order(2)
    public void testUnzippingFolder() {

        String zipFolderName = folderHandler.getZippedFolderName(source);
        assertEquals("No zipped folders", zipFolderName);

    }

    @Test
    @Order(3)
    public void testReadingCSV() {

        String CSVName = folderHandler.getCSVName(source);

        assertEquals("Sample 3 CSV.csv", CSVName);

        CSVReader csvReader = new CSVReader(source, CSVName);

        assertTrue(csvReader.readCSV());
    }

    @Test
    @Order(4)
    public void testCreateDestinationFolder() {

        assertTrue(folderHandler.createDestinationFolder(destination));
    }

    @Test
    @Order(5)
    public void testFileCopier() throws IOException {

        FileCopier fileCopier = new FileCopier(source, destination);

        fileCopier.copyFiles();

        int numFiles = folderHandler.numFilesInFolder(destination);

        assertEquals(49, numFiles);
    }

    @Test
    @Order(6)
    public void testFileRenamer() {

        CSVReader csvReader = new CSVReader(source, "Sample 3 CSV.csv");
        csvReader.readCSV();
        ArrayList<String> originalFileNames = folderHandler.getFileNames(destination);

        FileRenamer fileRenamer = new FileRenamer(destination, csvReader.getStudentData(), originalFileNames);

        int numRenamedFiles = fileRenamer.renameFiles();

        assertEquals(49, numRenamedFiles);

        int numFlaggedFiles = fileRenamer.getNumFlaggedFiles();

        assertEquals(0, numFlaggedFiles);
    }

    @Test
    @Order(7)
    public void testMissingSubmissions() {

        CSVReader csvReader = new CSVReader(source, "Sample 3 CSV.csv");
        csvReader.readCSV();
        ArrayList<String> renamedFileNames = folderHandler.getFileNames(destination);

        MissingSubmissions ms = new MissingSubmissions(destination, csvReader.getStudentData(), renamedFileNames);

        assertEquals(0, ms.writeToCSV());
    }

}