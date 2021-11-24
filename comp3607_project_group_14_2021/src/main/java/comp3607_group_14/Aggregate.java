package comp3607_group_14;

/**
 * This class defines the what methods must be implemented by concrete aggregate
 * classes
 */
public interface Aggregate {

    /**
     * This This methods create an iterator which is used to traverse the collection
     */
    public Iterator createIterator();
}
