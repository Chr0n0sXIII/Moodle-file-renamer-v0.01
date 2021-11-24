package comp3607_group_14;

/**
 * This class is used to rename a filename if it is of a hard format
 */
public class HardRenamingStrategy implements RenamingStrategy {

    /**
     * This method returns the new name of a file given a filename its corresponding
     * student
     * 
     * @param fileName
     * @param student
     * @return String
     */
    public String renameFile(String fileName, StudentData student) {
        String newFileName = "";
        newFileName += student.getFullName() + "_";
        newFileName += student.getIdentifier() + "_";
        newFileName += "assignsubmission_file_";
        String originalSubmissionName = fileName;
        newFileName += originalSubmissionName;

        return newFileName;
    }

}
