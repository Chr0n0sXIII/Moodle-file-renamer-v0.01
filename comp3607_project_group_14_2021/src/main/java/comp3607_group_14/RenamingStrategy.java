package comp3607_group_14;

/**
 * This interface provides what methods concrete RenamingStrategies should have
 */
public interface RenamingStrategy {

    /**
     * This function returns the new name of files according to the defined naming
     * convention
     */
    public String renameFile(String fileName, StudentData student);

}
