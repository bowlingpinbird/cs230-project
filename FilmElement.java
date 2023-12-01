/**
 * This class represents a vertex in the HollywoodGraph.
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
