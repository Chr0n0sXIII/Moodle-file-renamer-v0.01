package comp3607_group_14;

import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * This class identifies students who did not submit an assignment
 * 
 * @author Kershawn Edwards
 * @author Deepak Ramsubhag
 */

public class MissingSubmissions {
    /**
     * This variable stores the file path of the renamed assignment files
     */

    private String destination;
    /**
     * This variable stores student information
     */

    private StudentDataCollection studentDataCollection;
    /**
     * This variable stores an object which is used to traverse the collection of
     * studentData
     */

    private Iterator iterator;
    /**
     * This variable stores the file names of files in a directory
     */

    private ArrayList<String> fileNames;
    /**
     * This variable stores the count of the number of students that did not submit
     * an assignment
     */

    private int numMissingSubmissions = 0;

    /**
     * This constructor instantiates a MissingSubmissions object
     */
    public MissingSubmissions(String destination, ArrayList<StudentData> studentData, ArrayList<String> fileNames) {
        this.destination = destination;
        this.studentDataCollection = new StudentDataCollection(studentData);
        this.fileNames = fileNames;
        this.iterator = studentDataCollection.createIterator();
    }

    /**
     * This function creates a csv string containing the student information of
     * students whose files have not been identified in the destination file path
     * 
     * @return String
     */
    private String findMissingFiles() {

        StringBuilder builder = new StringBuilder();
        boolean found = false;

        builder.append(
                "Identifier,Full name,ID number,Email address,Status,Grade,Maximum Grade,Grade can be changed,Last modified (grade),Feedback comments"
                        + "\n");

        while (iterator.hasNext()) {
            StudentData student = (StudentData) iterator.next();
            search: for (String filename : fileNames) {

                if (filename.contains(student.getFullName()) == true) {
                    found = true;
                    break search;
                }
            }

            if (found == false) {
                builder.append("Participant " + student.getIdentifier() + "," + student.getFullName() + ",");
                builder.append(student.getIDNumber() + "," + student.getEmail() + ",");
                builder.append(student.getStatus() + "," + student.getGrade() + ",");
                builder.append(student.getMaxGrade() + "," + student.getChangeGrade() + ",");
                builder.append(student.getDateLastModified() + "," + student.getFeedback() + "\n");
                numMissingSubmissions++;
            }
            found = false;
        }

        iterator.reset();
        return builder.toString();
    }

    /**
     * This function creates a csv file containing the information of students whose
     * assignments were not identified in the destination file path. It returns the
     * number of missing students
     * 
     * @return int
     */
    public int writeToCSV() {

        try {
            PrintWriter writer = new PrintWriter(destination + File.separator + "Missing Submissions.csv");
            writer.write(findMissingFiles());
            writer.close();
            return numMissingSubmissions;
        }

        catch (IOException e) {
            System.out.println(e);
            return 0;
        }
    }

}