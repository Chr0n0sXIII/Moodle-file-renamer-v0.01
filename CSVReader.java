import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    private String source;
    private String CSVName;
    private ArrayList<StudentData> studentData;

    public CSVReader(String source, String CSVName) {
        this.source = source;
        this.CSVName = CSVName;
        studentData = new ArrayList<StudentData>();
    }

    public boolean readCSV() {
        String path = source + File.separator + CSVName;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = " ";
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

    public ArrayList<StudentData> getStudentData() {
        return studentData;
    }
}