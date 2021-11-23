package comp3607_group_14;

import java.util.ArrayList;

/**
 * This class creates an ArrayList of StudentData objects
 */
public class StudentDataCollection implements Aggregate {

    /**
     * This variable stores an ArrayList of StudentData Objects
     */
    private ArrayList<StudentData> studentData;

    /**
     * This contructor creates an ArrayList of StudentData Objects
     */
    public StudentDataCollection(ArrayList<StudentData> studentData) {
        this.studentData = studentData;
    }

    
    /** 
     * This function creates and returns an Iterator object
     * @return Iterator
     */
    public Iterator createIterator() {

        Iterator iterator = new ListIterator();

        return iterator;
    }


    /**
     *This class
     */
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
