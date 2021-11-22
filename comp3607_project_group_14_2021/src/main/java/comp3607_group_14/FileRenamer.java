package comp3607_group_14;

import java.io.File;
import java.util.ArrayList;

public class FileRenamer {

    private String destination;
    private StudentDataCollection studentDataCollection;
    private Iterator iterator;
    private ArrayList<String> fileNames;
    private RenamingStrategy strategy;
    private FileNameHandler fileNameHandler;
    private int numFlaggedFiles = 0;

    public FileRenamer(String destination, ArrayList<StudentData> studentData, ArrayList<String> fileNames) {
        studentDataCollection = new StudentDataCollection(studentData);
        this.destination = destination;
        this.fileNames = fileNames;
        iterator = studentDataCollection.createIterator();
        fileNameHandler = new FileNameHandler();
    }

    public int renameFiles() {

        int count = 0;
        numFlaggedFiles = 0;

        try {
            for (String originalFileName : fileNames) {

                boolean renamed = false;

                File fileToRename = new File(destination + File.separator + originalFileName);

                while (iterator.hasNext()) {

                    StudentData student = (StudentData) iterator.next();

                    String identifer = student.getIdentifier();
                    String IDnumber = student.getIDNumber();

                    boolean containsName = fileNameHandler.checkFileForFullName(originalFileName, student);

                    if (containsName || originalFileName.contains(identifer) || originalFileName.contains(IDnumber)) {

                        if (!fileNameHandler.needsNameChange(originalFileName, student)) {
                            System.out.println(originalFileName + " does not need renaming.\n");
                            iterator.reset();
                            renamed = true;
                            break;
                        }

                        String fileNameType = fileNameHandler.verifyFileNameType(originalFileName, student);
                        String newFileName = getNewFileName(originalFileName, student, fileNameType);
                        File renameFile = new File(destination + File.separator + newFileName);
                        boolean flag = fileToRename.renameTo(renameFile);

                        if (flag == false)
                            renamed = false;

                        else {
                            System.out.println(originalFileName + " Renamed to: " + newFileName + "\n");
                            renamed = true;
                            count++;
                        }
                        iterator.reset();
                        break;
                    }
                }
                if (!renamed) {
                    System.out.println("Error renaming " + originalFileName + "\n");
                    numFlaggedFiles++;
                }

                iterator.reset();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error renaming files.");
        }
        return count;
    }

    private void setStrategy(RenamingStrategy strategy) {
        this.strategy = strategy;
    }

    private String getNewFileName(String fileName, StudentData student, String fileNameType) {

        String newFileName = "";

        if (fileNameType.equals("basic"))
            setStrategy(new BasicRenamingStrategy());

        if (fileNameType.equals("hard"))
            setStrategy(new HardRenamingStrategy());

        newFileName = strategy.renameFile(fileName, student);

        return newFileName;
    }

    public int getNumFlaggedFiles() {
        return numFlaggedFiles;
    }
}
