package comp3607_group_14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for reading all the data of a CSV file and storing
 * that information
 */
public class CSVReader {

    /**
     * This variable stores the file path of the renamed assignment files
     */
    private String source;

    /**
     * This variable stores the name of a CSV file
     */
    private String CSVName;

    /**
     * This variable stores an arraylist of studentData
     */
    private ArrayList<StudentData> studentData;

    /**
     * This constructor initiates a CSVReader object
     */
    public CSVReader(String source, String CSVName) {
        this.source = source;
        this.CSVName = CSVName;
        studentData = new ArrayList<StudentData>();
    }

    /**
     * This method stores all the information on students in an StudentData
     * Arraylist
     * 
     * @return boolean
     */
    public boolean readCSV() {
        String path = source + File.separator + CSVName;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = "";
            line = br.readLine();

            while ((line = br.readLine()) != null) {

                StudentData sd = new StudentData(line);
                studentData.add(sd);
            }

            br.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method returns an arraylist with all the student's data
     * 
     * @return ArrayList
     */
    public ArrayList<StudentData> getStudentData() {
        return studentData;
    }
}