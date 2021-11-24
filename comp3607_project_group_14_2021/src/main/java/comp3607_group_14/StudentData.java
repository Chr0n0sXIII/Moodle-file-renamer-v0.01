package comp3607_group_14;

/**
 * This class stores student information
 */
public class StudentData {
    /**
     * This variable stores the student's unique identifier
     */

    private String identifier;
    /**
     * This variable stores the student's full name
     */

    private String fullName;
    /**
     * This variale stores the student's ID number
     */

    private String IDNumber;
    /**
     * This variable stores the student's email address
     */

    private String email;
    /**
     * This variable stores the student status
     */

    private String status;
    /**
     * This variable stoes the student's minimum grade
     */

    private String grade;
    /**
     * This variable soes the student's maximum grade
     */

    private String maxGrade;
    /**
     * This variable stores the student's last modified grade
     */

    private String changeGrade;
    /**
     * This variabe stores the date that the student's grade was last modified
     */

    private String dateLastModified;
    /**
     * This variable stores the feedback given to the student
     */

    private String feedback;

    /**
     * This constructor creates a student data object and assigns values to all the
     * variables
     */
    public StudentData(String line) {
        setData(line);
    }

    /**
     * This function separates information from a string and assigns that to
     * variables in the student data object
     * 
     * @param line
     * @return boolean
     */
    private boolean setData(String line) {

        try {

            String[] lineData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            this.identifier = lineData[0].substring(12);
            this.fullName = lineData[1];
            this.IDNumber = lineData[2];
            this.email = lineData[3];
            this.status = lineData[4];
            this.grade = lineData[5];
            this.maxGrade = lineData[6];
            this.changeGrade = lineData[7];
            this.dateLastModified = lineData[8];

            if (!line.substring(line.length() - 1).equals(","))
                this.feedback = lineData[9];
            else
                this.feedback = "";

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This function returns the identifier variable
     * 
     * @return String
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * This function returns the fullname variable
     * 
     * @return String
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * This function returns the IDNumber variable
     * 
     * @return String
     */
    public String getIDNumber() {
        return IDNumber;
    }

    /**
     * This function returns the email variable
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * This function returns the status variable
     * 
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * This function returns the grade variable
     * 
     * @return String
     */
    public String getGrade() {
        return grade;
    }

    /**
     * This function returns the maxGrade variable
     * 
     * @return String
     */
    public String getMaxGrade() {
        return maxGrade;
    }

    /**
     * This function returns the changeGrade variable
     * 
     * @return String
     */
    public String getChangeGrade() {
        return changeGrade;
    }

    /**
     * This function returns the dateLastModified variable
     * 
     * @return String
     */
    public String getDateLastModified() {
        return dateLastModified;
    }

    /**
     * This function returns the feedback variable
     * 
     * @return String
     */
    public String getFeedback() {
        return feedback;
    }

}