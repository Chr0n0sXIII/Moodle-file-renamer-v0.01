package comp3607_group_14;

import java.util.ArrayList;

/**
 * This class stores an ArrayList of StudentData objects and uses an iterator to
 * traverse it
 */
public class StudentDataCollection implements Aggregate {

    /**
     * This variable stores an ArrayList of StudentData Objects
     */
    private ArrayList<StudentData> studentData;

    /**
     * This contructor instantiates a StudentDataCollection object
     */
    public StudentDataCollection(ArrayList<StudentData> studentData) {
        this.studentData = studentData;
    }

    /**
     * This function creates and returns an Iterator object
     * 
     * @return Iterator
     */
    public Iterator createIterator() {

        Iterator iterator = new ListIterator();

        return iterator;
    }

    /**
     * This class is the iterator element of the iterator design pattern
     */
    private class ListIterator implements Iterator {

        /**
         * This variable stores the current position of traversal in the collection
         */
        private int currentPosition = 0;

        /**
         * This method returns true if a collection has a next object
         */
        public boolean hasNext() {

            if (currentPosition < studentData.size())
                return true;
            else
                return false;
        }

        /**
         * This method returns the next object in a collection
         */
        public Object next() {
            if (this.hasNext()) {

                Object o = studentData.get(currentPosition++);
                return o;

            } else
                return null;

        }

        /**
         * This method resets the traversal of a collection
         */
        public void reset() {
            currentPosition = 0;
        }

    }

}
