package comp3607_group_14;

/**
 * This interface provides what methods a concrete iterator should have
 * 
 */
public interface Iterator {

    /**
     * This method returns true if a collection has a next object
     */
    public boolean hasNext();

    /**
     * This method returns the next object in a collection
     */
    public Object next();

    /**
     * This method resets the traversal of a collection
     */
    public void reset();

}
