/**
 * This class represents a vertex in the HollywoodGraph.
 * Encapsulates Actors and Movies into one object type.
 * 
 * @author Sophie Lin
 * @author Lilymoon Whalen
 * @author Rachel Hu
 * @version December 12, 20223
 */
public class FilmElement {
    protected String name; // name of the movie or actor
    protected String typeOfElement; //stores whether it is a movie or an actor

    /**
     * Constructor for a FilmElement based on its name and type
     * @param name - Name of the FilmElement
     * @param type - "Movie" or "Actor"
     */
    public FilmElement(String name, String type) {
        this.name = name;
        this.typeOfElement = type;
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
     * Returns the type of element this FilmElement is (Movie or Actor)
     * @return String describing the type
     */
    public String getType(){
        return typeOfElement;
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
