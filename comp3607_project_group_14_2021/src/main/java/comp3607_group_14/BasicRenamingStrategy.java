package comp3607_group_14;

/**
 * This class is used to rename a filename if it is of a basic format
 */
public class BasicRenamingStrategy implements RenamingStrategy {

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
        String originalSubmissionName = getOriginalSubmissionName(fileName, student);
        newFileName += originalSubmissionName;

        return newFileName;
    }

    /**
     * This method returns the original submission name of a file of a basic format
     * 
     * @param fileName
     * @param student
     * @return String
     */
    private String getOriginalSubmissionName(String fileName, StudentData student) {

        String fileNameParts[] = fileName.split("_");
        String originalSubmission = "";
        int i;
        int arraySize = fileNameParts.length;

        for (i = 0; i < arraySize; i++) {
            if (fileNameParts[i].equals(student.getIdentifier())) {
                i++;
                while (i < arraySize) {
                    originalSubmission += fileNameParts[i];
                    i++;
                    if (i != arraySize)
                        originalSubmission += "_";
                }
                return originalSubmission;
            }
        }
        return "No submission name";
    }
}