import java.util.ArrayList;
/**
 * @author Sophie Lin
 * @author Rachel Hu
 * @author Lilymoon Whalen
 * @version 11/28/2023
 */
public class Movie{
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    private String name;

    public Movie(String movieName){
        name = movieName;
    }
    
    public String getName(){
        return name;
    }
}