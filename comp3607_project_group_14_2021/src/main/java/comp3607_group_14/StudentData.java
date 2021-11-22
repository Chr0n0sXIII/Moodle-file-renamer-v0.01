package comp3607_group_14;

public class StudentData {

    private String identifier;
    private String fullName;
    private String IDNumber;
    private String email;
    private String status;
    private String grade;
    private String maxGrade;
    private String changeGrade;
    private String dateLastModified;
    private String feedback;

    public StudentData(String line) {
        setData(line);
    }

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

    public String getIdentifier() {
        return identifier;
    }

    public String getFullName() {
        return fullName;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getGrade() {
        return grade;
    }

    public String getMaxGrade() {
        return maxGrade;
    }

    public String getChangeGrade() {
        return changeGrade;
    }

    public String getDateLastModified() {
        return dateLastModified;
    }

    public String getFeedback() {
        return feedback;
    }

}