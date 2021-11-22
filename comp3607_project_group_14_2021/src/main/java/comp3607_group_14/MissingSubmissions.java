package comp3607_group_14;

import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class MissingSubmissions {

    private String destination;
    private StudentDataCollection studentDataCollection;
    private Iterator iterator;
    private ArrayList<String> fileNames;
    private int numMissingSubmissions = 0;

    public MissingSubmissions(String destination, ArrayList<StudentData> studentData, ArrayList<String> fileNames) {
        this.destination = destination;
        this.studentDataCollection = new StudentDataCollection(studentData);
        this.fileNames = fileNames;
        this.iterator = studentDataCollection.createIterator();
    }

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