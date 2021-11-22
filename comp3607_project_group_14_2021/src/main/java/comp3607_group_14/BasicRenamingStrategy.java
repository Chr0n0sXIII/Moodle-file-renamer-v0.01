package comp3607_group_14;

public class BasicRenamingStrategy implements RenamingStrategy {

    public String renameFile(String fileName, StudentData student) {
        String newFileName = "";
        newFileName += student.getFullName() + "_";
        newFileName += student.getIdentifier() + "_";
        newFileName += "assignsubmission_file_";
        String originalSubmissionName = getOriginalSubmissionName(fileName, student);
        newFileName += originalSubmissionName;

        return newFileName;
    }

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