/**
 * This class represents a vertex in the HollywoodGraph.
 * 
 * //TODO REMOVE THIS LATER ONCE QUESTIONS ARE RESOLVED: wrote this bc the T casts in HollywoodGraph feel sketchy
 * 
 *  @author Sophie Lin
 *  @author Lilymoon Whalen 
 *  @author Rachel Hu
 * @version 11/30/2023
 */
public class FilmElement {
    String name;
    /**
     * Constructor 
     * @param String name of the element
     */
    public FilmElement(String name){
        this.name = name;
    }
    /**
     * Returns the name of the element
     * @return String name of the element
     */
    public String getName(){
        return name;
    }

}
