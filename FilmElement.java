/**
 * This class represents a vertex in the HollywoodGraph.
 * Encapsulates Actors and Movies into one object type.
 * 
 * @author Sophie Lin
 * @author Lilymoon Whalen
 * @author Rachel Hu
 * @version December 6, 20223
 */
public class FilmElement {
    protected String name; // name of the movie or actor

    /**
     * Constructor
     * 
     * @param String name of the element
     */
    public FilmElement(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the element
     * 
     * @return String name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Compares if a given FilmElement is the same as this FilmElement based on
     * their names
     * 
     * @param obj the other FilmElement to compare with this one
     * @return true if the two have the same name
     *         false if they have different names
     */
    public boolean equals(FilmElement obj) {
        return obj.getName().equals(this.name);
    }

}
