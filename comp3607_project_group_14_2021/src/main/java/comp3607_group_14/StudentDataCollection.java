package comp3607_group_14;

import java.util.ArrayList;

public class StudentDataCollection implements Aggregate {

    private ArrayList<StudentData> studentData;

    public StudentDataCollection(ArrayList<StudentData> studentData) {
        this.studentData = studentData;
    }

    public Iterator createIterator() {

        Iterator iterator = new ListIterator();

        return iterator;
    }

    private class ListIterator implements Iterator {

        private int currentPosition = 0;

        public boolean hasNext() {

            if (currentPosition < studentData.size())
                return true;
            else
                return false;
        }

        public Object next() {
            if (this.hasNext()) {

                Object o = studentData.get(currentPosition++);
                return o;

            } else
                return null;

        }

        public void reset() {
            currentPosition = 0;
        }

    }

}
